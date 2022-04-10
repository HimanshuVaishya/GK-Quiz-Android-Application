package com.example.dynamicapplicationtwo.learn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;

import com.example.dynamicapplicationtwo.R;

import java.util.List;

public class LearnSetsActivity extends AppCompatActivity {

    private GridView gridview;
    private List<String> sets;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_sets);

        gridview = findViewById(R.id.gridview);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getIntent().getStringExtra("title"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sets  = LearnCategoryActivity.list.get(getIntent().getIntExtra("position", 0)).getSets();

        LearnGridAdapter adapter = new LearnGridAdapter(sets
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