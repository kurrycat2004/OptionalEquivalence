package io.github.kurrycat2004.opeq.gameObj;

import io.github.kurrycat2004.Tags;
import io.github.kurrycat2004.opeq.gameObj.item.ItemStabilization;
import io.github.kurrycat2004.opeq.gameObj.recipe.RecipeDestabilizationScissors;
import io.github.kurrycat2004.opeq.gameObj.recipe.RecipeStabilizationTape;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber(modid = Tags.MODID)
public class ObjectHolder {
    public static final Item STABILIZATION_TAPE = new ItemStabilization("stabilization_tape");
    public static final Item DESTABILIZATION_SCISSORS = new ItemStabilization("destabilization_scissors").setAttackDamageModifier(1.0);

    @SubscribeEvent
    public static void registerItems(@NotNull RegistryEvent.Register<Item> evt) {
        IForgeRegistry<Item> r = evt.getRegistry();
        r.register(STABILIZATION_TAPE);
        r.register(DESTABILIZATION_SCISSORS);
    }

    @SubscribeEvent
    public static void registerRecipes(@NotNull RegistryEvent.Register<IRecipe> evt) {
        IForgeRegistry<IRecipe> r = evt.getRegistry();
        registerRecipe(r, new RecipeStabilizationTape(STABILIZATION_TAPE, "stabilization"), "recipe_stabilization_tape");
        registerRecipe(r, new RecipeDestabilizationScissors(DESTABILIZATION_SCISSORS, "stabilization"), "recipe_destabilization_scissors");
    }

    public static void registerModel(@NotNull Item item) {
        registerModel(item, 0);
    }

    public static void registerModel(@NotNull Item item, int meta) {
        ResourceLocation location = item.getRegistryName();
        assert location != null;
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(location, "inventory"));
    }

    private static void registerRecipe(@NotNull IForgeRegistry<IRecipe> r, @NotNull IRecipe recipe, String name) {
        r.register(recipe.setRegistryName(new ResourceLocation(Tags.MODID, name)));
    }
}
