package io.github.kurrycat2004.opeq.util;

import moze_intel.projecte.utils.EMCHelper;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class PEEMCHelper {
    public static final ThreadLocal<Boolean> skipEmcMixin = ThreadLocal.withInitial(() -> false);

    public static boolean originalDoesItemHaveEmc(@NotNull ItemStack stack) {
        skipEmcMixin.set(true);
        return EMCHelper.doesItemHaveEmc(stack);
    }
}
