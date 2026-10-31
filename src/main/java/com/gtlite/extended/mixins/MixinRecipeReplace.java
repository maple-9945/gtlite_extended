package com.gtlite.extended.mixins;

import com.gtlite.extended.common.recipe.LensMoldRecipeRewriter;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.util.ValidationResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * 拦截 GTCEu 的配方注册入口，把激光蚀刻机 / 压膜机配方里的透镜、模头替换成编程电路。
 *
 * <p>注入点说明：GT 的所有配方最终都经过
 * {@code RecipeMap#addRecipe(ValidationResult<Recipe>)}（{@code RecipeBuilder#buildAndRegister()} 的落点）。
 * 在 HEAD 处：
 * <ul>
 *     <li>不是目标配方表 → 直接放行；</li>
 *     <li>不是透镜/模头配方 → 直接放行；</li>
 *     <li>是“与已注册配方（去掉透镜/模头后）完全一致”的重复配方 → 取消添加（合并）；</li>
 *     <li>否则用改写后的配方调用 {@code compileRecipe} 重建查找树，并取消原始添加。</li>
 * </ul>
 *
 * <p>{@code remap = false}：GT 是 1.12.2 的 mod 类，方法名不做 SRG 重映射。</p>
 */
@Mixin(value = RecipeMap.class, remap = false)
public abstract class MixinRecipeReplace {

    @Inject(method = "addRecipe", at = @At("HEAD"), cancellable = true, remap = false)
    private void gtlite_extended$replaceLensAndMolds(ValidationResult<Recipe> validationResult,
                                                     CallbackInfoReturnable<Boolean> cir) {
        // Mixin 里 this 就是被注入的 RecipeMap 实例
        RecipeMap<?> self = (RecipeMap<?>) (Object) this;
        if (validationResult == null || validationResult.getResult() == null) {
            return;
        }
        if (!LensMoldRecipeRewriter.isTarget(self)) {
            return;
        }

        LensMoldRecipeRewriter.Result result = LensMoldRecipeRewriter.rewrite(self, validationResult.getResult());
        if (result == null) {
            // 不是透镜/模头配方：原样交给 GT
            return;
        }
        if (result.isDuplicate()) {
            // 合并：除透镜/模头（及对应电路号）外完全一致的配方只保留第一条
            cir.setReturnValue(Boolean.FALSE);
            return;
        }

        // 用替换后的配方走 GT 自己的编译流程（会写进 RecipeMap 的查找树，JEI 与机器看到同一份），
        // 同时取消原始配方的添加，避免重复。
        cir.setReturnValue(self.compileRecipe(result.getRecipe()));
    }
}
