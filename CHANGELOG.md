# Changelog

## [1.1.0]

### Added
- 用 Mixin（`MixinRecipeReplace` 注入 `RecipeMap#addRecipe`）改写 GTCEu **激光蚀刻机**与**压膜机**的配方：
  配方里的透镜（`OrePrefix.craftingLens` 彩色透镜，白1~黑16、无色17、其他 18~32）与模头
  （`MetaItems.SHAPE_MOLDS` 数组下标 +1，1~21；其他模头 24~32）全部替换为**编程电路**（`IntCircuitIngredient`，
  不消耗）。注册时按“去掉透镜/模头后的输入 + 全部输出 + 时长/耗电”归一化去重，
  除电路号外完全一致的配方只保留第一条，避免 JEI 重复。需要 MixinBooter 运行。
- 重写 **ME 输出总成（ME Output Assembly，meta id 2）**，并新增 **ME 库存输入总成
  （ME Input Assembly，meta id 3）**。两者共用底层逻辑基类 `MetaTileEntityMEAssembly`，
  但 **GUI 完全拆分**：
  - **输出总成 GUI**：只显示“输出缓存”——物品缓存 + 流体缓存两个可滚动 AE 列表，
    不显示幽灵标记槽；缓存写 NBT，无 AE 网络时产物留在缓存里并在界面中可见。
  - **输入总成 GUI**：只显示幽灵标记槽——8 列 × 6 行共 48 个标记槽，
    `ScrollableListWidget` 带滚动条（一屏 4 行），不显示任何缓存区。
    左键放物品标记、右键放流体标记（从光标上的流体容器取），空手点击清除；
    未标记的槽位不参与过滤。
  - **外观**：输出总成叠 `Textures.ME_OUTPUT_BUS`（在线 `_ACTIVE`），
    输入总成叠 `Textures.ME_INPUT_BUS`（在线 `_ACTIVE`）；
    物品模型改用 GT 的动态 MTE 模型 `MetaTileEntityRenderer.MODEL_LOCATION`
    （GT 1.12.2 没有 `gregtech:me_output_bus#inventory` 这类模型，总线外观由覆盖贴图决定）。
  - **Tooltip**：按类型给出 §7 说明；开启总线隔离时追加 `§e当前模式：总线隔离`。
  - **总线隔离**：读取所在多方块控制器的 `IDistinctBusController#isDistinct()`。
    开启时只使用本总成自己的标记；关闭时（全局）本总成还会读取结构内所有同类型总成的
    标记，即“标记作用于整个多方块结构内的所有同类型总线”。
  - **输出总成**：保留物品/流体内部缓存（`SerializableItemList/SerializableFluidList`，写 NBT），
    `update()` 中 AE 网络可用时直接 `injectItems` 推入网络；无网络时留在缓存并在 GUI 里显示。
    标记作为过滤器：有标记时只推送匹配的物品/流体，没有标记时全部推送。
  - **输入总成**：不存物品，只存标记。物品侧 `IItemHandlerModifiable` 每个槽位对应一个标记，
    `getStackInSlot` 用 SIMULATE 查询 AE 可用量、`extractItem` 直接从 AE 提取；
    流体侧每个标记槽注册一个 `IFluidTank`，`getFluid/getFluidAmount` 查询 AE、
    `drain(..., true)` 直接提取，全程不落地。
  合成：工作台无序，`ME输出仓 + ME输出总线 → ME输出总成`、
  `ME输入仓 + ME输入总线 → ME库存输入总成`；未安装 AE2 时跳过注册与配方。

### Fixed
- 矿石处理机配方产出改为照抄 gtlitecore 集成矿石处理厂**全部模式（电路 1~10）的配方并集**：
  同一物品只占一个槽位、数量取各模式最大值（主产物数量不变），几率产出一律按 100% 给 1 个。
  有瑕宝石（gemFlawed）、破碎宝石（gemChipped）与纯净的粉（dustPure）折算成同材料、同数量的粉，
  并累加到该材料的粉上。石种变体（花岗岩/石灰岩等）在 gtlite 只注册了
  ore/oreNetherrack/oreEndstone 时回退到基础矿石并集，按前缀倍率换算数量。
- 旧产物规则降级为兜底，仅在 gtlitecore 没有对应配方时使用；白名单补上 `gtlitecore` 命名空间，
  冰晶石矿石不再只剩钠粉。
- 石化工厂 JEI 界面修复：配方表容量改为**精确值**（物品输入 0、流体输入 2、物品输出 2、流体输出 13），
  不再出现多余的空格子；并改用石化工厂专用界面，把 2 个物品输出 + 13 个流体输出排成 3 列紧凑网格，
  整个界面落在 JEI 配方分类高度（110）以内，最后几种产物流体不再画到分类外面。

### Changed
- 石化工厂配方重做：4 条“1000mB 基础油（原油/石油/重油/轻油）+ 1000mB 蒸汽”的超级配方，
  按 GTCEu 的标准化工数据做数学等效合并——蒸馏塔分馏 → **四股馏分全部加氢脱硫（产 H2S）**
  → 脱硫后的重油/轻油/石脑油/炼油气**全部进石油裂化机做重度蒸汽裂化**
  → 四种裂化产物再分别蒸馏 → H2S 电解（H2 100% 回补加氢）。
  最终产物为合并后的 13 种流体（重油、轻质燃料、石脑油、甲苯、苯、丁烯、丁二烯、丙烷、丙烯、
  乙烷、乙烯、甲烷、氦气）+ 硫粉/碳粉副产物；氢平衡按 GT 数据恰好闭环，无需额外氢气进出。
  统一 10 tick、192 EU/t。
