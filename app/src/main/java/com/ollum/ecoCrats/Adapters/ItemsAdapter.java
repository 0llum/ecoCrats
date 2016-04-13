package com.ollum.ecoCrats.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.ollum.ecoCrats.Activities.MainActivity;
import com.ollum.ecoCrats.BackgroundTasks.BackgroundTask;
import com.ollum.ecoCrats.Classes.Country;
import com.ollum.ecoCrats.Classes.Item;
import com.ollum.ecoCrats.Fragments.RegionsFragment;
import com.ollum.ecoCrats.Fragments.StoreDetailsFragment;
import com.ollum.ecoCrats.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.RecyclerViewHolder> {
    ArrayList<Item> arrayList = new ArrayList<>();
    Context ctx;
    public static Bundle bundle;

    public ItemsAdapter(ArrayList<Item> arrayList, Context ctx) {
        this.arrayList = arrayList;
        this.ctx = ctx;
    }

    @Override
    public ItemsAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_items_row, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view, ctx, arrayList);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Item item = arrayList.get(position);
        holder.name.setText(item.getName());
        holder.company.setText(item.getCompany());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, company;
        ArrayList<Item> items = new ArrayList<Item>();
        Context ctx;
        int progress = 0;
        Item item;

        public RecyclerViewHolder(View view, Context ctx, ArrayList<Item> items) {
            super(view);
            this.items = items;
            this.ctx = ctx;
            view.setOnClickListener(this);

            name = (TextView) view.findViewById(R.id.items_row_name);
            company = (TextView) view.findViewById(R.id.items_row_company);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            item = this.items.get(position);

            final AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);
            SeekBar seekBar = new SeekBar(ctx);
            seekBar.setMax(100);
            seekBar.setKeyProgressIncrement(1);

            dialog.setTitle("Select Quantity");
            dialog.setMessage("" + 0);
            dialog.setView(seekBar);

            dialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String method = "addItem";
                    BackgroundTask backgroundTask = new BackgroundTask(ctx);
                    backgroundTask.execute(method, "" + StoreDetailsFragment.ID, "" + item.getID(), "" + progress);
                    dialog.dismiss();
                }
            });
            dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            final AlertDialog alertDialog = dialog.create();

            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progressV, boolean fromUser) {
                    progress = progressV;
                    alertDialog.setMessage("" + progress);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

            alertDialog.show();
        }
    }
}
