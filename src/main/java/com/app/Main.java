package com.app;

import com.app.calculator.cost.DeliveryCostCalculator;
import com.app.calculator.cost.DeliveryCostCalculatorImpl;
import com.app.calculator.discount.CampaignDiscountCalculator;
import com.app.calculator.discount.CouponDiscountCalculator;
import com.app.calculator.discount.type.DiscountType;
import com.app.entity.*;

public class Main {
    public static void main(String[] args) {
        CampaignDiscountCalculator campaignDiscountCalculator = new CampaignDiscountCalculator();
        CouponDiscountCalculator couponDiscountCalculator = new CouponDiscountCalculator();
        DeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculatorImpl(
                12,
                2,
                DeliveryCostCalculatorImpl.FIXED_COST
        );

        ShoppingCart shoppingCart = new ShoppingCart(
                deliveryCostCalculator,
                campaignDiscountCalculator,
                couponDiscountCalculator
        );

        Category fashion = new Category("Fashion");
        Category beauty = new Category("Beauty");

        Category balenciaga = new Category("Balenciaga", fashion);
        Category lesBenjamins = new Category("Les Benjamins", fashion);
        Category mac = new Category("Mac", beauty);

        Product sneakers = new Product("Sneakers", 60, balenciaga);
        Product sweatpants = new Product("Sweatpants", 89.90, lesBenjamins);
        Product highlighter = new Product("Highlighter", 120, mac);

        shoppingCart.addItem(sneakers, 2);
        shoppingCart.addItem(sweatpants, 1);
        shoppingCart.addItem(sneakers, 3);
        shoppingCart.addItem(highlighter, 2);
        shoppingCart.addItem(sweatpants, 3);
        shoppingCart.addItem(highlighter, 6);

        Coupon coupon = new Coupon(100, 10, DiscountType.RATE);

        Campaign fashionCampaign = new Campaign(fashion, 3, 20, DiscountType.RATE);
        Campaign beautyCampaign = new Campaign(beauty, 5, 30, DiscountType.AMOUNT);
        Campaign lesBenjaminsCampaign = new Campaign(lesBenjamins, 2, 50, DiscountType.AMOUNT);

        shoppingCart.applyDiscounts(fashionCampaign, beautyCampaign, lesBenjaminsCampaign);
        shoppingCart.applyCoupon(coupon);

        shoppingCart.print();
    }
}
