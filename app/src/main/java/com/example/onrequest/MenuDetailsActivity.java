package com.example.onrequest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.onrequest.manager.CartManager;
import com.example.onrequest.schema.dao.MenuItemDao;
import com.example.onrequest.schema.dao.MenuTableDao;
import com.example.onrequest.schema.db.AppDatabase;
import com.example.onrequest.schema.entity.item.MenuItem;
import com.example.onrequest.schema.entity.table.MenuTable;

public class MenuDetailsActivity extends AppCompatActivity {

    private static final String MENU_ITEM = "menuItem";
    private static final String MENU_TABLE = "menuTable";

    private DailyDiscount dailyDiscount;
    private MenuItemDao menuItemDao;
    //private MenuTable menuTable;
    private MenuTableDao tableDao;
    private TablesAdapter tablesAdapter;

    public static void startMenuDetailsActivity(Context context, MenuTable menuTable, MenuItem menuItem) {
        Intent intent = new Intent(context, MenuDetailsActivity.class);
        intent.putExtra(MenuDetailsActivity.MENU_ITEM, menuItem);
        intent.putExtra(MenuDetailsActivity.MENU_TABLE, menuTable);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onclick);
        menuItemDao = AppDatabase.getInstance(this).getMenuItemDao();
        tableDao = AppDatabase.getInstance(this).getMenuTableDao();
        this.tablesAdapter = new TablesAdapter(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            CartManager cartManager = CartManager.getInstance(this);
            MenuItem menuItem = bundle.getParcelable(MENU_ITEM);
            MenuTable menuTable = bundle.getParcelable(MENU_TABLE);
            TextView textViewDrink = findViewById(R.id.textView2);
            TextView descTextView = findViewById(R.id.descTextView);
            TextView priceOG = findViewById(R.id.PriceOG);

            //TextView do Preço formatada(double) com duas casa decimais %.2f
            TextView price = findViewById(R.id.ItemPrice);
            double menuItemPrice = menuItem.getMenuItemPrice();

            //Aplica o descontp
            DailyDiscount discount = new DailyDiscount(menuItemDao, 0.05, menuTable, tablesAdapter);
            double discountPrice = discount.calculateDiscountedPrice(menuItemPrice);
            //TextView do Preço formatada(double) com duas casa decimais %.2f

            String Currency = String.format("Now "+"$%.2f", discountPrice);
            price.setText(Currency);

            //Item ImageView
            ImageView imageViewAvatar = findViewById(R.id.imageView7);
            //Glide.with(this).load(menuItem.getMenuItemAvatar()).into(imageViewAvatar);

            if (menuItem.getMenuItemAvatar() != null){
                if (isDrawableResource(menuItem.getMenuItemAvatar().toString())){
                    int resourceId = getResources().getIdentifier(menuItem.getMenuItemAvatar().toString(), "drawable", getPackageName());
                    Glide.with(this).load(resourceId).into(imageViewAvatar);
                }
                else{
                    Glide.with(this).load(menuItem.getMenuItemAvatar()).into(imageViewAvatar);
                }
            }
            String originalPrice = Double.toString(menuItemPrice);
            priceOG.setText("Before " + "$" + originalPrice);

            descTextView.setText(menuItem.getMenuItemDesc());
            textViewDrink.setText(menuItem.getMenuItemName());
            View addButton = findViewById(R.id.button2);
            addButton.setOnClickListener(view -> { cartManager.addMenuItem(menuTable, menuItem);
                Toast.makeText(getApplicationContext(), String.format("Added: %s to table:%s cart!", menuItem.getMenuItemName(), menuTable.getMenuTableId()), Toast.LENGTH_SHORT).show();
            });
        } else {
            finish();
        }
    }

    private boolean isDrawableResource(String imageUrl){
        try {
            int resourceId = getResources().getIdentifier(imageUrl, "drawable", getPackageName());
            return resourceId != 0;
        }catch (Exception e){
            return false;
        }
    }

    private void applyDiscount(MenuTable menuTable, MenuItem menuItem){
        CartManager cartManager = CartManager.getInstance(this);
        cartManager.addMenuItem(menuTable, menuItem);
    }

}
