package com.example.onrequest.API;

import com.google.gson.annotations.SerializedName;

public class MenuItemAPI {
    private String menuItemName;
    private double menuItemPrice;

    @SerializedName("menuItemImage")
    private String imageURL;

    public MenuItemAPI(String menuItemName, int menuItemPrice, String imageURL) {
        this.menuItemName = menuItemName;
        this.menuItemPrice = menuItemPrice;
        this.imageURL = imageURL;
    }

    public String getMenuItemName() {
        return menuItemName;
    }

    public void setMenuItemName(String menuItemName) {
        this.menuItemName = menuItemName;
    }

    public double getMenuItemPrice() {
        return menuItemPrice;
    }

    public void setMenuItemQuantity(int menuItemQuantity) {
        this.menuItemPrice = menuItemQuantity;
    }

    public String getImageURL(){
        return imageURL;
    }
    public void setImageURL(String imageURL){
        this.imageURL = imageURL;
    }
}
