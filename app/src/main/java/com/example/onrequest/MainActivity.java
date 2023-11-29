package com.example.onrequest;

import static com.example.onrequest.schema.entity.item.MenuItemCategory.DRINK;
import static com.example.onrequest.schema.entity.item.MenuItemCategory.FOOD;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.onrequest.manager.CartManager;
import com.example.onrequest.schema.dao.MenuItemDao;
import com.example.onrequest.schema.db.AppDatabase;
import com.example.onrequest.schema.entity.cart.CartWithMenuItems;
import com.example.onrequest.schema.entity.item.MenuItem;
import com.example.onrequest.schema.entity.table.MenuTable;
import com.example.onrequest.viewmodel.MenuItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppDatabase appDatabase;
    private CartManager cartManager;
    private MenuAdapter adapter;
    private MenuTable menuTable;
    private MenuItemViewModel viewModel;
    public static void startMainActivity(MenuTable menuTable, Context context) {
        Intent menuIntent = new Intent(context, MainActivity.class);
        menuIntent.putExtra("table", menuTable);
        context.startActivity(menuIntent);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.appDatabase = AppDatabase.getInstance(this);
        this.cartManager = CartManager.getInstance(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        this.menuTable = bundle.getParcelable("table");

        ImageView imageViewLogo = findViewById(R.id.imageViewLogo);
        Glide.with(this).load(menuTable.getLogoUrl()).into(imageViewLogo);

        // obter uma referência para a RecyclerView que existe no layout da MainActivity4
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        //MenuItemDao menuItemDao = appDatabase.getMenuItemDao();
        //LiveData<List<MenuItem>> itemForTableLiveData = menuItemDao.getByTableId(menuTable.getMenuTableId());
        //itemForTableLiveData.observe(this, menuItems -> {
        //if (menuItems != null) {
                // Crie uma variável local para o adapter
        //MenuAdapter adapter = new MenuAdapter(menuTable, menuItems);

                // Defina o adapter e o layout manager na RecyclerView
        //recyclerView.setAdapter(adapter);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //}
        // });

        //List<MenuItem> itemsForTableAndCategory = menuItemDao.getByCategory(FOOD, DRINK, menuTable.getMenuTableId());
        //LiveData<List<MenuItem>> all = menuItemDao.getAll();
        //List<MenuItem> food = menuItemDao.getByCategory(FOOD);
        //List<MenuItem> drink = menuItemDao.getByCategory(DRINK);



        // criar um objeto do tipo MenuAdapter (que extende Adapter)
        //MenuAdapter adapter = new MenuAdapter(menuTable, (List<MenuItem>) itemForTable);

        // criar um objecto do tipo LinearLayoutManager para ser utilizado na RecyclerView
        // o LinearLayoutManager tem como orientação default a orientação Vertical

        //adapter
        if (adapter == null){
            adapter = new MenuAdapter(menuTable, new ArrayList<>());
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        // Definir que a RecyclerView utiliza como Adapter o objeto que criámos anteriormente
        recyclerView.setAdapter(adapter);

        // Definir que a RecyclerView utiliza como LayoutManager o objeto que criámos anteriormente
        recyclerView.setLayoutManager(layoutManager);

        //ModelView
        viewModel = new ViewModelProvider(this).get(MenuItemViewModel.class);
        long tableId = menuTable.getMenuTableId();
        viewModel.getMenuItemsByTableId(tableId).observe(this, menuItemList -> {
            adapter.refresh(menuItemList);
        });





        Button buttonAll = findViewById(R.id.buttonAll);
        //buttonAll.setOnClickListener(buttonOnClick(adapter, all));

        Button buttonFood = findViewById(R.id.buttonFood);
        //buttonFood.setOnClickListener(buttonOnClick(adapter, food));

        Button buttonDrink = findViewById(R.id.buttonDrink);
        //buttonDrink.setOnClickListener(buttonOnClick(adapter, drink));

        Button buttonPay = findViewById(R.id.buttonPay);
        buttonPay.setEnabled(canPay());
        buttonPay.setOnClickListener(view -> {
            List<CartWithMenuItems> cartWithMenuItemsByTable = cartManager.getCartWithMenuItemsByTable(menuTable);
            CartActivity.startCartActivity(this, cartWithMenuItemsByTable);
        });

    }


    private View.OnClickListener buttonOnClick(MenuAdapter menuAdapter, LiveData<List<MenuItem>> menuItemsLiveData) {
        return view -> {
            List<MenuItem> menuItems = menuItemsLiveData.getValue();
            if (menuItems != null) {
                menuAdapter.refresh(menuItems);
            }
        };
    }

    private boolean canPay() {
        return cartManager.isOpenCart(menuTable);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Button buttonPay = findViewById(R.id.buttonPay);
        buttonPay.setEnabled(canPay());
    }

    public void imageURL(ImageView imageView) {
        Glide.with(this).load(menuTable.getLogoUrl()).into(imageView);
    }


}