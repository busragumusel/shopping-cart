package com.app.calculator.discount.type;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TypePickerTest {

    private TypePicker typePicker;

    @BeforeEach
    void setUp() {
        typePicker = new TypePicker();
    }

    @Test
    void test_Calculate_ShouldBe_Return_AmountTypeCalculator_IfGivenTypeAmount() {
        assertTrue(typePicker.pick(DiscountType.AMOUNT) instanceof AmountTypeCalculator);
    }

    @Test
    void test_Calculate_ShouldBe_Return_RateTypeCalculator_IfGivenTypeRate() {
        assertTrue(typePicker.pick(DiscountType.RATE) instanceof RateTypeCalculator);
    }
}