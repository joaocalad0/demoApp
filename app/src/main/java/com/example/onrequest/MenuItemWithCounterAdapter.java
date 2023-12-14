package com.example.onrequest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MenuItemWithCounterAdapter extends ArrayAdapter<MenuItemWithCounter> {

    public MenuItemWithCounterAdapter(Context context, List<MenuItemWithCounter> menuItemWithCounterAdapters) {
        super(context, 0, menuItemWithCounterAdapters);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cart_menuitem, parent, false);
        }

        // Lookup view for data population
        TextView itemName = (TextView) convertView.findViewById(R.id.itemName);
        TextView itemCounter = (TextView) convertView.findViewById(R.id.itemCounter);
        TextView itemPrice = (TextView) convertView.findViewById(R.id.itemPrice);
        ImageView imageViewCart = (ImageView)convertView.findViewById(R.id.imageViewCart);

        // Populate the data into the template view using the data object
        MenuItemWithCounter menuItemWithCounter = getItem(position);

        itemName.setText(menuItemWithCounter.getMenuItemName());
        itemCounter.setText(String.valueOf(menuItemWithCounter.getCounter()));

        double discountPercentage = 0.05;
        itemPrice.setText(menuItemWithCounter.getFormattedPrice(discountPercentage));
        Glide.with(getContext()).load(menuItemWithCounter.getMenuItemImage()).into(imageViewCart);


        // Return the completed view to render on screen
        return convertView;
    }
}
