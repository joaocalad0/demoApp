package com.example.onrequest;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ReceiptDao {
    @Query("SELECT * FROM Receipt")
    LiveData<List<Receipt>> getAllReceipts();

    @Insert
    void insertReceipt(Receipt receipt);
}