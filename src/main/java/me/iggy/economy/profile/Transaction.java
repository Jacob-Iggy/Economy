package me.iggy.economy.profile;

import lombok.Getter;

public class Transaction {

    @Getter final private double amount;
    @Getter final private String type;
    @Getter final private String reason;

    public Transaction(double amount, String type, String reason) {
        this.amount = amount;
        this.type = type;
        this.reason = reason;
    }


}
