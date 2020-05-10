package com.app.entity;

import com.app.calculator.cost.DeliveryCostCalculator;
import com.app.calculator.discount.CampaignDiscountCalculator;
import com.app.calculator.discount.CouponDiscountCalculator;

import java.util.*;

public class ShoppingCart {
    private List<ShoppingCartItem> shoppingCartItems = new ArrayList<ShoppingCartItem>();
    private Double totalAmount = 0.0;
    private Double totalAmountAfterDiscounts = 0.0;
    private Coupon coupon;
    private Double couponDiscount;
    private List<Campaign> campaigns = new ArrayList<Campaign>();
    private Double campaignDiscount;
    private DeliveryCostCalculator deliveryCostCalculator;
    private CampaignDiscountCalculator campaignDiscountCalculator;
    private CouponDiscountCalculator couponDiscountCalculator;

    public ShoppingCart(
            DeliveryCostCalculator deliveryCostCalculator,
            CampaignDiscountCalculator campaignDiscountCalculator,
            CouponDiscountCalculator couponDiscountCalculator
    ) {
        this.deliveryCostCalculator = deliveryCostCalculator;
        this.campaignDiscountCalculator = campaignDiscountCalculator;
        this.couponDiscountCalculator = couponDiscountCalculator;
    }

    public List<ShoppingCartItem> getShoppingCartItems() {
        return shoppingCartItems;
    }

    public void setShoppingCartItems(List<ShoppingCartItem> shoppingCartItems) {
        this.shoppingCartItems = shoppingCartItems;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getTotalAmountAfterDiscounts() {
        return totalAmountAfterDiscounts;
    }

    public void setTotalAmountAfterDiscounts(Double totalAmountAfterDiscounts) {
        this.totalAmountAfterDiscounts = totalAmountAfterDiscounts;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public Double getCouponDiscount() {
        return couponDiscount;
    }

    public void setCouponDiscount(Double couponDiscount) {
        this.couponDiscount = couponDiscount;
    }

    public List<Campaign> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(List<Campaign> campaigns) {
        this.campaigns = campaigns;
    }

    public Double getCampaignDiscount() {
        return campaignDiscount;
    }

    public void setCampaignDiscount(Double campaignDiscount) {
        this.campaignDiscount = campaignDiscount;
    }

    public void addItem(Product product, int quantity) {
        boolean isNew = true;
        for (ShoppingCartItem cartItem : this.shoppingCartItems) {
            if (cartItem.getProduct().equals(product)) {
                isNew = false;
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
            }
        }

        if (isNew) {
            ShoppingCartItem newCartItem = new ShoppingCartItem(product, quantity);
            this.shoppingCartItems.add(newCartItem);
        }

        this.setTotalAmount(this.getTotalAmount() + product.getPrice() * quantity);
    }

    public Double getDeliveryCost() {
        return this.deliveryCostCalculator.calculateFor(this);
    }

    public int getNumberOfDeliveries() {
        HashSet<Category> categoryHashSet = new HashSet<>();
        this.getShoppingCartItems().forEach(cartItem -> {
            categoryHashSet.add(cartItem.getProduct().getCategory());
        });

        return categoryHashSet.size();
    }

    public void applyDiscounts(Campaign... discounts) {
        this.campaigns.addAll(Arrays.asList(discounts));
        this.setCampaigns(this.campaigns);
        this.setCampaignDiscount(campaignDiscountCalculator.apply(this));
        this.setTotalAmountAfterDiscounts(this.totalAmount - this.campaignDiscount);
    }

    public void applyCoupon(Coupon coupon) {
        this.setCoupon(coupon);
        this.setCouponDiscount(couponDiscountCalculator.apply(this));
        this.setTotalAmountAfterDiscounts(this.totalAmountAfterDiscounts - this.couponDiscount);
    }

    public HashMap<Category, List<ShoppingCartItem>> getCategorizedCartItemList() {
        HashMap<Category, List<ShoppingCartItem>> shoppingCartItems = new HashMap<>();

        for (ShoppingCartItem cartItem : this.getShoppingCartItems()) {
            Category category = cartItem.getProduct().getCategory();

            if (!shoppingCartItems.containsKey(category)) {
                shoppingCartItems.put(category, new ArrayList<>());
            }

            shoppingCartItems.get(category).add(cartItem);
        }

        return shoppingCartItems;
    }

    public void print(){
        HashMap<Category, List<ShoppingCartItem>> shoppingCartItems = this.getCategorizedCartItemList();

        shoppingCartItems.forEach((category, cartItemList) -> {
            System.out.println("\n---**--- Category: " + category.getTitle() + " ---**---");

            for (ShoppingCartItem cartItem: cartItemList) {
                System.out.println("Product: " + cartItem.getProduct().getTitle());
                System.out.println("Price: " + cartItem.getProduct().getPrice());
                System.out.println("Quantity: " + cartItem.getQuantity());
                System.out.println("Total Price: " + cartItem.getQuantity() * cartItem.getProduct().getPrice());
            }
        });

        System.out.println("\n\nTotal Amount: " + this.getTotalAmount());
        System.out.println("Applied Campaign Amount: " + campaignDiscount);
        System.out.println("Applied Coupon Amount: " + couponDiscount);
        System.out.println("Total Discount Amount: " + (couponDiscount +  campaignDiscount));
        System.out.println("Total Amount After Discounts: " + this.getTotalAmountAfterDiscounts());
        System.out.println("Delivery Cost: " + this.getDeliveryCost());
    }
}
