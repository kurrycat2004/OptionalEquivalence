package io.github.kurrycat2004.opeq.mixin.events;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalBooleanRef;
import io.github.kurrycat2004.opeq.config.settings.ClientSettings;
import io.github.kurrycat2004.opeq.util.OpEqEmcHelper;
import moze_intel.projecte.events.ToolTipEvent;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Slice;

import java.util.List;
import java.util.regex.Pattern;

@Mixin(value = ToolTipEvent.class, remap = false)
public class ToolTipEventMixin {
    @Unique
    private static final Pattern opeq$PATTERN = Pattern.compile("(§[0-9A-F])", Pattern.CASE_INSENSITIVE);

    @WrapOperation(
            method = "tTipEvent",
            at = @At(
                    value = "INVOKE",
                    target = "Lmoze_intel/projecte/utils/EMCHelper;doesItemHaveEmc(Lnet/minecraft/item/ItemStack;)Z"
            )
    )
    private static boolean wrapDoesItemHaveEmc(ItemStack stack, Operation<Boolean> original, @Share("isStabilized") @NotNull LocalBooleanRef isStabilized) {
        isStabilized.set(OpEqEmcHelper.isStabilized(stack));
        if (isStabilized.get()) return true;
        return original.call(stack);
    }

    @ModifyArg(
            method = "tTipEvent",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/List;add(Ljava/lang/Object;)Z"
            ),
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lmoze_intel/projecte/utils/EMCHelper;getEmcValue(Lnet/minecraft/item/ItemStack;)J"),
                    to = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiScreen;isShiftKeyDown()Z", remap = true)
            )
    )
    private static <E> E modifyTooltipAdd(E e, @Share("isStabilized") @NotNull LocalBooleanRef isStabilized) {
        if (!isStabilized.get()) return e;
        if (ClientSettings.INSTANCE.hideEmc)
            //noinspection unchecked
            return (E) "";

        String result = opeq$PATTERN.matcher((String) e).replaceAll(TextFormatting.RED.toString());
        //noinspection unchecked
        return (E) (TextFormatting.GRAY + "[" + result.trim() + TextFormatting.RESET + TextFormatting.GRAY + "]");
    }

    @WrapOperation(
            method = "tTipEvent",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/List;add(Ljava/lang/Object;)Z"
            ),
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lmoze_intel/projecte/utils/EMCHelper;doesItemHaveEmc(Lnet/minecraft/item/ItemStack;)Z"),
                    to = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiScreen;isShiftKeyDown()Z")
            )
    )
    private static <E> boolean wrapTooltipAdd(List<E> instance, E e, Operation<Boolean> original, @Share("isStabilized") @NotNull LocalBooleanRef isStabilized) {
        if (isStabilized.get() && ClientSettings.INSTANCE.hideEmc) return false;
        //noinspection MixinExtrasOperationParameters
        return original.call(instance, e);
    }
}
