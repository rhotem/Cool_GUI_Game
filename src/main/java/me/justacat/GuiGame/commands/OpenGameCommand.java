package me.justacat.GuiGame.commands;

import me.justacat.GuiGame.SoloGame;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class OpenGameCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender;

            if (!SoloGame.activeGames.containsKey(player.getUniqueId())) {SoloGame.startNewGame(player);}

            SoloGame soloGame = SoloGame.activeGames.get(player.getUniqueId());

            soloGame.openGame();
            return true;

        } else {
            Bukkit.getLogger().warning("You have to be a player to execute this command!");
            return false;
        }


    }
}
