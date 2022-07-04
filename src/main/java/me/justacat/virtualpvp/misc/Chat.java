package me.justacat.virtualpvp.misc;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Chat {

    public static String colorMessage(String message){
        return ChatColor.translateAlternateColorCodes('&', message);
    }


}
