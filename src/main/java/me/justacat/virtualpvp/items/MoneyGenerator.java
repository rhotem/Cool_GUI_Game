package me.justacat.virtualpvp.items;

import me.justacat.virtualpvp.SoloGame;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.concurrent.ThreadLocalRandom;

public class MoneyGenerator extends GameItem {

    private final int moneyPerSecond;

    public MoneyGenerator(int moneyPerSecond) {
        super(generatePreview(moneyPerSecond), Math.min(moneyPerSecond, 64), "&6Money Generator", "&0", "&7Gives &6" + moneyPerSecond + " &7coins per second!");
        this.moneyPerSecond = moneyPerSecond;
    }

    @Override
    public void onClick(Player player) {

        SoloGame soloGame = SoloGame.activeGames.get(player.getUniqueId());

        soloGame.addMoneyPerSecond(moneyPerSecond);

    }


    public static Material generatePreview(int moneyPerSecond) {
        if (moneyPerSecond < 5) {
            return Material.SMOKER;
        } else if (moneyPerSecond < 12) {
            return Material.FURNACE;
        } else {
            return Material.BLAST_FURNACE;
        }
    }

    public static MoneyGenerator randomMoneyGenerator() {

        int random = ThreadLocalRandom.current().nextInt(1, 6);
        int random2 =  ThreadLocalRandom.current().nextInt(1, 3);

        return new MoneyGenerator(random * random2);

    }
}
