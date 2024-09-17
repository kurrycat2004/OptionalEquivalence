package io.github.kurrycat2004.opeq;

import io.github.kurrycat2004.Tags;
import io.github.kurrycat2004.opeq.config.ConfigManager;
import io.github.kurrycat2004.opeq.config.OpEqConfigManager;
import io.github.kurrycat2004.opeq.config.settings.ServerSettings;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(
        modid = Tags.MODID,
        version = Tags.VERSION,
        name = Tags.MODNAME,
        acceptedMinecraftVersions = "[1.12.2]",
        dependencies = "required-after:projecte@[1.12.2-PE1.4.1,);",
        guiFactory = "io.github.kurrycat2004.opeq.config.OpEqGuiFactory"
)
public class OpEqMod {
    public static final Logger LOGGER = LogManager.getLogger(Tags.MODID);

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ConfigManager.preInit(event);
        OpEqConfigManager.sync(true);

        ServerSettings.updateStabilizedTagsSet();
    }
}
