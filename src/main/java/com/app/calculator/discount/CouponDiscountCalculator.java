package com.app.calculator.discount;

import com.app.calculator.discount.type.TypePicker;
import com.app.calculator.discount.type.TypeCalculator;
import com.app.entity.Coupon;
import com.app.entity.ShoppingCart;

public class CouponDiscountCalculator implements DiscountCalculator {
    private TypePicker typePicker;

    public CouponDiscountCalculator() {
        this.typePicker = new TypePicker();
    }

    @Override
    public double apply(ShoppingCart cart) {
        Coupon coupon = cart.getCoupon();
        if (cart.getTotalAmountAfterDiscounts() >= coupon.getMinPurchaseAmount()) {
            TypeCalculator typeCalculator = this.typePicker.pick(coupon.getType());
            return typeCalculator.calculate(cart.getTotalAmountAfterDiscounts(), coupon.getAmount());
        }

        return 0;
    }
}
