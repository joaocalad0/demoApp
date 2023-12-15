package com.example.onrequest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onrequest.manager.CartManager;
import com.example.onrequest.schema.dao.MenuItemDao;
import com.example.onrequest.schema.dao.MenuTableDao;
import com.example.onrequest.schema.db.AppDatabase;
import com.example.onrequest.schema.entity.cart.Cart;
import com.example.onrequest.schema.entity.cart.CartWithMenuItems;
import com.example.onrequest.schema.entity.item.MenuItem;
import com.example.onrequest.schema.entity.table.MenuTable;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CartActivity extends AppCompatActivity {

    private static final String MENU_ITEM_CART = "menuItem";

    private CartManager cartManager;

    private MenuItemDao menuItemDao;
    private MenuTable menuTable;
    private TablesAdapter tablesAdapter;
    private MenuTableDao tableDao;

    private ReceiptDao receiptDao;

    public static void startCartActivity(Context context, List<CartWithMenuItems> cartWithMenuItems) {
        Intent intent = new Intent(context, CartActivity.class);
        intent.putParcelableArrayListExtra(MENU_ITEM_CART, new ArrayList<>(cartWithMenuItems));
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        menuItemDao = AppDatabase.getInstance(this).getMenuItemDao();
        tableDao = AppDatabase.getInstance(this).getMenuTableDao();
        receiptDao = AppDatabase.getInstance(this).getReceiptDao();
        this.tablesAdapter = new TablesAdapter(this);


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
            double discountPercentage = 0.05;
            double discountedCartTotal = new DailyDiscount(menuItemDao, discountPercentage, menuTable, tablesAdapter)
                    .calculateDiscountedPrice(cartTotal);

            DecimalFormat decimalFormat = new DecimalFormat("#0.00");
            String formattedTotal = decimalFormat.format(discountedCartTotal);

            TextView textView12 = findViewById(R.id.textView12);
            textView12.setText("Total:" + formattedTotal);

            Button checkout = findViewById(R.id.buttonCheckOut);
            checkout.setOnClickListener(view -> {
                if (!menuItemCounter.cartWithMenuItems.isEmpty()) {
                    AppDatabase db = AppDatabase.getInstance(this);
                    ReceiptDao receiptDao = db.getReceiptDao();
                    Cart cart = menuItemCounter.cartWithMenuItems.get(0).cart;

                    //Tentativa de criar um "recibo" quando o utilizador efetua o pagamento
                    insertReceipts(cart, discountedCartTotal);

                    cartManager.payCart(menuItemCounter.cartWithMenuItems.get(0).cart);

                }
                onBackPressed();
            });
        } else {
            finish();
        }
    }

    private void insertReceipts(Cart cart, double discountedCartTotal) {
        List<MenuItem> menuItems = cart.getMenuItems();
        if (menuItems != null) {
            for (MenuItem menuItem : menuItems) {
                String itemName = menuItem.getMenuItemName();
                double totalPrice = discountedCartTotal;

                Receipt receipt = new Receipt();
                receipt.setItemName(itemName);
                receipt.setTotalPrice((int) totalPrice);
                receiptDao.insertReceipt(receipt);
            }
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
            return counterMap.entrySet().stream().map(it -> new MenuItemWithCounter(it.getKey(), it.getValue(), "")).collect(Collectors.toList());

        }

        public double total() {
            return cartWithMenuItems.stream().mapToDouble(it -> it.menuItem.getMenuItemPrice()).sum();
        }
    }

}