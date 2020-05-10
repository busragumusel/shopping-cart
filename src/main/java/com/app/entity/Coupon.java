package com.app.entity;

import com.app.calculator.discount.type.DiscountType;

public class Coupon extends Discount {
    private double minPurchaseAmount;

    public Coupon(double minPurchaseAmount, double discountAmount, DiscountType discountType) {
        super(discountType, discountAmount);
        this.minPurchaseAmount = minPurchaseAmount;
    }

    public double getMinPurchaseAmount() {
        return minPurchaseAmount;
    }

    public void setMinPurchaseAmount(double minPurchaseAmount) {
        this.minPurchaseAmount = minPurchaseAmount;
    }
}
