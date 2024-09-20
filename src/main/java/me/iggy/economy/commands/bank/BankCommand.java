package me.iggy.economy.commands.bank;

import com.jonahseguin.drink.annotation.Command;
import com.jonahseguin.drink.annotation.Sender;
import me.iggy.economy.Economy;
import me.iggy.economy.utils.CC;
import org.bukkit.entity.Player;

public class BankCommand {

    @Command(name = "", desc = "Bank Command", async = false)
    public static void onCommand(@Sender Player sender) {
        Economy.getInstance().getConfig().getStringList("bank-help-message").forEach(msg -> sender.sendMessage(CC.translate(msg)));
    }

}
