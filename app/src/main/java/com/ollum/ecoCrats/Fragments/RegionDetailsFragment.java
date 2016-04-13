package com.ollum.ecoCrats.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ollum.ecoCrats.Activities.MainActivity;
import com.ollum.ecoCrats.Adapters.RegionsAdapter;
import com.ollum.ecoCrats.BackgroundTasks.BackgroundTask;
import com.ollum.ecoCrats.R;

public class RegionDetailsFragment extends Fragment implements View.OnClickListener {
    String region, capital;
    int ID, area, population;
    TextView tvRegion, tvCapital, tvArea, tvPopulation;
    Button buy, build;
    int progress = 0;


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

        MainActivity.setTitle(region);

        tvRegion = (TextView) view.findViewById(R.id.region_details_region);
        tvCapital = (TextView) view.findViewById(R.id.region_details_capital);
        tvArea = (TextView) view.findViewById(R.id.region_details_area);
        tvPopulation = (TextView) view.findViewById(R.id.region_details_population);

        tvRegion.setText(region);
        tvCapital.setText(capital);
        tvArea.setText("" + area);
        tvPopulation.setText("" + population);

        buy = (Button) view.findViewById(R.id.region_details_buy);
        buy.setOnClickListener(this);
        build = (Button) view.findViewById(R.id.region_details_build);
        build.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.region_details_buy:
                showDialog();
                break;
            case R.id.region_details_build:
                BackgroundTask backgroundTask = new BackgroundTask(getContext());
                backgroundTask.execute("buildStore", MainActivity.user.getUsername(), "" + ID);
                break;
        }
    }

    public void showDialog() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        SeekBar seekBar = new SeekBar(getContext());
        seekBar.setMax(25);
        seekBar.setKeyProgressIncrement(1);

        dialog.setTitle("Select Area in ha");
        dialog.setMessage("" + 0);
        dialog.setView(seekBar);

        dialog.setPositiveButton("Buy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String method = "buyArea";
                BackgroundTask backgroundTask = new BackgroundTask(getContext());
                backgroundTask.execute(method, MainActivity.user.getUsername(), "" + ID, "" + progress);
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
