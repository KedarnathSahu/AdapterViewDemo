package com.cumulations.adapterviewdemo.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.cumulations.adapterviewdemo.model.Data;
import com.cumulations.adapterviewdemo.model.RemoteAPI;
import com.cumulations.adapterviewdemo.R;
import com.cumulations.adapterviewdemo.adapter.ListDataAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class ListViewController extends AppCompatActivity {

    ListView simpleListView, customListView;
    List<Data> data = new ArrayList<>();
    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> teams = new ArrayList<>();
    ArrayList<String> imageurls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_controller);
        simpleListView = findViewById(R.id.simpleListView);
        customListView = findViewById(R.id.customListView);

        getData();
    }

    private void getData() {
        String url = RemoteAPI.URL + "1fgll7";//https://api.myjson.com/bins/1fgll7
        retrofit2.Call<List<Data>> dataList = RemoteAPI.getService().getDataList(url);
        dataList.enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(retrofit2.Call<List<Data>> call, Response<List<Data>> response) {
                data = response.body();
                Toast.makeText(ListViewController.this, "Successful.", Toast.LENGTH_SHORT).show();

                for (int i = 0; i < data.size(); i++) {
                    names.add(data.get(i).getName());
                    teams.add(data.get(i).getTeam());
                    imageurls.add(data.get(i).getImageurl());
                }

                setSimpleListView(names);
                setCustomListView(names, teams, imageurls);
            }

            @Override
            public void onFailure(retrofit2.Call<List<Data>> call, Throwable t) {
                if (t instanceof IOException) {
                    Toast.makeText(ListViewController.this, "Network Failure! Retry.", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("@@@", t.getMessage());
                    Toast.makeText(ListViewController.this, "Conversion Issue.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setSimpleListView(ArrayList<String> names) {

//        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListViewController.this, android.R.layout.simple_list_item_1, names);
//        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListViewController.this, android.R.layout.simple_list_item_1, android.R.id.text1, names);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListViewController.this, R.layout.simple_list_item, R.id.textView3, names);
        simpleListView.setAdapter(adapter);

        simpleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = adapter.getItem(position);
                Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setCustomListView(ArrayList<String> names, ArrayList<String> teams, ArrayList<String> imageurls) {
        final ListDataAdapter adapter = new ListDataAdapter(getApplicationContext(), names, teams, imageurls);
        customListView.setAdapter(adapter);
    }

    public void nextPage(View view) {
        Intent i=new Intent (this,GridViewController.class);
        startActivity(i);
    }
}
