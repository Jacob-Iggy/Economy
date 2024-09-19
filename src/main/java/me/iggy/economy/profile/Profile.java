package me.iggy.economy.profile;

import lombok.Data;
import lombok.NonNull;
import me.iggy.economy.Economy;
import me.iggy.economy.database.models.Entity;
import me.iggy.economy.database.models.EntityID;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Data
@Entity
public class Profile {

    @EntityID
    @NonNull
    private UUID uuid;

    private double balance = Economy.getInstance().getConfig().getDouble("starting-balance");
    private double bankBalance = 0.0;
    private ArrayList<Transaction> transactionHistory = new ArrayList<>();

    public static Optional<Profile> getProfile(UUID user) {
        return Economy.getInstance().getProfileHandler().getProfiles().load(user);
    }

    public void save() { Economy.getInstance().getProfileHandler().getProfiles().save(this); }

    public boolean inDatabase() { return getProfile(uuid).isPresent(); }

}
