package io.github.kurrycat2004.opeq.config;

import io.github.kurrycat2004.Tags;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.config.GuiConfig;

public class OpEqConfigGui extends GuiConfig {
    public OpEqConfigGui(GuiScreen parentScreen) {
        super(parentScreen,
                ConfigManager.getConfigElements(Tags.MODID, "opeq.config.general"),
                Tags.MODID, null, false, false,
                GuiConfig.getAbridgedConfigPath(ConfigManager.configFile.getAbsolutePath()),
                null
        );

        if (Minecraft.getMinecraft().getIntegratedServer() == null)
            this.configElements.removeIf(iConfigElement -> iConfigElement.getName().equals("server_settings"));
    }
}