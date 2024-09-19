package me.iggy.economy.commands;

import com.jonahseguin.drink.annotation.Command;
import com.jonahseguin.drink.annotation.Sender;
import me.iggy.economy.Economy;
import me.iggy.economy.utils.CC;
import org.bukkit.entity.Player;

public class BalanceCommand {

    @Command(name = "", desc = "Balance Command", async = false)
    public static void onCommand(@Sender Player sender) {
        sender.sendMessage(CC.translate(Economy.getInstance().getConfig().getString("balance-message").replaceAll("%amount%", String.valueOf(Economy.getInstance().getEconomyHandler().getBalance(sender)))));
    }

}
