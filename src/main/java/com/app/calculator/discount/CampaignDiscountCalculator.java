package com.app.calculator.discount;

import com.app.calculator.discount.type.TypeCalculator;
import com.app.calculator.discount.type.TypePicker;
import com.app.entity.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class CampaignDiscountCalculator implements DiscountCalculator {
    private TypePicker typePicker;

    public CampaignDiscountCalculator() {
        this.typePicker = new TypePicker();
    }

    @Override
    public double apply(ShoppingCart cart) {
        HashMap<Double, Discount> applicableCampaignList = new HashMap<>();

        for (Campaign campaign : cart.getCampaigns()) {
            List<ShoppingCartItem> cartItemsByCategory = getCartItemsByCategory(
                    cart.getShoppingCartItems(), campaign.getCategory()
            );

            int cartItemCount = cartItemsByCategory.stream()
                    .mapToInt(ShoppingCartItem::getQuantity)
                    .sum();

            double totalAmountForCategory = getCartItemsTotalAmount(cartItemsByCategory);

            if (cartItemCount > campaign.getNumberOfProducts()) {
                TypeCalculator typeCalculator = this.typePicker.pick(campaign.getType());
                double campaignTotalDiscount = typeCalculator.calculate(
                        totalAmountForCategory,
                        campaign.getAmount()
                );
                applicableCampaignList.put(campaignTotalDiscount, campaign);
            }
        }
        return Collections.max(applicableCampaignList.keySet());
    }

    private List<ShoppingCartItem> getCartItemsByCategory(List<ShoppingCartItem> shoppingCartItems, Category category) {
        return shoppingCartItems
                .stream()
                .filter(i -> checkParent(i.getProduct().getCategory(), category))
                .collect(Collectors.toList());
    }

    private boolean checkParent(Category category, Category searchCategory) {
        if (category.equals(searchCategory)) {
            return true;
        } else if (category.getParent() != null) {
            return checkParent(category.getParent(), searchCategory);
        }

        return false;
    }

    private double getCartItemsTotalAmount(List<ShoppingCartItem> cartItems) {
        return cartItems
                .stream()
                .mapToDouble(i -> i.getQuantity() * i.getProduct().getPrice())
                .sum();
    }
}
