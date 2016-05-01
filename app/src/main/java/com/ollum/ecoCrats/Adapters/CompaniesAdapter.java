package com.ollum.ecoCrats.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ollum.ecoCrats.Activities.MainActivity;
import com.ollum.ecoCrats.Classes.Company;
import com.ollum.ecoCrats.Fragments.CompanyDetailsFragment;
import com.ollum.ecoCrats.R;

import java.util.ArrayList;

public class CompaniesAdapter extends RecyclerView.Adapter<CompaniesAdapter.RecyclerViewHolder> {
    public static Bundle bundle;
    ArrayList<Company> arrayList = new ArrayList<>();
    Context ctx;

    public CompaniesAdapter(ArrayList<Company> arrayList, Context ctx) {
        this.arrayList = arrayList;
        this.ctx = ctx;
    }

    @Override
    public CompaniesAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_companies_row, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view, ctx, arrayList);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(CompaniesAdapter.RecyclerViewHolder holder, int position) {
        Company company = arrayList.get(position);
        holder.name.setText(company.getName());
        holder.region.setText(company.getRegion());

        switch (company.getCompanyID()) {
            case 1:
                holder.imageView.setImageResource(R.mipmap.solarkraftwerk);
                break;
            case 2:
                break;
            case 3:
                holder.imageView.setImageResource(R.mipmap.windkraftanlage);
                break;
            case 4:
                holder.imageView.setImageResource(R.mipmap.mine);
                break;
            case 5:
                holder.imageView.setImageResource(R.mipmap.wasserkraftwerk);
                break;
            case 6:
                holder.imageView.setImageResource(R.mipmap.stahlwerk);
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                holder.imageView.setImageResource(R.mipmap.agrarbetrieb);
                break;
            case 10:
                holder.imageView.setImageResource(R.mipmap.bohrturm);
                break;
            case 11:
                holder.imageView.setImageResource(R.mipmap.tagebau);
                break;
            case 12:
                holder.imageView.setImageResource(R.mipmap.saegewerk);
                break;
            case 13:
                break;
            case 14:
                break;
            case 15:
                break;
            case 16:
                break;
            case 17:
                break;
            case 18:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, region;
        ImageView imageView;
        ArrayList<Company> companies = new ArrayList<Company>();
        Context ctx;

        public RecyclerViewHolder(View view, Context ctx, ArrayList<Company> stores) {
            super(view);
            this.companies = companies;
            this.ctx = ctx;
            view.setOnClickListener(this);

            name = (TextView) view.findViewById(R.id.companies_row_name);
            region = (TextView) view.findViewById(R.id.companies_row_region);
            imageView = (ImageView) view.findViewById(R.id.companies_row_image);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Company company = this.companies.get(position);

            bundle = new Bundle();
            bundle.putInt("ID", company.getID());
            bundle.putString("region", company.getRegion());

            CompanyDetailsFragment companyDetailsFragment = new CompanyDetailsFragment();
            companyDetailsFragment.setArguments(bundle);
            FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
            transaction.replace(R.id.mainContent, companyDetailsFragment, "CompanyDetailsFragment");
            transaction.addToBackStack("CompanyDetailsFragment");
            transaction.commit();
        }
    }
}
