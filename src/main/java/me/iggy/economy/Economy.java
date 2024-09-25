package me.iggy.economy;

import com.jonahseguin.drink.CommandService;
import com.jonahseguin.drink.Drink;
import lombok.Getter;
import me.iggy.economy.commands.BalanceCommand;
import me.iggy.economy.commands.PayCommand;
import me.iggy.economy.commands.WithdrawCommand;
import me.iggy.economy.commands.admin.EcoCommand;
import me.iggy.economy.commands.admin.sub.EcoAddCommand;
import me.iggy.economy.commands.admin.sub.EcoRemoveCommand;
import me.iggy.economy.commands.admin.sub.EcoSetCommand;
import me.iggy.economy.commands.bank.BankCommand;
import me.iggy.economy.commands.bank.sub.BankDepositCommand;
import me.iggy.economy.commands.bank.sub.BankWithdrawCommand;
import me.iggy.economy.database.DatabaseHandler;
import me.iggy.economy.profile.ProfileHandler;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class Economy extends JavaPlugin {

    @Getter
    private static Economy instance;

    CommandService drink;

    private EconomyHandler economyHandler;
    private ProfileHandler profileHandler;
    private DatabaseHandler databaseHandler;

    @Override
    public void onEnable() {

        instance = this;
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        loadHandlers();
        registerCommands();
    }

    @Override
    public void onDisable() {
        saveConfig();
        instance = null;
        databaseHandler.getMongoClient().close();
    }

    private void loadHandlers() {
        economyHandler = new EconomyHandler();
        profileHandler = new ProfileHandler();
        databaseHandler = new DatabaseHandler();
    }

    private void registerCommands() {
        drink = Drink.get(this);
        drink.register(new BalanceCommand(), "balance", "bal");
        drink.register(new PayCommand(), "pay");
        drink.register(new WithdrawCommand(), "withdraw");
        drink.register(new BankCommand(), "bank")
                .registerSub(new BankDepositCommand())
                .registerSub(new BankWithdrawCommand());
        drink.register(new EcoCommand(), "eco")
                .registerSub(new EcoAddCommand())
                .registerSub(new EcoRemoveCommand())
                .registerSub(new EcoSetCommand());
        drink.registerCommands();
    }

}
