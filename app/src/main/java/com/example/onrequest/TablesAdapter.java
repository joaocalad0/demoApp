package com.example.onrequest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.onrequest.schema.entity.table.MenuTable;

import java.util.ArrayList;
import java.util.List;

public class TablesAdapter extends RecyclerView.Adapter<TablesAdapter.ViewHolder> {
    //TODO
    private List<MenuTable> menuTableList;
    private Context context;

    public interface OnItemClickListener{
        void onItemClick(MenuTable menuTable);
    }

    private OnItemClickListener onItemClickListener;

    public TablesAdapter(Context context) {
        this.context = context;
        this.menuTableList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tables_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MenuTable menuTable = menuTableList.get(position);

        //holder.tableName.setText("Table" + menuTable.getMenuTableId());
        Glide.with(holder.itemView.getContext()).load(menuTable.getLogoUrl()).into(holder.tablesAvatar);

        holder.itemView.setOnClickListener(view -> {
            if (onItemClickListener != null){
                onItemClickListener.onItemClick(menuTable);
            }
        });
    }

    @Override
    public int getItemCount() {
        return menuTableList.size();
    }

    public void refreshList(List<MenuTable> menuTableList){
        this.menuTableList = menuTableList;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    //TODO
    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView tablesAvatar;
        //public TextView tableName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tablesAvatar = itemView.findViewById(R.id.tablesAvatar);
            //this.tableName = itemView.findViewById(R.id.tableName);
        }
    }
}
