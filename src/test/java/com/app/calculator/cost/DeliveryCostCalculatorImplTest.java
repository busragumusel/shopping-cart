package com.app.calculator.cost;

import com.app.calculator.discount.CampaignDiscountCalculator;
import com.app.calculator.discount.CouponDiscountCalculator;
import com.app.entity.Category;
import com.app.entity.Product;
import com.app.entity.ShoppingCart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryCostCalculatorImplTest {

    private ShoppingCart shoppingCart;
    private DeliveryCostCalculatorImpl deliveryCostCalculator;
    private CampaignDiscountCalculator campaignDiscountCalculator;
    private CouponDiscountCalculator couponDiscountCalculator;

    @BeforeEach
    void setUp() {
        campaignDiscountCalculator = new CampaignDiscountCalculator();
        couponDiscountCalculator = new CouponDiscountCalculator();
        deliveryCostCalculator = new DeliveryCostCalculatorImpl(2,3,2.99);

        shoppingCart = new ShoppingCart(
                deliveryCostCalculator,
                campaignDiscountCalculator,
                couponDiscountCalculator
        );
    }

    @Test
    void test_CalculateFor_ShouldBe_Equal_IfOneProductOneCategory() {

        Category fashion = new Category("fashion");
        Product sneakers = new Product("sneakers", 6.5, fashion);
        shoppingCart.addItem(sneakers, 2);

        assertEquals(7.99, deliveryCostCalculator.calculateFor(shoppingCart));
    }

    @Test
    void test_CalculateFor_ShouldBe_Equal_IfThreeProductTwoCategory() {

        Category fashion = new Category("fashion");
        Category beauty = new Category("beauty");

        Product sneakers = new Product("sneakers", 6.5, fashion);
        Product sweatpants = new Product("sweatpants", 6.5, fashion);
        Product highlighter = new Product("highlighter", 6.5, beauty);

        shoppingCart.addItem(sneakers, 2);
        shoppingCart.addItem(sweatpants, 1);
        shoppingCart.addItem(highlighter, 3);

        assertEquals(15.99, deliveryCostCalculator.calculateFor(shoppingCart));
    }
}