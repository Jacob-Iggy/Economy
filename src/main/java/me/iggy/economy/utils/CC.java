package me.iggy.economy.utils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CC {

    private static final Pattern pattern = Pattern.compile("&#[a-fA-F0-9]{6}");

    public static List<String> translate(List<String> message) {
        return message.stream().map(CC::translate).collect(Collectors.toList());
    }

    public static String translate(String string) {
        Matcher match = pattern.matcher(string);
        while (match.find()) {
            String color = string.substring(match.start() + 1, match.end());
            string = string.replace("&" + color, net.md_5.bungee.api.ChatColor.of(color) + "");
            match = pattern.matcher(string);
        }
        return net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', string);
    }

}
