package me.iggy.economy.profile;

import me.iggy.economy.Economy;
import me.iggy.economy.database.collection.WrappedCollection;
import me.iggy.economy.listeners.JoinListener;
import me.iggy.economy.listeners.MoneyNoteListener;

public class ProfileHandler {

    private Economy instance = Economy.getInstance();

    public ProfileHandler() {
        instance.getServer().getPluginManager().registerEvents(new JoinListener(), instance);
        instance.getServer().getPluginManager().registerEvents(new MoneyNoteListener(), instance);
        instance.getServer().getPluginManager().registerEvents(new TransactionMenu(), instance);
    }

    public WrappedCollection<Profile> getProfiles() {
        return Economy.getInstance().getDatabaseHandler().createCollection("profiles", Profile.class);
    }

}
