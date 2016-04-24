package com.ollum.ecoCrats.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ollum.ecoCrats.R;

public class NavDrawerAdapter extends BaseAdapter{
    private Context ctx;
    String[] items;
    int[] images = {
        R.mipmap.profile_white,
        R.mipmap.friendlist_white,
        R.mipmap.stores_white,
        R.mipmap.companies_white,
        R.mipmap.research_white,
        R.mipmap.market_white,
        R.mipmap.world_map_white,
        R.mipmap.bank_white
    };

    public NavDrawerAdapter(Context ctx) {
        this.ctx = ctx;
        items = ctx.getResources().getStringArray(R.array.navDrawerItems);
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.nav_drawer_row, parent, false);
        } else {
            row = convertView;
        }
        TextView textView = (TextView) row.findViewById(R.id.nav_drawer_row_text);
        ImageView imageView = (ImageView) row.findViewById(R.id.nav_drawer_row_image);

        textView.setText(items[position]);
        imageView.setImageResource(images[position]);

        return row;
    }
}
