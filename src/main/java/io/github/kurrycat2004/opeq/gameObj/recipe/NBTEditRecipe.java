package io.github.kurrycat2004.opeq.gameObj.recipe;

import io.github.kurrycat2004.Tags;
import io.github.kurrycat2004.opeq.util.PEEMCHelper;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class NBTEditRecipe extends IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {
    private final Item item;
    private final String group;

    public NBTEditRecipe(Item item, String group) {
        this.item = item;
        this.group = Tags.MODID + ":" + group;
    }

    @Nullable
    private ItemStack getOtherIfValid(@NotNull InventoryCrafting inv) {
        int itemSlot = -1;
        int otherItemSlot = -1;

        for (int i = 0; i < inv.getSizeInventory(); ++i) {
            ItemStack stack = inv.getStackInSlot(i);
            if (stack.isEmpty()) continue;

            if (stack.getItem() == item) {
                if (itemSlot == -1) itemSlot = i;
                else return null;
            } else {
                if (otherItemSlot == -1) otherItemSlot = i;
                else return null;
            }
        }

        if (itemSlot == -1 || otherItemSlot == -1) return null;
        return inv.getStackInSlot(otherItemSlot);
    }

    @Override
    public boolean matches(@NotNull InventoryCrafting inv, @NotNull World worldIn) {
        ItemStack otherStack = getOtherIfValid(inv);
        if (otherStack == null || !PEEMCHelper.originalDoesItemHaveEmc(otherStack)) return false;
        return otherItemMatches(otherStack);
    }

    public abstract boolean otherItemMatches(@NotNull ItemStack stack);

    @Override
    @NotNull
    public ItemStack getCraftingResult(@NotNull InventoryCrafting inv) {
        ItemStack otherStack = getOtherIfValid(inv);
        if (otherStack == null) return ItemStack.EMPTY;
        ItemStack result = otherStack.copy();
        result.setCount(1);
        return craftedOtherItem(result);
    }

    @NotNull
    public abstract ItemStack craftedOtherItem(@NotNull ItemStack stack);

    @Override
    public boolean canFit(int width, int height) {
        return width * height >= 2;
    }

    @Override
    @NotNull
    public ItemStack getRecipeOutput() {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean isDynamic() {
        return true;
    }

    @Override
    @NotNull
    public String getGroup() {
        return group;
    }

    @Override
    @NotNull
    public NonNullList<ItemStack> getRemainingItems(@NotNull InventoryCrafting inv) {
        NonNullList<ItemStack> remaining = NonNullList.withSize(inv.getSizeInventory(), ItemStack.EMPTY);
        for (int i = 0; i < inv.getSizeInventory(); ++i) {
            ItemStack stack = inv.getStackInSlot(i);
            if (stack.getItem() == item) remaining.set(i, stack.copy());
            else remaining.set(i, ItemStack.EMPTY);
        }
        return remaining;
    }
}
