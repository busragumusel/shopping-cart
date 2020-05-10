package com.app.calculator.discount.type;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateTypeCalculatorTest {

    private RateTypeCalculator rateTypeCalculator;

    @BeforeEach
    void setUp() {
        rateTypeCalculator = new RateTypeCalculator();
    }

    @Test
    void test_Calculate_ShouldBe_Equal_DiscountAmount_Percentage_From_TotalAmount() {
        assertEquals(22.5, rateTypeCalculator.calculate(150, 15));
    }
}