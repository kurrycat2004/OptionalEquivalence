package io.github.kurrycat2004.opeq.gameObj.item;


import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ItemStabilization extends Item {
    public ItemStabilization(String translationKey) {
        this.maxStackSize = 1;
        setCreativeTab(CreativeTabs.MISC);
        setMaxStackSize(1);
        setTranslationKey(translationKey);
        setRegistryName(translationKey);
    }

    @Override
    @NotNull
    public ItemStack getContainerItem(ItemStack itemStack) {
        return itemStack.copy();
    }

    @Override
    public boolean hasContainerItem(@NotNull ItemStack stack) {
        return true;
    }
}