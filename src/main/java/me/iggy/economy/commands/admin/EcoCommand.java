package me.iggy.economy.commands.admin;

import com.jonahseguin.drink.annotation.Command;
import com.jonahseguin.drink.annotation.Require;
import com.jonahseguin.drink.annotation.Sender;
import me.iggy.economy.Economy;
import me.iggy.economy.utils.CC;
import org.bukkit.entity.Player;

public class EcoCommand {

    @Command(name = "", desc = "Eco Command", async = false)
    @Require("economy.admin")
    public static void onCommand(@Sender Player sender) {
        Economy.getInstance().getConfig().getStringList("eco-help-message").forEach(msg -> sender.sendMessage(CC.translate(msg)));
    }

}
