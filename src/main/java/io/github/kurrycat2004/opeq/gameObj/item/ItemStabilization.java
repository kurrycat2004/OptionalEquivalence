package io.github.kurrycat2004.opeq.gameObj.item;


import com.google.common.collect.Multimap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ItemStabilization extends Item {
    private double attackDamageModifier = 0.0F;

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

    public ItemStabilization setAttackDamageModifier(double attackDamageModifier) {
        this.attackDamageModifier = attackDamageModifier;
        return this;
    }

    @NotNull
    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(@NotNull EntityEquipmentSlot equipmentSlot, @NotNull ItemStack stack) {
        Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(equipmentSlot, stack);
        if (equipmentSlot != EntityEquipmentSlot.MAINHAND) return multimap;
        if (this.attackDamageModifier == 0.0F) return multimap;

        multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", this.attackDamageModifier, 0));
        return multimap;
    }
}