package com.app.calculator.discount.type;

import java.util.HashMap;

public class TypePicker {
    private HashMap<DiscountType, TypeCalculator> discountTypeHashMap = new HashMap<>();

    public TypePicker() {
        discountTypeHashMap.put(DiscountType.RATE, new RateTypeCalculator());
        discountTypeHashMap.put(DiscountType.AMOUNT, new AmountTypeCalculator());
    }

    public TypeCalculator pick(DiscountType typeName) {
        return discountTypeHashMap.get(typeName);
    }
}
