package io.github.kurrycat2004.opeq.util;

import io.github.kurrycat2004.Tags;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;

public class OpEqEmcHelper {
    public static final String TAG_STABILIZED = Tags.MODID + ":" + "emcStabilized";

    public static final HashSet<String> STABILIZED_TAGS = new HashSet<>();

    public static boolean isTagStabilized(@NotNull ItemStack item) {
        NBTTagCompound nbt = item.getTagCompound();
        if (nbt == null) return false;
        return nbt.getBoolean(TAG_STABILIZED);
    }

    public static boolean isStabilized(@NotNull ItemStack item) {
        NBTTagCompound nbt = item.getTagCompound();
        if (nbt == null) return false;
        if (nbt.getBoolean(TAG_STABILIZED)) return true;
        System.out.println(nbt);
        for (String tag : STABILIZED_TAGS) {
            System.out.println(tag);
            if (nbt.hasKey(tag)) return true;
        }
        return false;
    }
}
