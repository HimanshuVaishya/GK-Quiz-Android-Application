package com.example.dynamicapplicationtwo.learn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.dynamicapplicationtwo.practice.QuestionModel;
import com.example.dynamicapplicationtwo.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LearnQuestionsActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    private Dialog loadingDialog;

    private String categoryName, setId;
    private RecyclerView recyclerview;
    private LearnQuestionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_questions);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        categoryName = getIntent().getStringExtra("category");
        setId = getIntent().getStringExtra("setId");
        getSupportActionBar().setTitle(categoryName );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerview = findViewById(R.id.recycler_view);

        loadingDialog = new Dialog(this);
        loadingDialog.setContentView(R.layout.loading);
        loadingDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.round_corners));
        loadingDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        loadingDialog.setCancelable(false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        recyclerview.setLayoutManager(layoutManager);
        List<QuestionModel> list = new ArrayList<>();


//        list.add(new QuestionModel("fjgyuuy", "Thw way you sre doing hardWork you will definately become successful:-)","100","100", "100","100","100", "1"));
//        list.add(new QuestionModel("fjgyuuy", "Thw way you sre doing hardWork you will definately become successful:-)","100","100", "100","100","100", "1"));
//        list.add(new QuestionModel("fjgyuuy", "Thw way you sre doing hardWork you will definately become successful:-)","100","100", "100","100","100", "1"));
        adapter = new LearnQuestionAdapter(list,categoryName);
        recyclerview.setAdapter(adapter);

        loadingDialog.show();
        myRef.child("SETS").child(setId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String id = dataSnapshot1.getKey();

                    String question = dataSnapshot1.child("question").getValue().toString();
                    String a = dataSnapshot1.child("optionA").getValue().toString();
                    String b = dataSnapshot1.child("optionB").getValue().toString();
                    String c = dataSnapshot1.child("optionC").getValue().toString();
                    String d = dataSnapshot1.child("optionD").getValue().toString();
                    String correctANS = dataSnapshot1.child("correctANS").getValue().toString();

                    list.add(new QuestionModel(id, question, a, b, c, d, correctANS, setId));

                }
                adapter.notifyDataSetChanged();
                if (list.size() == 0){
                    finish();
                    Toast.makeText(LearnQuestionsActivity.this, "No Question and answer found!!", Toast.LENGTH_SHORT).show();
                }
                loadingDialog.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(LearnQuestionsActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                loadingDialog.dismiss();
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}