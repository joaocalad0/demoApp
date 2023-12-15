package com.example.onrequest.schema.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.onrequest.Receipt;
import com.example.onrequest.ReceiptDao;
import com.example.onrequest.User;
import com.example.onrequest.UserDao;
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
                UserProfile.class,
                User.class,
                Receipt.class,

        },
        version = 1
)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract MenuItemDao getMenuItemDao();

    public abstract CartDao getCartDao();

    public abstract MenuTableDao getMenuTableDao();

    public abstract UserProfileDao getUserProfileDao();

    public abstract UserDao getUserDao();

    public abstract ReceiptDao getReceiptDao();

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




                            //----------------McDonalds(2)---------------------------------------------//
                            db.execSQL("INSERT INTO menuitem VALUES(1, 'Coca cola', 1,'https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/ec545603-cf4e-48e0-936d-5376ea12fdc0/dfk5nsl-e81fa33d-3059-4edb-aa66-6bdbb51d3ef8.png/v1/fill/w_901,h_810/mcdonald_s_coca_cola_png_2022_by_wcwjunkbox_dfk5nsl-fullview.png?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7ImhlaWdodCI6Ijw9ODEwIiwicGF0aCI6IlwvZlwvZWM1NDU2MDMtY2Y0ZS00OGUwLTkzNmQtNTM3NmVhMTJmZGMwXC9kZms1bnNsLWU4MWZhMzNkLTMwNTktNGVkYi1hYTY2LTZiZGJiNTFkM2VmOC5wbmciLCJ3aWR0aCI6Ijw9OTAxIn1dXSwiYXVkIjpbInVybjpzZXJ2aWNlOmltYWdlLm9wZXJhdGlvbnMiXX0.74MfK3N807O836_5Om22uN1pLRRMXoN_xshVDbNwvLY', 'DRINK', 'Uma Coca-Cola bem fresca faz-nos gozar cada momento das nossas vidas de uma forma especial. Graças ao seu sabor único e ao seu carácter refrescante e autêntico', 2, 0.0)");
                            db.execSQL("INSERT INTO menuitem VALUES(7, 'Big Mac', 5.6, 'https://www.mcdonalds.pt/media/7040/produtos_500x500_bestburgers_big-mac.png?anchor=center&mode=crop&width=210&height=210&rnd=132981257380000000', 'FOOD', 'A sanduíche dupla mais cobiçada no mundo inteiro. Feita com dois suculentos hambúrgueres 100% carne de vaca, queijo fundido, pepino, cebola, alface e um molho irresistível. Uma combinação única. ',2, 0.0)");
                            db.execSQL("INSERT INTO menuitem VALUES(8, 'Batata Frita', 1, 'https://www.mcdonalds.pt/media/7534/batatasmedias_500x500.png', 'FOOD', 'São uma perdição. Pode tentar resistir-lhes, mas depois alguém traz para a mesa umas batatas fritas longas, loiras, estaladiças é impossível não provar uma. Ou duas. Três, se a outra pessoa não estiver a olhar',2, 0.0)");
                            db.execSQL("INSERT INTO menuitem VALUES(9, 'Happy Meal', 5,'https://www.pngall.com/wp-content/uploads/14/Happy-Meal-PNG-Photo.png', 'FOOD', 'O eterno favorito dos mais pequenos.', 2, 0.0)");

                            //----------------Burguer King(1)---------------------------------------------//
                            db.execSQL("INSERT INTO menuitem VALUES(2, 'Double Bacon', 5.6, 'https://cdn.sanity.io/images/czqk28jt/prod_bk_pt/1d2904a14e8bd85f89689733a1dce63eed2f66a3-1333x1333.png?w=750&q=40&fit=max&auto=format', 'FOOD', 'Duplo bacon, sabor a dobrar.',1, 0.0)");
                            db.execSQL("INSERT INTO menuItem VALUES(3, 'Steakhouse', 8.5, 'https://cdn.sanity.io/images/czqk28jt/prod_bk_pt/2c1666a1ff25596bf17a5d2019c853c1d0ea8465-1333x1333.png?w=750&q=40&fit=max&auto=format', 'FOOD', 'Feita com carne feita na grelha',1, 0.0)");
                            db.execSQL("INSERT INTO menuItem VALUES(4, 'Pepsi', 1.2, 'https://i.pinimg.com/originals/3d/c5/4e/3dc54e8f5df2eaa53ca93758d080b2f0.png', 'DRINK', 'Uma Pepsi bem fresca faz-nos gozar cada momento das nossas vidas de uma forma especial',1, 0.0)");
                            db.execSQL("INSERT INTO menuitem VALUES(6, 'King Jr', 5, 'https://cdn.sanity.io/images/czqk28jt/prod_bk_pt/a79b13ac8af93da4347b9fad9cecad2e1100fa11-1333x1333.png?w=750&q=40&fit=max&auto=format', 'DRINK', 'O eterno favorito dos mais pequenos ',1, 0.0)");

                            //----------------Pizzaria Millano(3)---------------------------------------------//
                            db.execSQL("INSERT INTO menuitem VALUES(5, 'Sopa do Dia', 2.6, 'https://www.comidas.pt/upload/media/cache/3febf4adfb109dd3e4d15d6221d7299a_shop_thumbnail.jpg', 'FOOD', 'Delicie-se com nosso cremoso Creme de Abóbora, realçado com o toque picante do gengibre. Uma explosão de sabores sazonais para começar sua refeição com conforto e sofisticação.',3, 0.0)");
                            db.execSQL("INSERT INTO menuitem VALUES(10, 'Pão de Alho', 5,'https://www.comidas.pt/upload/media/cache/cd0a503e1821ac0eff0557e49fcb3300_shop_thumbnail.jpg', 'FOOD', ' Pão de Alho é a combinação perfeita de pão macio e alho dourado. Crocante por fora e macio por dentro, enquanto a generosa camada de manteiga de alho derrete na boca', 3, 0.0)");
                            db.execSQL("INSERT INTO menuitem VALUES(11, 'Pizza Hawai', 12.5,'https://www.comidas.pt/upload/media/cache/bf46eaab8258ed2d3526eef0713cd22a_shop_thumbnail.jpg', 'FOOD', ' uma fusão deliciosa de sabores tropicais, o equilibrio perfeito entre o douce do ananás com a salinidade do fiambre', 3, 0.0)");
                            db.execSQL("INSERT INTO menuitem VALUES(12, 'Terra e Mar', 20,'https://www.comidas.pt/upload/media/cache/1e14a7c7a1acc03c12c0359d0d95df32_shop_thumbnail.jpeg', 'FOOD', ' Pão de Alho é a combinação perfeita de pão macio e alho dourado. Crocante por fora e macio por dentro, enquanto a generosa camada de manteiga de alho derrete na boca', 3, 0.0)");

                            //----------------Bushido(4)---------------------------------------------//
                            db.execSQL("INSERT INTO menuitem VALUES(13, 'Massa c/Gambas ', 8.40,'https://www.comidas.pt/upload/media/cache/c1d0a569c3b30afd1c07f2980184ffd2_shop_thumbnail.jpg', 'FOOD', ' Delicie-se com nossa Massa com Gambas, onde o sabor suculento das gambas mistura-se perfeitamente com uma massa al dente, tudo envolvido num molho aromático e leve.', 4, 0.0)");
                            db.execSQL("INSERT INTO menuitem VALUES(14, 'Sashimi Salmão ', 8.40,'https://www.comidas.pt/upload/media/cache/c3b654d6e541bca81da1320a37f01492_shop_thumbnail.jpg', 'FOOD', ' Experimente a frescura incomparável do Sashimi de Salmão, fatias finas de salmão cru, uma experiência autêntica para os amantes de sushi.', 4, 0.0)");
                            db.execSQL("INSERT INTO menuitem VALUES(15, 'Frango Panado', 8.40,'https://www.comidas.pt/upload/media/cache/f18d2ea96efce0ceeb5edbcb6be6c577_shop_thumbnail.jpg', 'FOOD', ' O Frango Panado é uma obra-prima crocante por fora e suculenta por dentro. Cada pedaço é cuidadosamente empanado e frito até a perfeição, oferecendo uma experiência irresistível de comfort food', 4, 0.0)");
                            db.execSQL("INSERT INTO menuitem VALUES(16, 'Sushi Misto 20p', 1.75,'https://www.comidas.pt/upload/media/cache/7d526b45c96624fe199f590c79a0e5df_shop_thumbnail.jpg', 'FOOD', ' Explore uma variedade de sabores com nosso Sushi Misto. Uma seleção cuidadosa de nigiris e sushis enrolados, apresentando o melhor da culinária japonesa em cada peça', 4, 0.0)");

                            //----------------Dom Dinis(5)---------------------------------------------//
                            db.execSQL("INSERT INTO menuitem VALUES(17, 'Frango Grelhado', 12,'https://img.cybercook.com.br/imagens/receitas/340/file-de-frango-grelhado-2.jpeg', 'FOOD', ' Simplicidade e sabor puro com nosso Frango Grelhado. Peitos de frango tenros, cuidadosamente grelhados para obter aquela crosta dourada por fora e a suculência perfeita por dentro', 5, 0.0)");
                            db.execSQL("INSERT INTO menuitem VALUES(18, 'Pijaminha à D.D.', 26.9,'https://media-cdn.tripadvisor.com/media/photo-m/1280/19/62/9d/ec/d-dinis-pijaminha-com.jpg', 'FOOD', ' Simplicidade e sabor puro com nosso Frango Grelhado. Peitos de frango tenros, cuidadosamente grelhados para obter aquela crosta dourada por fora e a suculência perfeita por dentro', 5, 0.0)");
                            db.execSQL("INSERT INTO menuitem VALUES(19, 'Migas De Espargos', 2.75,'https://img-global.cpcdn.com/recipes/d152089b4bb34437/400x400cq70/photo.jpg', 'FOOD', ' Espargos frescos e tenros, delicadamente salteados com migalhas de pão, alho dourado e ervas aromáticas', 5, 0.0)");
                            db.execSQL("INSERT INTO menuitem VALUES(20, 'Picanha black angus ',14,'https://media-cdn.tripadvisor.com/media/photo-s/17/4c/f1/83/picanha-di-black-angus.jpg', 'FOOD', ' Corte suculento e marmorizado de Black Angus, grelhado com maestria para realçar a suculência e o sabor robusto', 5, 0.0)");


                            //----------------Table IDs---------------------------------------------//
                            db.execSQL("INSERT INTO menuTable (menuTableId, logoUrl) VALUES (1,'https://brandslogos.com/wp-content/uploads/images/large/burger-king-logo.png')");
                            db.execSQL("INSERT INTO menuTable (menuTableId, logoUrl) VALUES (2, 'https://www.mcdonalds.pt/media/3650/logotipo-mcdonalds-portugal.png?mode=pad')");
                            db.execSQL("INSERT INTO menuTable (menuTableId, logoUrl) VALUES (3, 'https://www.comidas.pt/upload/media/cache/7baa4f3fe1855bbcfccba59bc8926e5f_logos.png')");
                            db.execSQL("INSERT INTO menuTable (menuTableId, logoUrl) VALUES (4, 'https://www.comidas.pt/upload/media/cache/46db6ac037b47f02983373d371fbe5b3_logos.png')");
                            db.execSQL("INSERT INTO menuTable (menuTableId, logoUrl) VALUES (5, 'https://www.comidas.pt/upload/media/cache/0f0d6ef23ecf8137fb2e7f7c40aadf89_logos.png')");
                        }
                    }).build();
        }
        return INSTANCE;
    }
}

