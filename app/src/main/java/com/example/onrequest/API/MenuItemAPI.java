package com.example.onrequest.API;

public class MenuItemAPI {
    private String menuItemName;
    private int menuItemPrice;

    public MenuItemAPI(String menuItemName, int menuItemPrice) {
        this.menuItemName = menuItemName;
        this.menuItemPrice = menuItemPrice;
    }

    public String getMenuItemName() {
        return menuItemName;
    }

    public void setMenuItemName(String menuItemName) {
        this.menuItemName = menuItemName;
    }

    public int getMenuItemPrice() {
        return menuItemPrice;
    }

    public void setMenuItemQuantity(int menuItemQuantity) {
        this.menuItemPrice = menuItemQuantity;
    }
}
