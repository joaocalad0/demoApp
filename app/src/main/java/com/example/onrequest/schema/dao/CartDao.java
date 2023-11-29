package com.example.onrequest.schema.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.onrequest.schema.entity.cart.Cart;
import com.example.onrequest.schema.entity.cart.CartMenuItem;
import com.example.onrequest.schema.entity.cart.CartWithMenuItems;

import java.util.List;

@Dao
public interface CartDao {

    @Query("SELECT * FROM cart")
    LiveData<List<Cart>> getAll();

    @Query("SELECT * FROM cart where cartId = :cartId")
    LiveData<Cart> getById(long cartId);

    @Query("SELECT * FROM cart c INNER JOIN MENUTABLE m ON c.menuTableId = m.menuTableId WHERE m.menuTableId = :tableId AND c.cartState = 'OPEN'")
    LiveData<Cart> getOpenCartByTable(long tableId);

    @Query("SELECT * FROM cartmenuitem c where c.cartId = :cartId")
    LiveData<List<CartMenuItem>> getMenuItemByCart(long cartId);

    @Transaction
    @Query("SELECT * FROM cartmenuitem c where c.cartId = :cartId")
    LiveData<List<CartWithMenuItems>> getCartWithMenuItemByCartId(long cartId);

    @Insert
    long insert(Cart cart);

    @Insert
    long insert(CartMenuItem cartMenuItem);

    @Update
    void update(Cart cart);

    @Update
    void update(CartMenuItem cartMenuItem);

    @Delete
    void delete(Cart cart);

    @Delete
    void delete(CartMenuItem cartMenuItem);
}
