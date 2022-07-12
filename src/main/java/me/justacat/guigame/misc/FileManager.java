package me.justacat.guigame.misc;

import me.justacat.guigame.GuiGame;
import me.justacat.guigame.SoloGame;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class FileManager {


    public static File dataFolder = GuiGame.instance.getDataFolder();

    public static File playersFolder;


    public static void generateFolders() {

        playersFolder = new File(dataFolder, "Players");

        if (!playersFolder.exists()) {

            playersFolder.mkdirs();

        }

    }


    public static File createFile(File folder, String name) {

        File file = new File(folder, name);

        try {

            if (!file.exists()) {

                file.createNewFile();

            }

        }  catch (IOException e) {
            e.printStackTrace();
        }


        return file;


    }


    public static void savePlayerData(Player player) {

        File file = createFile(playersFolder, player.getUniqueId() + ".yml");

        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);

        SoloGame game = SoloGame.activeGames.get(player.getUniqueId());

        yamlConfiguration.set("Data.Money", game.getMoney()); //saves the money
        yamlConfiguration.set("Data.MoneyPerSecond", game.getMoneyPerSecond()); //saves the money per second
        yamlConfiguration.set("Data.SlotsUnlocked", game.getSlotsUnlocked()); //saves the slots

        try {
            yamlConfiguration.save(file);
        } catch (IOException e) {
            Bukkit.getLogger().warning("Couldn't save data for player " + player.getName() + " !");
        }


    }

    public static void loadPlayerData(Player player) {

        SoloGame game = new SoloGame(player);

        File file = new File(playersFolder, player.getUniqueId() + ".yml");

        if (!file.exists()) return;

        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);

        int money = yamlConfiguration.getInt("Data.Money");
        int moneyPerSecond = yamlConfiguration.getInt("Data.MoneyPerSecond");
        int slots = yamlConfiguration.getInt("Data.SlotsUnlocked");


        game.setMoney(money);
        game.setMoneyPerSecond(moneyPerSecond);
        game.setSlotsUnlocked(slots);

    }



}
