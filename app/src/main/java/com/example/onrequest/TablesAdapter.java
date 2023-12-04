package com.example.onrequest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onrequest.schema.entity.table.MenuTable;

import java.util.List;

public class TablesAdapter extends RecyclerView.Adapter<TablesAdapter.ViewHolder> {
    //TODO
    private List<MenuTable> menuTableList;
    private Context context;

    public TablesAdapter(List<MenuTable> menuTableList, Context context) {
        this.menuTableList = menuTableList;
        this.context = context;
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
    }

    @Override
    public int getItemCount() {
        return menuTableList.size();
    }

    //TODO
    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView tablesAvatar;
        public TextView tableName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tablesAvatar = itemView.findViewById(R.id.tablesAvatar);
            this.tableName = itemView.findViewById(R.id.tableName);
        }
    }
}
