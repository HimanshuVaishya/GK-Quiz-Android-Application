package com.example.dynamicapplicationtwo.practice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;

import com.example.dynamicapplicationtwo.R;
import com.example.dynamicapplicationtwo.practice.CategoriesActivity;
import com.example.dynamicapplicationtwo.practice.GridAdapter;

import java.util.List;

public class SetsActivity extends AppCompatActivity {

    private GridView gridview;
    private List<String> sets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sets);

        gridview = findViewById(R.id.gridview);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getIntent().getStringExtra("title"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sets = CategoriesActivity.list.get(getIntent().getIntExtra("position", 0)).getSets();


        GridAdapter adapter = new GridAdapter(sets
                ,getIntent().getStringExtra("title"));
        gridview.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}