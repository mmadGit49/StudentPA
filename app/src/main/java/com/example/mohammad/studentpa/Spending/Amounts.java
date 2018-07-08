package com.example.mohammad.studentpa.Spending;

public class Amounts {
    private String amount;
    private  String details;

    public Amounts(String amount, String details) {
        this.amount = amount;
        this.details = details;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
