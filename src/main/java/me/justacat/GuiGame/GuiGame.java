package me.justacat.GuiGame;

import me.justacat.GuiGame.commands.OpenGameCommand;
import me.justacat.GuiGame.listeners.InventoryEvents;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class GuiGame extends JavaPlugin {


    public static GuiGame instance;
    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getPluginManager().registerEvents(new InventoryEvents(), this);

        getCommand("openGame").setExecutor(new OpenGameCommand());
    }

    @Override
    public void onDisable() {
    }
}
