package nl.dionnek.lecternbook.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MessageUtils {

    public static String formatMessage(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static void sendMessage(Player p, String message) {
        p.sendMessage(formatMessage(message));
    }
}
