package com.example.onrequest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onrequest.manager.CartManager;
import com.example.onrequest.schema.entity.cart.CartWithMenuItems;
import com.example.onrequest.schema.entity.item.MenuItem;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CartActivity extends AppCompatActivity {

    private static final String MENU_ITEM_CART = "menuItem";

    private CartManager cartManager;

    public static void startCartActivity(Context context, List<CartWithMenuItems> cartWithMenuItems) {
        Intent intent = new Intent(context, CartActivity.class);
        intent.putParcelableArrayListExtra(MENU_ITEM_CART, new ArrayList<>(cartWithMenuItems));
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        this.cartManager = CartManager.getInstance(this);
        Intent intent = getIntent();
        CartWithMenuItemsPresenter menuItemCounter = new CartWithMenuItemsPresenter(intent.getParcelableArrayListExtra(MENU_ITEM_CART));
        if (!menuItemCounter.cartWithMenuItems.isEmpty()) {
            List<MenuItemWithCounter> menuItemWithCounters = menuItemCounter.getContent();

            // Create the adapter to convert the array to views
            MenuItemWithCounterAdapter adapter = new MenuItemWithCounterAdapter(this, menuItemWithCounters);

            // Attach the adapter to a ListView
            ListView listView = (ListView) findViewById(R.id.cartListView);
            listView.setAdapter(adapter);

            double cartTotal = menuItemCounter.total();
            TextView textView12 = findViewById(R.id.textView12);
            textView12.setText("Total:" + cartTotal);

            Button checkout = findViewById(R.id.buttonCheckOut);
            checkout.setOnClickListener(view -> {
                if (!menuItemCounter.cartWithMenuItems.isEmpty()) {
                    cartManager.payCart(menuItemCounter.cartWithMenuItems.get(0).cart);
                }
                onBackPressed();
            });
        } else {
            finish();
        }
    }

    private static class CartWithMenuItemsPresenter {

        private final List<CartWithMenuItems> cartWithMenuItems;

        public CartWithMenuItemsPresenter(List<CartWithMenuItems> cartWithMenuItems) {
            this.cartWithMenuItems = cartWithMenuItems;
        }

        public List<MenuItemWithCounter> getContent() {
            List<MenuItem> menuItemsInCart = cartWithMenuItems.stream().map(it -> it.menuItem)
                    .distinct()
                    .collect(Collectors.toList());
            Map<MenuItem, Integer> counterMap = new HashMap<>(menuItemsInCart.size());
            this.cartWithMenuItems.forEach(cartWithMenuItems -> {
                Integer counter = counterMap.get(cartWithMenuItems.menuItem) != null ? (counterMap.get(cartWithMenuItems.menuItem) + 1) : 1;
                counterMap.put(cartWithMenuItems.menuItem, counter);
            });
            return counterMap.entrySet().stream().map(it -> new MenuItemWithCounter(it.getKey(), it.getValue())).collect(Collectors.toList());
        }

        public double total() {
            return cartWithMenuItems.stream().mapToDouble(it -> it.menuItem.getMenuItemPrice()).sum();
        }
    }

}