package io.github.kurrycat2004.opeq.config;

import io.github.kurrycat2004.opeq.config.settings.ClientSettings;
import io.github.kurrycat2004.opeq.config.settings.ServerSettings;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class OpEqConfigManager {
    public static void sync(boolean init) {
        if (FMLCommonHandler.instance().getEffectiveSide().isClient()) {
            ConfigManager.sync(
                    ClientSettings.INSTANCE, "client_settings",
                    "These options are client-side only",
                    "opeq.config.client_settings",
                    false, false,
                    init
            );
        }
        ConfigManager.sync(
                ServerSettings.INSTANCE, "server_settings",
                "These options require a server restart on dedicated servers",
                "opeq.config.server_settings",
                false, false,
                init
        );

        ConfigManager.save();
    }
}
