package me.iggy.economy.commands.admin.sub;

import com.jonahseguin.drink.annotation.Command;
import com.jonahseguin.drink.annotation.Require;
import com.jonahseguin.drink.annotation.Sender;
import me.iggy.economy.Economy;
import me.iggy.economy.utils.CC;
import org.bukkit.entity.Player;

public class EcoSetCommand {

    @Command(name = "set", desc = "Eco Set Command", async = false)
    @Require("economy.admin")
    public static void onCommand(@Sender Player sender, Player target, double amount) {

        Economy.getInstance().getEconomyHandler().setMoney(target, amount, "Admin");
        sender.sendMessage(CC.translate("&aSuccessfully added money to " + target.getName() + "'s account!"));

    }

}
