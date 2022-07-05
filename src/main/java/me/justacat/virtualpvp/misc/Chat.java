package me.justacat.virtualpvp.misc;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class Chat {

    public static String colorMessage(String message){
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static List<String> colorList(String... list) {
        List<String> list1 = new ArrayList<>();
        for (String str : list) {
            list1.add(Chat.colorMessage(str));
        }
        return list1;
    }

}
