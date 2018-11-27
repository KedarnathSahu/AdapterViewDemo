package com.cumulations.adapterviewdemo.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.cumulations.adapterviewdemo.R;

public class GridViewController extends AppCompatActivity {

    GridView simpleGridView,customGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view_controller);
        simpleGridView=findViewById(R.id.simpleGridView);
        customGridView=findViewById(R.id.customGridView);
    }

    public void nextPage(View view) {
    }
}
