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
    public static void onCommand(@Sender Player sender, Player target, String type, double amount) {

        if (!type.equalsIgnoreCase("wallet") && !type.equalsIgnoreCase("bank")) {
            sender.sendMessage(CC.translate("&cInvalid type!"));
            return;
        }
        
        if (type.equalsIgnoreCase("wallet")) {
            Economy.getInstance().getEconomyHandler().setMoney(target, amount, "Admin");
            sender.sendMessage(CC.translate("&aSuccessfully set balance of " + target.getName() + "'s wallet!"));
            return;
        }

        Economy.getInstance().getEconomyHandler().setBankMoney(target, amount, "Admin");
        sender.sendMessage(CC.translate("&aSuccessfully set balance of " + target.getName() + "'s bank account!"));

    }

}
