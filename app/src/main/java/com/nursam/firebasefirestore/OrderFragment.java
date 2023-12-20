package com.nursam.firebasefirestore;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


public class OrderFragment extends Fragment {
    private RecyclerView orderRecyclerView;
    private ArrayList<FurnitureItem> orderItemList = new ArrayList<>();
    public OrderFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_order, container, false);
        orderRecyclerView = rootView.findViewById(R.id.recyclerViewOrder);
        orderItemList = getOrderItemList();
        showRecyclerList();
        return rootView;
    }
    private ArrayList<FurnitureItem> getOrderItemList(){
        String[] dataName = getResources().getStringArray(R.array.data_name);
        String[] dataDeskripsi = getResources().getStringArray(R.array.data_deskripsi);
        TypedArray dataFoto = getResources().obtainTypedArray(R.array.data_foto);
        ArrayList<FurnitureItem> orderItemArrayList = new ArrayList<>();
        for (int i = 0; i<dataName.length; i++){
            FurnitureItem furnitureItem = new FurnitureItem();
            furnitureItem.setDeskripsi(dataDeskripsi[i]);
            furnitureItem.setNama(dataName[i]);
            furnitureItem.setFoto(dataFoto.getResourceId(i, -1));
            orderItemArrayList.add(furnitureItem);
        }
        return  orderItemArrayList;
    }
    private void showRecyclerList(){
        orderRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        FurnitureAdapter furnitureAdapter = new FurnitureAdapter(orderItemList);
        orderRecyclerView.setAdapter(furnitureAdapter);
        furnitureAdapter.setOnItemClickCallback(this::showSelectedFurnitureItem);
    }
    private void showSelectedFurnitureItem(FurnitureItem furnitureItem){
        Intent intent = new Intent(getActivity(), MainActivity3.class);

        intent.putExtra("data_name", furnitureItem.getNama());
        intent.putExtra("data_deskripsi", furnitureItem.getDeskripsi());
        intent.putExtra("data_foto", furnitureItem.getFoto());

        startActivity(intent);

        Toast.makeText(getActivity(),"Kamu memilih"+ furnitureItem.getNama(), Toast.LENGTH_SHORT).show();
    }
}

