package io.github.kurrycat2004.opeq.config;

import io.github.kurrycat2004.Tags;
import io.github.kurrycat2004.opeq.config.settings.ServerSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.util.Set;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = Tags.MODID)
public class OpEqGuiFactory implements IModGuiFactory {
    @Override
    public void initialize(Minecraft minecraftInstance) {}

    @Override
    public boolean hasConfigGui() {return true;}

    @Override
    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {return null;}

    @Override
    public GuiScreen createConfigGui(GuiScreen parentScreen) {
        return new OpEqConfigGui(parentScreen);
    }

    @SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (!event.getModID().equals(Tags.MODID)) return;
        OpEqConfigManager.sync(false);
        ServerSettings.updateStabilizedTagsSet();
    }
}

