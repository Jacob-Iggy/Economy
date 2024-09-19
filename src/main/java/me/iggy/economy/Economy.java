package me.iggy.economy;

import com.jonahseguin.drink.CommandService;
import com.jonahseguin.drink.Drink;
import lombok.Getter;
import me.iggy.economy.commands.BalanceCommand;
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
        drink.registerCommands();
    }

}
