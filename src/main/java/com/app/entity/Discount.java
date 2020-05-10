package com.app.entity;

import com.app.calculator.discount.type.DiscountType;

public abstract class Discount {
    private DiscountType type;
    private double amount;

    public Discount(DiscountType type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public DiscountType getType() {
        return type;
    }

    public void setType(DiscountType type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
