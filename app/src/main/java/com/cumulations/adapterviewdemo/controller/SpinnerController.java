package com.cumulations.adapterviewdemo.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.cumulations.adapterviewdemo.R;
import com.cumulations.adapterviewdemo.adapter.SpinnerDataAdapter;
import com.cumulations.adapterviewdemo.model.Data;
import com.cumulations.adapterviewdemo.model.RemoteAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class SpinnerController extends AppCompatActivity {

    List<Data> data = new ArrayList<>();
    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> teams = new ArrayList<>();
    ArrayList<String> imageurls = new ArrayList<>();
    Spinner simpleSpinner, customSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_controller);
        simpleSpinner = findViewById(R.id.simpleSpinner);
        customSpinner = findViewById(R.id.customSpinner);

        getData();
    }

    private void getData() {
        String url = RemoteAPI.URL + "1fgll7";//https://api.myjson.com/bins/1fgll7
        retrofit2.Call<List<Data>> dataList = RemoteAPI.getService().getDataList(url);
        dataList.enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(retrofit2.Call<List<Data>> call, Response<List<Data>> response) {
                data = response.body();
                Toast.makeText(SpinnerController.this, "Successful.", Toast.LENGTH_SHORT).show();

                for (int i = 0; i < data.size(); i++) {
                    names.add(data.get(i).getName());
                    teams.add(data.get(i).getTeam());
                    imageurls.add(data.get(i).getImageurl());
                }

                setSimpleSpinner(names);
                setCustomSpinner(names, teams, imageurls);
            }

            @Override
            public void onFailure(retrofit2.Call<List<Data>> call, Throwable t) {
                if (t instanceof IOException) {
                    Toast.makeText(SpinnerController.this, "Network Failure! Retry.", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("@@@", t.getMessage());
                    Toast.makeText(SpinnerController.this, "Conversion Issue.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setSimpleSpinner(ArrayList<String> names) {
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(SpinnerController.this, R.layout.simple_list_item, R.id.textView3, names);
        simpleSpinner.setAdapter(adapter);

        simpleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String value = adapter.getItem(position);
//                Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " is Selected.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getBaseContext(),"Nothing is Selected.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setCustomSpinner(ArrayList<String> names, ArrayList<String> teams, ArrayList<String> imageurls) {
        final SpinnerDataAdapter adapter = new SpinnerDataAdapter(getApplicationContext(), names, teams, imageurls);
        customSpinner.setAdapter(adapter);

        customSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value = (String) adapter.getItem(position);
                Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getBaseContext(),"Nothing is Selected.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
