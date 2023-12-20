package com.nursam.firebasefirestore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FurnitureAdapter extends RecyclerView.Adapter<FurnitureAdapter.FurnitureViewHolder> {

    private ArrayList<FurnitureItem> furnitureList;

    public FurnitureAdapter(ArrayList<FurnitureItem> furnitureList) {
        this.furnitureList = furnitureList;
    }

    @NonNull
    @Override
    public FurnitureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_furniture, parent, false);
        return new FurnitureViewHolder(itemView);
    }

    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @Override
    public void onBindViewHolder(@NonNull final FurnitureAdapter.FurnitureViewHolder holder, int position) {
        FurnitureItem furnitureItem = furnitureList.get(position);
        holder.imageViewFurniture.setImageResource(furnitureItem.getFoto());
        holder.textViewNamaFurniture.setText(furnitureItem.getNama());
        holder.textViewDeskripsiFurniture.setText(furnitureItem.getDeskripsi());
        holder.itemView.setOnClickListener(v -> onItemClickCallback.onItemClicked(furnitureList.get(holder.getAdapterPosition())));
    }

    public interface OnItemClickCallback {
        void onItemClicked(FurnitureItem data);
    }

    @Override
    public int getItemCount() {
        return furnitureList.size();
    }

    class FurnitureViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewFurniture;
        TextView textViewNamaFurniture;
        TextView textViewDeskripsiFurniture;

        public FurnitureViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewFurniture = itemView.findViewById(R.id.imageViewFurniture);
            textViewNamaFurniture = itemView.findViewById(R.id.textViewNamaFurniture);
            textViewDeskripsiFurniture = itemView.findViewById(R.id.textViewDeskripsiFurniture);
        }
    }
}
