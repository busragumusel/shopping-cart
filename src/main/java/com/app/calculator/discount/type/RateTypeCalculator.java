package com.app.calculator.discount.type;

public class RateTypeCalculator implements TypeCalculator {

    @Override
    public double calculate(double totalAmount, double discountAmount) {
        return totalAmount * discountAmount / 100;
    }
}
