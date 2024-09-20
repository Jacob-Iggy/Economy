package me.iggy.economy.commands;

import com.jonahseguin.drink.annotation.Command;
import com.jonahseguin.drink.annotation.Sender;
import me.iggy.economy.Economy;
import me.iggy.economy.utils.CC;
import org.bukkit.entity.Player;

public class BalanceCommand {

    @Command(name = "", desc = "Balance Command", async = false)
    public static void onCommand(@Sender Player sender) {
        Economy.getInstance().getConfig().getStringList("balance-message").forEach(msg -> {
            sender.sendMessage(CC.translate(msg
                    .replaceAll("%bal%", String.valueOf(Economy.getInstance().getEconomyHandler().getBalance(sender)))
                    .replaceAll("%bank_bal%", String.valueOf(Economy.getInstance().getEconomyHandler().getBankBalance(sender)))));
        });
    }

}
