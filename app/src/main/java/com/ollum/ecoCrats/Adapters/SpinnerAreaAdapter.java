package com.ollum.ecoCrats.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ollum.ecoCrats.Classes.Area;
import com.ollum.ecoCrats.Classes.Store;
import com.ollum.ecoCrats.R;

import java.util.ArrayList;

public class SpinnerAreaAdapter extends ArrayAdapter<Area> {
    LayoutInflater inflater;
    private Context context;
    private ArrayList<Area> values;

    public SpinnerAreaAdapter(Context context, int textView, ArrayList<Area> values) {
        super(context, textView, values);
        this.context = context;
        this.values = values;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return values.size();
    }

    public Area getItem(int position) {
        return values.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = inflater.inflate(R.layout.spinner_layout, parent, false);
        TextView label = (TextView) row.findViewById(R.id.txt);
        label.setText(values.get(position).getRegion());
        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View row = inflater.inflate(R.layout.spinner_layout, parent, false);
        TextView label = (TextView) row.findViewById(R.id.txt);
        label.setText(values.get(position).getRegion());
        return label;
    }
}