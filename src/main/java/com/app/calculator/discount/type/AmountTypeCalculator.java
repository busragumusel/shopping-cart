package com.app.calculator.discount.type;

public class AmountTypeCalculator implements TypeCalculator {

    @Override
    public double calculate(double totalAmount, double discountAmount) {
        return discountAmount;
    }
}
