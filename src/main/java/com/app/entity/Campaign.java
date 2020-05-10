package com.app.entity;

import com.app.calculator.discount.type.DiscountType;

public class Campaign extends Discount {
    private Category category;
    private Double discount;
    private int numberOfProducts;

    public Campaign(Category category, int numberOfProducts, double discountAmount, DiscountType discountType) {
        super(discountType, discountAmount);
        this.category = category;
        this.numberOfProducts = numberOfProducts;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public int getNumberOfProducts() {
        return numberOfProducts;
    }

    public void setNumberOfProducts(int numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
    }
}
