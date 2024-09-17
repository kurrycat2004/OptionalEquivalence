package io.github.kurrycat2004.opeq.config.settings;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientSettings {
    @Config.Ignore
    public static final ClientSettings INSTANCE = new ClientSettings();

    @Config.LangKey("opeq.config.client_settings.hide_emc")
    @Config.Comment("Hide EMC values in tooltips")
    public boolean hideEmc = false;
}
