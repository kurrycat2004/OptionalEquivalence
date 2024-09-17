package io.github.kurrycat2004.opeq.mixin.utils;

import io.github.kurrycat2004.opeq.util.OpEqEmcHelper;
import io.github.kurrycat2004.opeq.util.PEEMCHelper;
import moze_intel.projecte.utils.EMCHelper;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = EMCHelper.class, remap = false)
public class EMCHelperMixin {
    // I don't know if we need this as a ThreadLocal, but I'll just keep this here
    @Unique
    private static final ThreadLocal<Boolean> opeq$skipValueMixin = ThreadLocal.withInitial(() -> Boolean.FALSE);

    @Inject(method = "doesItemHaveEmc(Lnet/minecraft/item/ItemStack;)Z", at = @At("HEAD"), cancellable = true)
    private static void injectDoesItemHaveEmcMixin(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        boolean skipMixin = PEEMCHelper.skipEmcMixin.get();
        PEEMCHelper.skipEmcMixin.set(false);
        if (!skipMixin && OpEqEmcHelper.isStabilized(stack)) cir.setReturnValue(false);
    }

    // We still want a pretty tooltip
    @Inject(method = "getEmcSellString", at = @At("HEAD"))
    private static void injectGetEmcSellStringMixin(ItemStack stack, int stackSize, CallbackInfoReturnable<String> cir) {
        opeq$skipValueMixin.set(true);
    }

    @Inject(method = "getEmcSellValue", at = @At("HEAD"), cancellable = true)
    private static void injectGetEmcSellValueMixin(ItemStack stack, CallbackInfoReturnable<Long> cir) {
        boolean skipMixin = opeq$skipValueMixin.get();
        opeq$skipValueMixin.set(false);
        if (!skipMixin && OpEqEmcHelper.isStabilized(stack)) cir.setReturnValue(0L);
    }
}
