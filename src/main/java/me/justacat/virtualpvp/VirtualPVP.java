package me.justacat.virtualpvp;

import org.bukkit.plugin.java.JavaPlugin;

public final class VirtualPVP extends JavaPlugin {


    public static VirtualPVP instance;
    @Override
    public void onEnable() {
        instance = this;

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
