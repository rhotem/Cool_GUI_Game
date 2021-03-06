package me.justacat.GuiGame.items;

import me.justacat.GuiGame.misc.Chat;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public abstract class GameItem {

    protected ItemStack item;

    public GameItem(Material type, int amount,String name, String... lore) {

        ItemStack itemStack = new ItemStack(type, amount);

        ItemMeta itemMeta = itemStack.getItemMeta();

        List<String> newLore = Chat.colorList(lore); //just colors the lore

        itemMeta.setLore(newLore);

        itemMeta.setLocalizedName("GameItem");

        itemMeta.addItemFlags(ItemFlag.values()); //hide all the flags

        itemMeta.setDisplayName(Chat.colorMessage(name));

        itemStack.setItemMeta(itemMeta);
        item = itemStack;
    }

    public ItemStack getItem() {return item;}

    public abstract void onClick(Player player, int slotNumber);

    public static GameItem randomGameItem() {

        double random = Math.random();

        if (random > 0.9) {
            return MoneyGenerator.randomMoneyGenerator();
        } else {
            return MoneyBag.randomMoneyBag();
        }

    }

}
