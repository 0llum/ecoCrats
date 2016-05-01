package com.ollum.ecoCrats.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ollum.ecoCrats.Adapters.RegionsAdapter;
import com.ollum.ecoCrats.R;

public class RegionDetailsFragment extends Fragment {
    public static int ID, area, population;
    String region, capital;
    TextView tvRegion, tvCapital, tvArea, tvPopulation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Bundle bundle = RegionsAdapter.bundle;

        if (bundle != null) {
            ID = bundle.getInt("ID");
            region = bundle.getString("region");
            capital = bundle.getString("capital");
            area = bundle.getInt("area");
            population = bundle.getInt("population");
        }

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_region_details, container, false);

        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(region);

        tvRegion = (TextView) view.findViewById(R.id.region_details_region);
        tvCapital = (TextView) view.findViewById(R.id.region_details_capital);
        tvArea = (TextView) view.findViewById(R.id.region_details_area);
        tvPopulation = (TextView) view.findViewById(R.id.region_details_population);

        tvRegion.setText(region);
        tvCapital.setText(capital);
        tvArea.setText("" + area);
        tvPopulation.setText("" + population);

        return view;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem buy = menu.findItem(R.id.buyArea);
        buy.setVisible(true);

        /*MenuItem build = menu.findItem(R.id.build);
        build.setVisible(true);*/
    }
}
