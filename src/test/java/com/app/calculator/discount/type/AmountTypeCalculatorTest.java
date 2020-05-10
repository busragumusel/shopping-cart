package com.app.calculator.discount.type;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AmountTypeCalculatorTest {

    private AmountTypeCalculator amountTypeCalculator;

    @BeforeEach
    void setUp() {
        amountTypeCalculator = new AmountTypeCalculator();
    }

    @Test
    void test_Calculate_ShouldBe_Equal_DiscountAmount() {
        assertEquals(15.7, amountTypeCalculator.calculate(22.2, 15.7));
    }
}