package me.iggy.economy.commands;

import com.jonahseguin.drink.annotation.Command;
import com.jonahseguin.drink.annotation.Require;
import com.jonahseguin.drink.annotation.Sender;
import me.iggy.economy.Economy;
import me.iggy.economy.utils.CC;
import org.bukkit.entity.Player;

public class ReloadCommand {

    @Command(name = "", desc = "Economy Config Reload Command", async = false)
    @Require("economy.reload")
    public static void onCommand(@Sender Player sender) {
        long currTime = System.currentTimeMillis();
        Economy.getInstance().reloadConfig();
        sender.sendMessage(CC.translate("&aEconomy Config Reloaded in " + (System.currentTimeMillis() - currTime) + "ms"));
    }

}
