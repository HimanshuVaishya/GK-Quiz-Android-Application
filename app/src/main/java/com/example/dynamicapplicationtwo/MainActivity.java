package com.example.dynamicapplicationtwo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.dynamicapplicationtwo.bookmark.BookmarkActivity;
import com.example.dynamicapplicationtwo.chatapp.Activities.SignInActivity;
import com.example.dynamicapplicationtwo.feedback.FeedbackActivity;
import com.example.dynamicapplicationtwo.learn.LearnCategoryActivity;
import com.example.dynamicapplicationtwo.practice.CategoriesActivity;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private LinearLayout learn, practice, bookmark, chat;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        move();

    }

    private void init(){
        learn = findViewById(R.id.learn);
        practice = findViewById(R.id.practice);
        bookmark = findViewById(R.id.bookmark);
        chat = findViewById(R.id.chat);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_open, R.string.navigation_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavCall();
    }

    private void move(){
        learn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LearnCategoryActivity.class);
            startActivity(intent);
        });

        practice.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CategoriesActivity.class);
            startActivity(intent);
        });

        bookmark.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BookmarkActivity.class);
            startActivity(intent);
        });

        chat.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SignInActivity.class);
            startActivity(intent);
        });


    }

    private void NavCall(){

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                switch (id) {
                    case R.id.learn_menu:
                        Intent j = new Intent(getApplicationContext(), LearnCategoryActivity.class);
                        startActivity(j);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.practice_menu:
                        Intent k = new Intent(getApplicationContext(), CategoriesActivity.class);
                        startActivity(k);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.chat_menu:
                        Intent l = new Intent(getApplicationContext(), SignInActivity.class);
                        startActivity(l);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.share_menu:
                        Intent m = new Intent(Intent.ACTION_SEND);
                        m.setType("text/plain");
                        String shareBody = "GK Quiz Application\n"+ "https://play.google.com/store/apps\n"
                                +"This GK Quiz application good for your Knowledge Enhancement";
                        m.putExtra(Intent.EXTRA_TEXT, shareBody);
                        startActivity(Intent.createChooser(m, "Share via"));
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.feedback_menu:
                        Intent n = new Intent(getApplicationContext(), FeedbackActivity.class);
                        startActivity(n);
                        drawerLayout.closeDrawers();
                        break;
                }
                return false;
            }
        });
    }
}