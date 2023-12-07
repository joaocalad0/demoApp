package com.example.onrequest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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

        // Populate the data into the template view using the data object
        MenuItemWithCounter menuItemWithCounter = getItem(position);

        itemName.setText(menuItemWithCounter.getMenuItemName());
        itemCounter.setText(String.valueOf(menuItemWithCounter.getCounter()));

        double discountPercentage = 0.05;
        itemPrice.setText(menuItemWithCounter.getFormattedPrice(discountPercentage));

        // Return the completed view to render on screen
        return convertView;
    }
}
