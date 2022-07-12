package me.justacat.guigame;

import me.justacat.guigame.commands.OpenGameCommand;
import me.justacat.guigame.listeners.InventoryEvents;
import me.justacat.guigame.listeners.JoinQuitEvents;
import me.justacat.guigame.misc.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class GuiGame extends JavaPlugin {


    public static GuiGame instance;
    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getPluginManager().registerEvents(new InventoryEvents(), this);
        Bukkit.getPluginManager().registerEvents(new JoinQuitEvents(), this);

        getCommand("openGame").setExecutor(new OpenGameCommand());

        FileManager.generateFolders();;

    }

    @Override
    public void onDisable() {
    }
}
