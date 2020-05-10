package com.app.calculator.cost;

import com.app.entity.ShoppingCart;

public class DeliveryCostCalculatorImpl implements DeliveryCostCalculator {
    public static final double FIXED_COST = 2.99;

    private final double costPerDelivery;
    private final double costPerProduct;
    private final double fixedCost;

    public DeliveryCostCalculatorImpl(double costPerDelivery, double costPerProduct, double fixedCost) {
        this.costPerDelivery = costPerDelivery;
        this.costPerProduct = costPerProduct;
        this.fixedCost = fixedCost;
    }

    @Override
    public double calculateFor(ShoppingCart cart) {
        return (costPerDelivery * cart.getNumberOfDeliveries())
                + (costPerProduct * cart.getShoppingCartItems().size())
                + fixedCost;
    }
}
