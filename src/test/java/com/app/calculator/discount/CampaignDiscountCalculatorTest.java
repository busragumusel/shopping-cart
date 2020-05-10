package com.app.calculator.discount;

import com.app.calculator.cost.DeliveryCostCalculatorImpl;
import com.app.calculator.discount.type.DiscountType;
import com.app.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CampaignDiscountCalculatorTest {

    private Category food;
    private Category fashion;
    private Product burger;
    private Product shirt;
    private List<Campaign> campaigns = new ArrayList<Campaign>();
    private ShoppingCart shoppingCart;
    private CampaignDiscountCalculator campaignDiscountCalculator;
    private CouponDiscountCalculator couponDiscountCalculator;
    private DeliveryCostCalculatorImpl deliveryCostCalculator;

    @BeforeEach
    void setUp() {
        campaignDiscountCalculator = new CampaignDiscountCalculator();

        shoppingCart = new ShoppingCart(
                deliveryCostCalculator,
                campaignDiscountCalculator,
                couponDiscountCalculator
        );

        food = new Category("food");
        fashion = new Category("fashion");

        burger = new Product("burger", 10, food);
        shirt = new Product("shirt", 20, fashion);

        Campaign campaign1 = new Campaign(food, 1, 5, DiscountType.AMOUNT);
        Campaign campaign2 = new Campaign(food, 2, 10, DiscountType.AMOUNT);
        Campaign campaign3 = new Campaign(fashion, 1, 40, DiscountType.RATE);

        campaigns.add(campaign1);
        campaigns.add(campaign2);
        campaigns.add(campaign3);

        shoppingCart.setCampaigns(campaigns);
    }

    @Test
    void test_Apply_ShouldBe_Equal_IfOneProductFromOneCategory() {
        shoppingCart.addItem(burger,1);

        assertEquals(0, campaignDiscountCalculator.apply(shoppingCart));
    }

    @Test
    void test_Apply_ShouldBe_Equal_IfTwoProductFromOneCategory() {
        shoppingCart.addItem(burger,2);

        assertEquals(5, campaignDiscountCalculator.apply(shoppingCart));
    }

    @Test
    void test_Apply_ShouldBe_Equal_IfTreeProductFromOneCategory() {
        shoppingCart.addItem(burger,3);

        assertEquals(10, campaignDiscountCalculator.apply(shoppingCart));
    }

    @Test
    void test_Apply_ShouldBe_Equal_IfFiveProductFromTwoCategory() {
        shoppingCart.addItem(burger,3);
        shoppingCart.addItem(shirt,2);

        assertEquals(16, campaignDiscountCalculator.apply(shoppingCart));
    }
}