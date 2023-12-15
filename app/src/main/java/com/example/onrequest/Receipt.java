package com.example.onrequest;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Receipt {

    @PrimaryKey(autoGenerate = true)
    private long receiptId;
    private String itemName;
    private double totalPrice;

    public Receipt(long receiptId, String itemName, double totalPrice) {
        this.receiptId = receiptId;
        this.itemName = itemName;
        this.totalPrice = totalPrice;
    }

    public long getOrderId() {
        return receiptId;
    }

    public void setOrderId(long orderId) {
        this.receiptId = orderId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
