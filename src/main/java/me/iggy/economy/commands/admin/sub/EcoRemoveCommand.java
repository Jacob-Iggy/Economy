package me.iggy.economy.commands.admin.sub;

import com.jonahseguin.drink.annotation.Command;
import com.jonahseguin.drink.annotation.Require;
import com.jonahseguin.drink.annotation.Sender;
import me.iggy.economy.Economy;
import me.iggy.economy.utils.CC;
import org.bukkit.entity.Player;

public class EcoRemoveCommand {

    @Command(name = "remove", desc = "Eco Remove Command", async = false)
    @Require("economy.admin")
    public static void onCommand(@Sender Player sender, Player target, double amount) {

        Economy.getInstance().getEconomyHandler().removeMoney(target, amount, "Admin");
        sender.sendMessage(CC.translate("&aSuccessfully removed money from " + target.getName() + "'s account!"));

    }

}
