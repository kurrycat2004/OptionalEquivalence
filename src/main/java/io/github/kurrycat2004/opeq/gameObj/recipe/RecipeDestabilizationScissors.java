package io.github.kurrycat2004.opeq.gameObj.recipe;

import io.github.kurrycat2004.opeq.util.OpEqEmcHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.jetbrains.annotations.NotNull;

public class RecipeDestabilizationScissors extends NBTEditRecipe {
    public RecipeDestabilizationScissors(Item item, String group) {
        super(item, group);
    }

    @Override
    public boolean otherItemMatches(@NotNull ItemStack stack) {
        return OpEqEmcHelper.isTagStabilized(stack);
    }

    @Override
    @NotNull
    public ItemStack craftedOtherItem(@NotNull ItemStack stack) {
        NBTTagCompound nbt = stack.getTagCompound();
        if (nbt == null) return ItemStack.EMPTY;
        nbt.removeTag(OpEqEmcHelper.TAG_STABILIZED);
        if (nbt.isEmpty()) stack.setTagCompound(null);
        return stack;
    }
}
