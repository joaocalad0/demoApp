package com.example.onrequest.schema.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.onrequest.UserProfileMain;
import com.example.onrequest.schema.converters.Converters;
import com.example.onrequest.schema.dao.CartDao;
import com.example.onrequest.schema.dao.MenuItemDao;
import com.example.onrequest.schema.dao.MenuTableDao;
import com.example.onrequest.schema.dao.UserProfileDao;
import com.example.onrequest.schema.entity.UserProfile;
import com.example.onrequest.schema.entity.cart.Cart;
import com.example.onrequest.schema.entity.cart.CartMenuItem;
import com.example.onrequest.schema.entity.item.MenuItem;
import com.example.onrequest.schema.entity.table.MenuTable;

@Database(
        entities = {
                MenuItem.class,
                Cart.class,
                CartMenuItem.class,
                MenuTable.class,
                UserProfile.class

        },
        version = 1
)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract MenuItemDao getMenuItemDao();

    public abstract CartDao getCartDao();

    public abstract MenuTableDao getMenuTableDao();

    public abstract UserProfileDao getUserProfileDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {

        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "MenuEntracesDB")
                    .allowMainThreadQueries().
                    addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            db.execSQL("CREATE TABLE IF NOT EXISTS menuItem (" +
                                    "menuItemId INTEGER PRIMARY KEY AUTOINCREMENT," +
                                    "menuItemName TEXT," +
                                    "menuItemPrice REAL," +
                                    "menuItemAvatar TEXT," +
                                    "menuItemCategory TEXT," +
                                    "menuItemDesc TEXT," +
                                    "menuTableId INTEGER," +
                                    "logoUrl TEXT," +
                                    "FOREIGN KEY (menuTableId) REFERENCES menuTable(menuTableId) ON DELETE CASCADE)");

                            db.execSQL("INSERT INTO menuitem VALUES(1, 'Coca cola', 10.2,'https://mariapaparica.pt/wp-content/uploads/2016/09/coca-cola-classic.jpg', 'DRINK', 'Our Coca-Cola is served ice-cold, providing a satisfying fizz as you pop open the bottle or take a sip from a glass filled with ice. The moment you take your first sip, you ll experience the unmistakable taste that has made Coca-Cola a beloved classic.', 1)");
                            db.execSQL("INSERT INTO menuitem VALUES(2, 'Hambuguer Angus', 10.2,'https://files.menudino.com/cardapios/44081/368.jpg', 'FOOD', 'Our Angus burger is carefully crafted using select meat from one of the finest bovine breeds: Angus. This premium meat is known for its marbled texture and unparalleled juiciness, delivering an explosion of flavors with every bite.',1)");
                            db.execSQL("INSERT INTO menuItem VALUES(3, 'Pizza Peperoni', 8.5, 'https://i.pinimg.com/474x/a7/41/dc/a741dc4882308dba06a7a7d976d123eb.jpg', 'FOOD', 'Our pizza is carefully crafted using fresh and select ingredients, combined on a perfectly baked crust. With each bite, you ll experience the crispiness of the crust, providing a perfect foundation for the delicious flavors to come',1)");
                            db.execSQL("INSERT INTO menuItem VALUES(4, 'Pepsi', 1.2, 'https://www.eurohorecana.lt/image/cache/data/gerimai/GĖRIMAI.VAISVANDENIAI/PEPSI%20500-400x400.jpg', 'DRINK', 'Our  Pepsi is served ice-cold, presenting a moment of anticipation as you crack open a can or take a sip from a glass filled with ice. With the first taste, you ll experience the crisp and invigorating sensation that sets Pepsi apart.',2)");
                            db.execSQL("INSERT INTO menuitem VALUES(5, 'Taco Chilli', 6.8, 'https://assets.tmecosys.com/image/upload/t_web767x639/img/recipe/ras/Assets/7f17c36b-38db-4d74-8ed4-d3983957a293/Derivates/ab88505f-8375-4990-82fa-864f6bda6ce1.jpg', 'FOOD', 'a tantalizing fusion of savory and spicy flavors! This delectable creation features seasoned ground beef, smoky chili sauce, crisp lettuce, diced tomatoes, and shredded cheddar cheese, all nestled within a warm, soft taco shell.',3)");
                            db.execSQL("INSERT INTO menuitem VALUES(6, 'sparkling water', 1.2, 'https://prd360hyper-17e49.kxcdn.com/images/thumbs/6879060_agua-com-gas-pedras-salgadas-24x25cl_550.png', 'DRINK', 'Quench your thirst with our Sparkling Elegance, a crisp and effervescent refreshment that will tantalize your taste buds. Our sparkling water is meticulously crafted to provide the perfect balance of fizzy bubbles and pure hydration. ',1)");
                            //----------------Table IDs---------------------------------------------//
                            db.execSQL("INSERT INTO menuTable (menuTableId, logoUrl) VALUES (1,'https://brandslogos.com/wp-content/uploads/images/large/burger-king-logo.png')");
                            db.execSQL("INSERT INTO menuTable (menuTableId, logoUrl) VALUES (2, 'https://upload.wikimedia.org/wikipedia/commons/3/32/McDonald%27s_1968_logo.png')");
                            db.execSQL("INSERT INTO menuTable (menuTableId) VALUES (3)");
                            db.execSQL("INSERT INTO menuTable (menuTableId) VALUES (4)");
                            db.execSQL("INSERT INTO menuTable (menuTableId) VALUES (5)");
                            db.execSQL("INSERT INTO menuTable (menuTableId) VALUES (6)");
                        }
                    }).build();
        }
        return INSTANCE;
    }
}

