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
import com.ollum.ecoCrats.Classes.Company;
import com.ollum.ecoCrats.Classes.Item;
import com.ollum.ecoCrats.Classes.Plant;
import com.ollum.ecoCrats.Fragments.NewTransportFragment;
import com.ollum.ecoCrats.Fragments.StoreDetailsFragment;
import com.ollum.ecoCrats.R;

import java.util.ArrayList;

public class CompanyDetailsAdapter extends RecyclerView.Adapter<CompanyDetailsAdapter.RecyclerViewHolder> {
    public static Bundle bundle;
    ArrayList<Plant> arrayList = new ArrayList<>();
    Context ctx;

    public CompanyDetailsAdapter(ArrayList<Plant> arrayList, Context ctx) {
        this.arrayList = arrayList;
        this.ctx = ctx;
    }

    @Override
    public CompanyDetailsAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_company_details_row, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view, ctx, arrayList);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(CompanyDetailsAdapter.RecyclerViewHolder holder, int position) {
        Plant plant = arrayList.get(position);
        holder.name.setText(plant.getName());

        switch (plant.getID()) {
            case 1:
                holder.imageView.setImageResource(R.mipmap.hochofen);
                break;
            case 2:
                holder.imageView.setImageResource(R.mipmap.konverter);
                break;
            case 3:
                holder.imageView.setImageResource(R.mipmap.lichtbogenofen);
                break;
            case 4:
                holder.imageView.setImageResource(R.mipmap.walzwerk);
                break;
            case 5:
                holder.imageView.setImageResource(R.mipmap.aetzanlage);
                break;
            case 6:
                holder.imageView.setImageResource(R.mipmap.bestueckungsautomat);
                break;
            case 7:
                holder.imageView.setImageResource(R.mipmap.galvanikanlage);
                break;
            case 8:
                break;
            case 9:
                holder.imageView.setImageResource(R.mipmap.strassenwalze);
                break;
            case 10:
                holder.imageView.setImageResource(R.mipmap.kran);
                break;
            case 11:
                holder.imageView.setImageResource(R.mipmap.gleisbaumaschine);
                break;
            case 12:
                holder.imageView.setImageResource(R.mipmap.kokerei);
                break;
            case 13:
                holder.imageView.setImageResource(R.mipmap.gesenkschmiede);
                break;
            case 14:
                holder.imageView.setImageResource(R.mipmap.wasseraufbereitungsanlage);
                break;
            case 15:
                holder.imageView.setImageResource(R.mipmap.recyclinganlage);
                break;
            case 16:
                holder.imageView.setImageResource(R.mipmap.industrieroboter);
                break;
            case 17:
                holder.imageView.setImageResource(R.mipmap.raffinerie);
                break;
            case 18:
                holder.imageView.setImageResource(R.mipmap.brennofen);
                break;
            case 19:
                break;
            case 20:
                holder.imageView.setImageResource(R.mipmap.gattersaege);
                break;
            case 21:
                holder.imageView.setImageResource(R.mipmap.bandsaege);
                break;
            case 22:
                holder.imageView.setImageResource(R.mipmap.werkstatt);
                break;
            case 23:
                break;
            case 24:
                break;
            case 25:
                break;
            case 26:
                break;
            case 27:
                break;
            case 28:
                break;
            case 29:
                break;
            case 30:
                break;
            case 31:
                break;
            case 32:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        ImageView imageView;
        ArrayList<Plant> plants = new ArrayList<Plant>();
        Context ctx;

        public RecyclerViewHolder(View view, Context ctx, ArrayList<Plant> plants) {
            super(view);
            this.plants = plants;
            this.ctx = ctx;
            view.setOnClickListener(this);

            name = (TextView) view.findViewById(R.id.company_details_row_name);
            imageView = (ImageView) view.findViewById(R.id.company_details_row_image);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            final Plant plant = this.plants.get(position);
        }
    }
}
