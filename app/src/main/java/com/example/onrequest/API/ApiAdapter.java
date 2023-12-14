package com.example.onrequest.API;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.onrequest.R;

import java.util.List;

public class ApiAdapter extends RecyclerView.Adapter<ApiAdapter.ViewHolder> {
     List<MenuItemAPI> menuItems;
     Context context;

    public ApiAdapter(List<MenuItemAPI> menuItems, Context context) {
        this.menuItems = menuItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.api_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MenuItemAPI menuItem = menuItems.get(position);

        holder.name.setText(menuItem.getMenuItemName());
        holder.price.setText("$" + String.valueOf(menuItem.getMenuItemPrice()));

        //Glide.with(context.getApplicationContext()).load(menuItems.get(position).getImageURL()).centerCrop().into(holder.menuItemAvatar);


        //Picasso.get().load(menuItem.getMenuItemAvatarUrl()).into(holder.menuItemAvatar);

    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }


    public void updateAdapter(List<MenuItemAPI> menuItemAPIList){
        menuItems = menuItemAPIList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView price;
        ImageView menuItemAvatar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            menuItemAvatar = itemView.findViewById(R.id.avatar);
            price = itemView.findViewById(R.id.price);


        }
    }
}