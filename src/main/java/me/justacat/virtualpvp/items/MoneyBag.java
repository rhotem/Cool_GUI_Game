package me.justacat.virtualpvp.items;

import me.justacat.virtualpvp.SoloGame;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.concurrent.ThreadLocalRandom;

public class MoneyBag extends GameItem {

    private final int moneyGives;

    public MoneyBag(int moneyGives) {
        super(generatePreview(moneyGives), Math.min(moneyGives, 64), "&6Money Bag", "&0", "&7Gives &6" + moneyGives + " &7coins!");
        this.moneyGives = moneyGives;
    }


    @Override
    public void onClick(Player player) {
        SoloGame soloGame = SoloGame.activeGames.get(player.getUniqueId());
        soloGame.addMoney(moneyGives);
    }

    public static Material generatePreview(int moneyGives) {
        if (moneyGives < 16) {
            return Material.GOLD_NUGGET;
        } else if (moneyGives < 64) {
            return Material.GOLD_INGOT;
        } else {
            return Material.GOLD_BLOCK;
        }
    }

    public static MoneyBag randomMoneyBag() {

        int random = ThreadLocalRandom.current().nextInt(1, 11);
        int random2 =  ThreadLocalRandom.current().nextInt(1, 4);
        return new MoneyBag((int) Math.pow(random, random2));
    }

}
