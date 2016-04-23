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
import com.ollum.ecoCrats.Classes.Country;
import com.ollum.ecoCrats.Fragments.RegionsFragment;
import com.ollum.ecoCrats.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.RecyclerViewHolder> {
    public static Bundle bundle;
    ArrayList<Country> arrayList = new ArrayList<>();
    Context ctx;

    public CountriesAdapter(ArrayList<Country> arrayList, Context ctx) {
        this.arrayList = arrayList;
        this.ctx = ctx;
    }

    @Override
    public CountriesAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_countries_row, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view, ctx, arrayList);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Country country = arrayList.get(position);
        holder.country.setText(country.getName());
        holder.capital.setText(country.getCapital());
        holder.area.setText("" + NumberFormat.getNumberInstance(Locale.GERMANY).format(country.getArea()));
        holder.population.setText("" + NumberFormat.getNumberInstance(Locale.GERMANY).format(country.getPopulation()));

        switch (country.getID()) {
            case 1:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 2:
                holder.imageView.setImageResource(R.mipmap.albania);
                break;
            case 3:
                holder.imageView.setImageResource(R.mipmap.algeria);
                break;
            case 4:
                holder.imageView.setImageResource(R.mipmap.andorra);
                break;
            case 5:
                holder.imageView.setImageResource(R.mipmap.angola);
                break;
            case 6:
                holder.imageView.setImageResource(R.mipmap.antigua_and_barbuda);
                break;
            case 7:
                holder.imageView.setImageResource(R.mipmap.argentina);
                break;
            case 8:
                holder.imageView.setImageResource(R.mipmap.armenia);
                break;
            case 9:
                holder.imageView.setImageResource(R.mipmap.australia);
                break;
            case 10:
                holder.imageView.setImageResource(R.mipmap.austria);
                break;
            case 11:
                holder.imageView.setImageResource(R.mipmap.azerbaijan);
                break;
            case 12:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 13:
                holder.imageView.setImageResource(R.mipmap.bahrain);
                break;
            case 14:
                holder.imageView.setImageResource(R.mipmap.bangladesh);
                break;
            case 15:
                holder.imageView.setImageResource(R.mipmap.barbados);
                break;
            case 16:
                holder.imageView.setImageResource(R.mipmap.belarus);
                break;
            case 17:
                holder.imageView.setImageResource(R.mipmap.belgium);
                break;
            case 18:
                holder.imageView.setImageResource(R.mipmap.belize);
                break;
            case 19:
                holder.imageView.setImageResource(R.mipmap.benin);
                break;
            case 20:
                holder.imageView.setImageResource(R.mipmap.bhutan);
                break;
            case 21:
                holder.imageView.setImageResource(R.mipmap.bolivia);
                break;
            case 22:
                holder.imageView.setImageResource(R.mipmap.bosnia_and_herzegovina);
                break;
            case 23:
                holder.imageView.setImageResource(R.mipmap.botswana);
                break;
            case 24:
                holder.imageView.setImageResource(R.mipmap.brazil);
                break;
            case 25:
                holder.imageView.setImageResource(R.mipmap.brunei);
                break;
            case 26:
                holder.imageView.setImageResource(R.mipmap.bulgaria);
                break;
            case 27:
                holder.imageView.setImageResource(R.mipmap.burkina_faso1);
                break;
            case 28:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 29:
                holder.imageView.setImageResource(R.mipmap.burundi);
                break;
            case 30:
                holder.imageView.setImageResource(R.mipmap.cambodia);
                break;
            case 31:
                holder.imageView.setImageResource(R.mipmap.cameroon);
                break;
            case 32:
                holder.imageView.setImageResource(R.mipmap.canada);
                break;
            case 33:
                holder.imageView.setImageResource(R.mipmap.cape_verde);
                break;
            case 34:
                holder.imageView.setImageResource(R.mipmap.central_african_republic);
                break;
            case 35:
                holder.imageView.setImageResource(R.mipmap.chad);
                break;
            case 36:
                holder.imageView.setImageResource(R.mipmap.chile);
                break;
            case 37:
                holder.imageView.setImageResource(R.mipmap.china);
                break;
            case 38:
                holder.imageView.setImageResource(R.mipmap.colombia);
                break;
            case 39:
                holder.imageView.setImageResource(R.mipmap.comoros);
                break;
            case 40:
                holder.imageView.setImageResource(R.mipmap.costa_rica);
                break;
            case 41:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 42:
                holder.imageView.setImageResource(R.mipmap.croatia);
                break;
            case 43:
                holder.imageView.setImageResource(R.mipmap.cuba);
                break;
            case 44:
                holder.imageView.setImageResource(R.mipmap.cyprus);
                break;
            case 45:
                holder.imageView.setImageResource(R.mipmap.czech_republic);
                break;
            case 46:
                holder.imageView.setImageResource(R.mipmap.democratic_republic_of_the_congo);
                break;
            case 47:
                holder.imageView.setImageResource(R.mipmap.denmark);
                break;
            case 48:
                holder.imageView.setImageResource(R.mipmap.djibouti);
                break;
            case 49:
                holder.imageView.setImageResource(R.mipmap.dominica);
                break;
            case 50:
                holder.imageView.setImageResource(R.mipmap.dominican_republic);
                break;
            case 51:
                holder.imageView.setImageResource(R.mipmap.east_timor);
                break;
            case 52:
                holder.imageView.setImageResource(R.mipmap.ecuador);
                break;
            case 53:
                holder.imageView.setImageResource(R.mipmap.egypt);
                break;
            case 54:
                holder.imageView.setImageResource(R.mipmap.el_salvador);
                break;
            case 55:
                holder.imageView.setImageResource(R.mipmap.equatorial_guinea);
                break;
            case 56:
                holder.imageView.setImageResource(R.mipmap.eritrea);
                break;
            case 57:
                holder.imageView.setImageResource(R.mipmap.estonia);
                break;
            case 58:
                holder.imageView.setImageResource(R.mipmap.ethiopia);
                break;
            case 59:
                holder.imageView.setImageResource(R.mipmap.federated_states_of_micronesia);
                break;
            case 60:
                holder.imageView.setImageResource(R.mipmap.fiji);
                break;
            case 61:
                holder.imageView.setImageResource(R.mipmap.finland);
                break;
            case 62:
                holder.imageView.setImageResource(R.mipmap.france);
                break;
            case 63:
                holder.imageView.setImageResource(R.mipmap.gabon);
                break;
            case 64:
                holder.imageView.setImageResource(R.mipmap.gambia);
                break;
            case 65:
                holder.imageView.setImageResource(R.mipmap.georgia);
                break;
            case 66:
                holder.imageView.setImageResource(R.mipmap.germany);
                break;
            case 67:
                holder.imageView.setImageResource(R.mipmap.ghana);
                break;
            case 68:
                holder.imageView.setImageResource(R.mipmap.greece);
                break;
            case 69:
                holder.imageView.setImageResource(R.mipmap.grenada);
                break;
            case 70:
                holder.imageView.setImageResource(R.mipmap.guatemala);
                break;
            case 71:
                holder.imageView.setImageResource(R.mipmap.guinea);
                break;
            case 72:
                holder.imageView.setImageResource(R.mipmap.guinea_bissau);
                break;
            case 73:
                holder.imageView.setImageResource(R.mipmap.guyana);
                break;
            case 74:
                holder.imageView.setImageResource(R.mipmap.haiti);
                break;
            case 75:
                holder.imageView.setImageResource(R.mipmap.honduras);
                break;
            case 76:
                holder.imageView.setImageResource(R.mipmap.hungary);
                break;
            case 77:
                holder.imageView.setImageResource(R.mipmap.iceland);
                break;
            case 78:
                holder.imageView.setImageResource(R.mipmap.india);
                break;
            case 79:
                holder.imageView.setImageResource(R.mipmap.indonesia);
                break;
            case 80:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 81:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 82:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 83:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 84:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 85:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 86:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 87:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 88:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 89:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 90:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 91:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 92:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 93:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 94:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 95:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 96:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 97:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 98:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 99:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 100:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 101:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 102:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 103:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 104:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 105:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 106:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 107:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 108:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 109:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 110:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 111:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 122:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 123:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 124:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 125:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 126:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 127:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 128:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 129:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 130:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 131:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 132:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 133:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 134:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 135:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 136:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 137:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 138:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 139:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 140:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 141:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 142:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 143:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 144:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 145:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 146:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 147:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 148:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 149:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 150:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 151:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 152:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 153:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 154:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 155:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 156:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 157:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 158:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 159:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 160:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 161:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 162:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 163:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 164:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 165:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 166:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 167:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 168:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 169:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 170:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 171:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 172:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 173:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 174:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 175:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 176:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 177:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 178:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 179:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 180:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 181:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 182:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 183:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 184:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 185:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 186:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 187:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 188:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 189:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 190:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 191:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 192:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 193:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 194:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
            case 195:
                holder.imageView.setImageResource(R.mipmap.afghanistan);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView country, capital, area, population;
        ImageView imageView;
        ArrayList<Country> countries = new ArrayList<Country>();
        Context ctx;

        public RecyclerViewHolder(View view, Context ctx, ArrayList<Country> countries) {
            super(view);
            this.countries = countries;
            this.ctx = ctx;
            view.setOnClickListener(this);

            country = (TextView) view.findViewById(R.id.display_countries_row_country);
            capital = (TextView) view.findViewById(R.id.display_countries_row_capital);
            area = (TextView) view.findViewById(R.id.display_countries_row_area);
            population = (TextView) view.findViewById(R.id.display_countries_row_population);
            imageView = (ImageView) view.findViewById(R.id.countries_row_image);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Country country = this.countries.get(position);

            bundle = new Bundle();
            bundle.putString("country", country.getName());

            RegionsFragment regionsFragment = new RegionsFragment();
            regionsFragment.setArguments(bundle);
            FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
            transaction.replace(R.id.mainContent, regionsFragment, "RegionsFragment");
            transaction.addToBackStack("RegionsFragment");
            transaction.commit();
        }
    }
}
