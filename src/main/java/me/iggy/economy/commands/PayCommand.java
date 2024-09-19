package me.iggy.economy.commands;

import com.jonahseguin.drink.annotation.Command;
import com.jonahseguin.drink.annotation.Sender;
import me.iggy.economy.Economy;
import me.iggy.economy.utils.CC;
import org.bukkit.entity.Player;

public class PayCommand {

    @Command(name = "", desc = "Pay Command", async = false)
    public static void onCommand(@Sender Player sender, Player target, double amount) {

        if (Economy.getInstance().getEconomyHandler().getBalance(sender) < amount) {
            sender.sendMessage(CC.translate(Economy.getInstance().getConfig().getString("insufficient-funds-message")));
            return;
        }

        Economy.getInstance().getEconomyHandler().transferMoney(sender, target, amount);
        sender.sendMessage(CC.translate(Economy.getInstance().getConfig().getString("pay-messages.sender").replaceAll("%target%", target.getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));
        target.sendMessage(CC.translate(Economy.getInstance().getConfig().getString("pay-messages.target").replaceAll("%sender%", sender.getDisplayName()).replaceAll("%amount%", String.valueOf(amount))));

    }

}
