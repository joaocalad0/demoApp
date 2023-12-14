package com.example.onrequest;

public class Receipt {
    private long orderId;
    private String itemName;
    private int totalPrice;

    public Receipt(long orderId, String itemName, int totalPrice) {
        this.orderId = orderId;
        this.itemName = itemName;
        this.totalPrice = totalPrice;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
