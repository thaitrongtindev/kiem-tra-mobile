package com.example.kiemtra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {


    private ArrayList<ObjectModel> models;

    public ListAdapter(ArrayList<ObjectModel> models) {
        this.models = models;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public Object getItem(int position) {
        return models.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View rowView = inflater.inflate(R.layout.item_list, parent, false);
        TextView tvQuantities = rowView.findViewById(R.id.tvQuantities);
        TextView tvName = rowView.findViewById(R.id.tvName);
       ObjectModel model =   models.get(position);
        tvQuantities.setText(model.quantities + "");
        tvName.setText(model.name);
        return rowView;
    }
}
