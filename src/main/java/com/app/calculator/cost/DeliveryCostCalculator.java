package com.app.calculator.cost;

import com.app.entity.ShoppingCart;

public interface DeliveryCostCalculator {
    double calculateFor(ShoppingCart cart);
}
