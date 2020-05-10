package com.app.calculator.discount;

import com.app.calculator.cost.DeliveryCostCalculatorImpl;
import com.app.calculator.discount.type.DiscountType;
import com.app.entity.Category;
import com.app.entity.Coupon;
import com.app.entity.Product;
import com.app.entity.ShoppingCart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CouponDiscountCalculatorTest {

    private Coupon coupon;
    private ShoppingCart shoppingCart;
    private CouponDiscountCalculator couponDiscountCalculator;
    private DeliveryCostCalculatorImpl deliveryCostCalculator;
    private CampaignDiscountCalculator campaignDiscountCalculator;

    @BeforeEach
    void setUp() {
        couponDiscountCalculator = new CouponDiscountCalculator();

        shoppingCart = new ShoppingCart(
                deliveryCostCalculator,
                campaignDiscountCalculator,
                couponDiscountCalculator
        );
        coupon = new Coupon(100, 20, DiscountType.AMOUNT);
    }

    @Test
    void test_Apply_ShouldBe_0_IfMinPurchaseAmountGreaterThanTotalAmountAfterDiscounts() {
        shoppingCart.setTotalAmountAfterDiscounts(80);
        shoppingCart.setCoupon(coupon);

        assertEquals(0, couponDiscountCalculator.apply(shoppingCart));
    }

    @Test
    void test_Apply_ShouldBe_Apply_Coupon_Discount_IfTotalAmountAfterDiscountsEqualToMinPurchaseAmount() {
        shoppingCart.setTotalAmountAfterDiscounts(100);
        shoppingCart.setCoupon(coupon);

        assertEquals(20, couponDiscountCalculator.apply(shoppingCart));
    }

    @Test
    void test_Apply_ShouldBe_Apply_Coupon_Discount_IfTotalAmountAfterDiscountsGreaterThanMinPurchaseAmount() {
        shoppingCart.setTotalAmountAfterDiscounts(120);
        shoppingCart.setCoupon(coupon);

        assertEquals(20, couponDiscountCalculator.apply(shoppingCart));
    }
}