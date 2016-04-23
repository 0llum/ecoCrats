package com.ollum.ecoCrats.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.ollum.ecoCrats.BackgroundTasks.BackgroundTask;
import com.ollum.ecoCrats.Classes.Item;
import com.ollum.ecoCrats.Fragments.StoreDetailsFragment;
import com.ollum.ecoCrats.R;

import java.util.ArrayList;

public class AddItemsAdapter extends RecyclerView.Adapter<AddItemsAdapter.RecyclerViewHolder> {
    public static Bundle bundle;
    ArrayList<Item> arrayList = new ArrayList<>();
    Context ctx;

    public AddItemsAdapter(ArrayList<Item> arrayList, Context ctx) {
        this.arrayList = arrayList;
        this.ctx = ctx;
    }

    @Override
    public AddItemsAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_items_row, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view, ctx, arrayList);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Item item = arrayList.get(position);
        holder.name.setText(item.getName());
        holder.company.setText(item.getCompany());

        switch (item.getID()) {
            case 1:
                holder.imageView.setImageResource(R.mipmap.eisenerz);
                break;
            case 2:
                holder.imageView.setImageResource(R.mipmap.holz);
                break;
            case 3:
                holder.imageView.setImageResource(R.mipmap.erdoel);
                break;
            case 4:
                holder.imageView.setImageResource(R.mipmap.erdgas);
                break;
            case 5:
                holder.imageView.setImageResource(R.mipmap.kautschuk);
                break;
            case 6:
                holder.imageView.setImageResource(R.mipmap.gold);
                break;
            case 7:
                holder.imageView.setImageResource(R.mipmap.kohle);
                break;
            case 8:
                holder.imageView.setImageResource(R.mipmap.sand);
                break;
            case 9:
                holder.imageView.setImageResource(R.mipmap.wasser);
                break;
            case 10:
                holder.imageView.setImageResource(R.mipmap.stein);
                break;
            case 11:
                holder.imageView.setImageResource(R.mipmap.kupfererz);
                break;
            case 12:
                holder.imageView.setImageResource(R.mipmap.baumwolle);
                break;
            case 13:
                holder.imageView.setImageResource(R.mipmap.leder);
                break;
            case 14:
                holder.imageView.setImageResource(R.mipmap.kalkstein);
                break;
            case 15:
                holder.imageView.setImageResource(R.mipmap.ton);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, company;
        ImageView imageView;
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
            imageView = (ImageView) view.findViewById(R.id.items_row_image);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            item = this.items.get(position);

            final AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);
            SeekBar seekBar = new SeekBar(ctx);
            seekBar.setMax(100);
            seekBar.setKeyProgressIncrement(1);

            dialog.setTitle(R.string.quantity_select);
            dialog.setMessage("" + 0);
            dialog.setView(seekBar);

            dialog.setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String method = "addItem";
                    BackgroundTask backgroundTask = new BackgroundTask(ctx);
                    backgroundTask.execute(method, "" + StoreDetailsFragment.ID, "" + item.getID(), "" + progress);
                    dialog.dismiss();
                }
            });
            dialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
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
