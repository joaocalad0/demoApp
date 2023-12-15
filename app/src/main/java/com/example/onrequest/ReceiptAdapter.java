package com.example.onrequest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReceiptAdapter extends RecyclerView.Adapter<ReceiptAdapter.ReceiptViewHolder> {

    private List<Receipt> receiptList;

    public ReceiptAdapter(List<Receipt> receiptList) {
        this.receiptList = receiptList;
    }

    @NonNull
    @Override
    public ReceiptViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.receipt_item, parent, false);
        return new ReceiptViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReceiptViewHolder holder, int position) {
        Receipt receipt = receiptList.get(position);
        holder.bind(receipt);
    }

    @Override
    public int getItemCount() {
        return receiptList.size();
    }

    public class ReceiptViewHolder extends RecyclerView.ViewHolder {
        private TextView itemNameTextView;
        private TextView totalPriceTextView;

        public ReceiptViewHolder(@NonNull View itemView) {
            super(itemView);
            itemNameTextView = itemView.findViewById(R.id.textViewItemName);
            totalPriceTextView = itemView.findViewById(R.id.textViewPrice);
        }

        public void bind(Receipt receipt) {
            itemNameTextView.setText(receipt.getItemName());
            totalPriceTextView.setText(String.valueOf(receipt.getTotalPrice()));
        }
    }
}