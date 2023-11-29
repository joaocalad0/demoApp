package com.example.onrequest.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.onrequest.model.CartRepository;
import com.example.onrequest.schema.db.AppDatabase;
import com.example.onrequest.schema.entity.cart.Cart;
import com.example.onrequest.schema.entity.cart.CartMenuItem;
import com.example.onrequest.schema.entity.cart.CartWithMenuItems;

import java.util.List;

public class CartViewModel extends AndroidViewModel {
    //TODO

    private CartRepository cartRepository;

    public CartViewModel(@NonNull Application application){
        super(application);
        this.cartRepository = new CartRepository(AppDatabase.getInstance(application).getCartDao());
    }

    public LiveData<List<Cart>> getCart() {
        return cartRepository.getCart();
    }

    public LiveData<Cart> getById(long cartID) {
        return cartRepository.getById(cartID);
    }

    public LiveData<Cart> getOpenCartByTable(long tableId) {
        return cartRepository.getOpenCartByTable(tableId);
    }

    public LiveData<List<CartMenuItem>> getMenuItemByCart(long cartId) {
        return cartRepository.getMenuItemByCart(cartId);
    }

    public LiveData<List<CartWithMenuItems>> getCartWithMenuItemByCartId(long cartId) {
        return cartRepository.getCartWithMenuItemByCartId(cartId);
    }
}
