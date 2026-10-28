/*
 * ============================================================================
 *  OreProcessingRecipeMaps#buildOutputs / #addOutput  ——  最新版本快照
 * ============================================================================
 *  说明：本文件只是「代码快照」，位于 src/main/java 之外，不参与 Gradle 编译，
 *        方便在 IDEA 里单独对着看。真正生效的实现已经在：
 *          src/main/java/com/gtlite/extended/common/recipe/OreProcessingRecipeMaps.java
 *        两者内容完全一致。
 *
 *  规则（严格按用户要求）：
 *    1. 主产物粉 = OreDictUnifier.get(OrePrefix.dust, material)，即「这块矿石自己的材料」的粉；
 *       绝不用 PropertyKey.DUST 去材料表里反查别的材料（否则冰晶石会变成钠粉）。
 *       数量 = max(4, resolvePrimaryAmount(...))。
 *    2. 宝石变体（同属主产物）：材料有 PropertyKey.GEM 时输出
 *       gem / gemExquisite / gemFlawless 各 1 个；绝不输出 gemFlawed / gemChipped。
 *    3. 副产物粉永远排最后：遍历 oreProperty.getOreByProducts()，
 *       数量 = max(1, byProductMultiplier) * 主产物数量。
 *    4. 槽位保护：插入顺序即槽位顺序（主产物粉 → 宝石 → 副产物），
 *       超过 OUTPUT_SLOTS 个时从末尾截断，因此只会丢副产物。
 *
 *  相关常量（在本类顶部，非本快照的一部分）：
 *      public static final int OUTPUT_SLOTS = 6;          // 机器 / 配方表 / 截断共用
 *      private static final int MIN_PRIMARY_AMOUNT = 4;   // 主产物数量下限
 * ============================================================================
 */

    /**
     * 由 Materials + OrePrefixes 直接推导某一材料矿石的全部产出（不再扫 GT 的机器配方表）。
     *
     * <p>插入顺序就是优先级顺序，也就是配方产出的槽位顺序：</p>
     * <ol>
     *     <li><b>主产物粉</b>：严格是 {@code OreDictUnifier.get(OrePrefix.dust, material)}，
     *         即“这块矿石自己的材料”的粉。数量 = {@code max(4, resolvePrimaryAmount(...))}。
     *         这里<b>不允许</b>用 {@link PropertyKey#DUST} 去材料表里反查，
     *         否则冰晶石矿石会被它的副产物钠粉顶替成“主产物”（主副产物颠倒的元凶）；</li>
     *     <li><b>宝石变体</b>：材料有 {@link PropertyKey#GEM} 时输出
     *         gem（普通）/ gemExquisite（精致）/ gemFlawless（无瑕）各 1 个；
     *         绝不输出 gemFlawed（有瑕）与 gemChipped（碎裂）；</li>
     *     <li><b>副产物粉</b>：遍历 {@code oreProperty.getOreByProducts()}，每个副产物
     *         {@code max(1, byProductMultiplier) * mainAmount} 个；</li>
     *     <li><b>槽位保护</b>：结果超过 {@link #OUTPUT_SLOTS} 个时从末尾截断。
     *         因为顺序已经是“主产物粉 → 宝石 → 副产物”，被截掉的只可能是副产物。</li>
     * </ol>
     *
     * <p>同一物品（registry@meta 相同）只占一个槽位，重复命中时数量累加。</p>
     */
    private static List<ItemStack> buildOutputs(OrePrefix prefix, Material material, OreProperty oreProperty) {
        List<ItemStack> outputs = new ArrayList<>(OUTPUT_SLOTS);

        // ── 1. 主产物粉：只认这个材料自己的粉 ─────────────────────────────────────
        // 绝不能写成「遍历材料表找第一个有 DUST 属性的材料」——那样冰晶石会变成钠粉。
        int mainAmount = Math.max(MIN_PRIMARY_AMOUNT,
                resolvePrimaryAmount(prefix, material, oreProperty, isDoubled(prefix)));
        ItemStack mainDust = OreDictUnifier.get(OrePrefix.dust, material);
        if (isValidStack(mainDust)) {
            addOutput(outputs, mainDust, mainAmount);
        } else if (material.hasProperty(PropertyKey.DUST)) {
            // 材料声明有粉、但这套 GT 没生成粉物品：如实告警，绝不用别的材料顶替
            GTLiteExtended.LOGGER.warn(
                    "[GTLite Extended] {} 声明有 DUST 属性，但 OrePrefix.dust 解析不到物品（{}）；"
                            + "该矿石本次没有主产物粉，不会用别的材料替代",
                    material.getName(), stackName(mainDust));
        }

        // ── 2. 宝石变体（同样属于主产物）：gem / gemExquisite / gemFlawless 各 1 ──
        // 绝不输出 gemFlawed / gemChipped。
        if (material.hasProperty(PropertyKey.GEM)) {
            addOutput(outputs, OreDictUnifier.get(OrePrefix.gem, material), 1);
            addOutput(outputs, OreDictUnifier.get(OrePrefix.gemExquisite, material), 1);
            addOutput(outputs, OreDictUnifier.get(OrePrefix.gemFlawless, material), 1);
        }

        // ── 3. 副产物粉：永远排在最后，数量 = max(1, 副产物倍率) × 主产物数量 ─────
        int byProductAmount = Math.max(1, oreProperty.getByProductMultiplier()) * mainAmount;
        for (Material byProduct : oreProperty.getOreByProducts()) {
            if (byProduct == null || byProduct == material) {
                // 副产物列表里偶尔会把自己再列一遍，跳过，避免把数量加到主产物上
                continue;
            }
            if (!byProduct.hasProperty(PropertyKey.DUST)) {
                continue;
            }
            addOutput(outputs, OreDictUnifier.get(OrePrefix.dust, byProduct), byProductAmount);
        }

        // ── 4. 槽位保护：顺序已固定，从末尾截断只会丢副产物 ───────────────────────
        if (outputs.size() > OUTPUT_SLOTS) {
            GTLiteExtended.LOGGER.warn("[GTLite Extended] {} 的产出 {} 种超过 {} 个槽位，末尾（副产物）被截断为 {} 种",
                    material.getName(), outputs.size(), OUTPUT_SLOTS, OUTPUT_SLOTS);
            return new ArrayList<>(outputs.subList(0, OUTPUT_SLOTS));
        }
        return outputs;
    }

    /**
     * 追加一个产出：严格按调用顺序占槽（不排序、不重排），同一物品只占一个槽并累加数量；
     * 命名空间不合规（非 gregtech / minecraft）的产出直接丢弃。
     *
     * <p>顺序即优先级，所以调用方（{@link #buildOutputs}）的调用次序就是槽位次序。</p>
     */
    private static void addOutput(List<ItemStack> outputs, ItemStack stack, int amount) {
        if (!isValidStack(stack)) {
            return;
        }
        int total = Math.max(1, stack.getCount()) * Math.max(1, amount);
        String key = stackKey(stack);
        for (ItemStack existing : outputs) {
            if (stackKey(existing).equals(key)) {
                existing.setCount(Math.min(existing.getCount() + total, existing.getMaxStackSize()));
                return;
            }
        }
        ItemStack copy = stack.copy();
        copy.setCount(Math.min(total, copy.getMaxStackSize()));
        outputs.add(copy);
    }

    /** 日志用：{@code registry@meta}，空栈给出 {@code (empty)}。 */
    private static String stackName(ItemStack stack) {
        if (stack == null || stack.isEmpty()) {
            return "(empty)";
        }
        ResourceLocation registryName = stack.getItem().getRegistryName();
        return (registryName == null ? "(no-registry)" : registryName.toString()) + "@" + stack.getMetadata();
    }
