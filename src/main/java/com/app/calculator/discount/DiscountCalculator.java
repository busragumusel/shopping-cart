package com.app.calculator.discount;

import com.app.entity.ShoppingCart;

public interface DiscountCalculator {

    double apply(ShoppingCart cart);
}
