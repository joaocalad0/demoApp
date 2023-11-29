package com.example.onrequest.model;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.onrequest.schema.dao.CartDao;
import com.example.onrequest.schema.db.AppDatabase;
import com.example.onrequest.schema.entity.cart.Cart;
import com.example.onrequest.schema.entity.cart.CartMenuItem;
import com.example.onrequest.schema.entity.cart.CartWithMenuItems;

import java.util.List;

public class CartRepository {
    //TODO
    private CartDao cartDao;



    public CartRepository(CartDao cartDao){
        this.cartDao = cartDao;
    }

    public LiveData<List<Cart>> getCart(){
        return this.cartDao.getAll();
    }

    public LiveData<Cart>getById(long cartID){
        return this.cartDao.getById(cartID);
    }

    public LiveData<Cart> getOpenCartByTable(long tableId){
        return this.cartDao.getOpenCartByTable(tableId);
    }

    public LiveData<List<CartMenuItem>> getMenuItemByCart(long cartId){
        return this.cartDao.getMenuItemByCart(cartId);
    }

    public LiveData<List<CartWithMenuItems>> getCartWithMenuItemByCartId(long cartId){
        return this.cartDao.getCartWithMenuItemByCartId(cartId);
    }


}
