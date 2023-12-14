package com.example.onrequest;

import android.icu.text.DecimalFormat;

import com.example.onrequest.schema.entity.item.MenuItem;

public class MenuItemWithCounter {

    private static DecimalFormat REAL_FORMATTER = new DecimalFormat("0.##");

    private final MenuItem menuItem;

    private final int counter;

    private String menuItemImage;


    public MenuItemWithCounter(MenuItem menuItem, int counter, String menuItemImage) {
        this.menuItem = menuItem;
        this.counter = counter;
        this.menuItemImage = menuItemImage;
    }

    public String getMenuItemImage(){
        return menuItemImage;
    }
    public int getCounter() {
        return counter;
    }

    public String getMenuItemName() {
        return menuItem.getMenuItemName();
    }

    public double getPrice() {
        return menuItem.getMenuItemPrice() * counter;
    }

    public String getFormattedPrice() {
        double price = getPrice();
        return REAL_FORMATTER.format(price);
    }

    public double getDiscountedPrice(double discountPercentage) {
        double originalPrice = getPrice();
        return originalPrice - (originalPrice * discountPercentage);
    }

    public String getFormattedPrice(double discountPercentage) {
        double discountedPrice = getDiscountedPrice(discountPercentage);
        return REAL_FORMATTER.format(discountedPrice);
    }
}
