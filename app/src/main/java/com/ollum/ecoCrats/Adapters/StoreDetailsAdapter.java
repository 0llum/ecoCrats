package com.ollum.ecoCrats.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.ollum.ecoCrats.Activities.MainActivity;
import com.ollum.ecoCrats.BackgroundTasks.BackgroundTask;
import com.ollum.ecoCrats.Classes.Item;
import com.ollum.ecoCrats.Fragments.StoreDetailsFragment;
import com.ollum.ecoCrats.Fragments.TransportFragment;
import com.ollum.ecoCrats.R;

import java.util.ArrayList;

public class StoreDetailsAdapter extends RecyclerView.Adapter<StoreDetailsAdapter.RecyclerViewHolder> {
    public static Bundle bundle;
    ArrayList<Item> arrayList = new ArrayList<>();
    Context ctx;

    public StoreDetailsAdapter(ArrayList<Item> arrayList, Context ctx) {
        this.arrayList = arrayList;
        this.ctx = ctx;
    }

    @Override
    public StoreDetailsAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_store_details_row, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view, ctx, arrayList);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(StoreDetailsAdapter.RecyclerViewHolder holder, int position) {
        Item item = arrayList.get(position);
        holder.name.setText(item.getName());
        holder.quantity.setText("" + item.getQuantity());

        switch (item.getItemID()) {
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
        TextView name, quantity;
        ImageView imageView;
        ArrayList<Item> items = new ArrayList<Item>();
        Context ctx;
        int progress = 0;
        int price = 0;

        public RecyclerViewHolder(View view, Context ctx, ArrayList<Item> items) {
            super(view);
            this.items = items;
            this.ctx = ctx;
            view.setOnClickListener(this);

            name = (TextView) view.findViewById(R.id.store_details_row_name);
            quantity = (TextView) view.findViewById(R.id.store_details_row_quantity);
            imageView = (ImageView) view.findViewById(R.id.store_details_row_image);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            final Item item = this.items.get(position);

            AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);
            dialog.setItems(R.array.store_details_options, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case 0:
                            final AlertDialog.Builder sellDialog = new AlertDialog.Builder(ctx);

                            LinearLayout layout = new LinearLayout(ctx);
                            layout.setOrientation(LinearLayout.VERTICAL);

                            SeekBar seekBarSell = new SeekBar(ctx);
                            seekBarSell.setMax(item.getQuantity());
                            seekBarSell.setKeyProgressIncrement(1);
                            layout.addView(seekBarSell);

                            final EditText etPrice = new EditText(ctx);
                            etPrice.setInputType(InputType.TYPE_CLASS_NUMBER);
                            layout.addView(etPrice);

                            sellDialog.setTitle(R.string.quantity_price_select);
                            sellDialog.setMessage("" + 0);
                            sellDialog.setView(layout);

                            sellDialog.setPositiveButton(R.string.sell, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    price = Integer.parseInt(etPrice.getText().toString());

                                    String method = "sellItem";
                                    BackgroundTask backgroundTask = new BackgroundTask(ctx);
                                    backgroundTask.execute(method, "" + item.getID(), "" + progress, "" + price);
                                    dialog.dismiss();
                                }
                            });
                            sellDialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });

                            final AlertDialog alertDialogSell = sellDialog.create();

                            seekBarSell.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                @Override
                                public void onProgressChanged(SeekBar seekBar, int progressV, boolean fromUser) {
                                    progress = progressV;
                                    alertDialogSell.setMessage("" + progress);
                                }

                                @Override
                                public void onStartTrackingTouch(SeekBar seekBar) {

                                }

                                @Override
                                public void onStopTrackingTouch(SeekBar seekBar) {

                                }
                            });
                            alertDialogSell.show();
                            break;
                        case 1:
                            bundle = new Bundle();
                            bundle.putInt("storeID", StoreDetailsFragment.ID);
                            bundle.putInt("itemID", item.getID());

                            TransportFragment transportFragment = new TransportFragment();
                            transportFragment.setArguments(bundle);
                            FragmentTransaction transaction2 = MainActivity.fragmentManager.beginTransaction();
                            transaction2.replace(R.id.mainContent, transportFragment, "TransportFragment");
                            transaction2.addToBackStack("TransportFragment");
                            transaction2.commit();
                            break;
                    }
                }
            });
            dialog.create();
            dialog.show();
        }
    }
}
