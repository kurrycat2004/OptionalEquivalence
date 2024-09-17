package io.github.kurrycat2004.opeq.gameObj.recipe;

import io.github.kurrycat2004.opeq.util.OpEqEmcHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.jetbrains.annotations.NotNull;

public class RecipeStabilizationTape extends NBTEditRecipe {
    public RecipeStabilizationTape(Item item, String group) {
        super(item, group);
    }

    @Override
    public boolean otherItemMatches(@NotNull ItemStack stack) {
        return !OpEqEmcHelper.isStabilized(stack);
    }

    @Override
    @NotNull
    public ItemStack craftedOtherItem(@NotNull ItemStack stack) {
        NBTTagCompound nbt = stack.getTagCompound();
        if (nbt == null) {
            nbt = new NBTTagCompound();
            stack.setTagCompound(nbt);
        }
        nbt.setBoolean(OpEqEmcHelper.TAG_STABILIZED, true);
        return stack;
    }
}
