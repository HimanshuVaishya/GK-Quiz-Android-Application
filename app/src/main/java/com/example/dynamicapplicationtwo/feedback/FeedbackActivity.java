package com.example.dynamicapplicationtwo.feedback;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dynamicapplicationtwo.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.UUID;

public class FeedbackActivity extends AppCompatActivity {

    private EditText name, email, feed;
    private Button feedSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        init();

        feedSubmit.setOnClickListener(v -> {
            if (name.getText().toString().isEmpty()) {
                name.setError("Required");
                return;
            }
            if (email.getText().toString().isEmpty()) {
                email.setError("Required");
                return;
            }
            if (feed.getText().toString().isEmpty()) {
                feed.setError("Required");
                return;
            }
            upload();
            name.setText("");
            email.setText("");
            feed.setText("");
            Toast.makeText(FeedbackActivity.this, "Thank you for your valuable feedback", Toast.LENGTH_SHORT).show();

        });

    }

    private void init() {
        name = findViewById(R.id.inputName);
        email = findViewById(R.id.inputEmail);
        feed = findViewById(R.id.inputFeedback);
        feedSubmit = findViewById(R.id.buttonSubmitFeedback);
    }

    private void upload() {


        HashMap<String, Object> map = new HashMap<>();
        map.put("name", name.getText().toString());
        map.put("email", email.getText().toString());
        map.put("feed", feed.getText().toString());

        final String id = UUID.randomUUID().toString();

        FirebaseDatabase.getInstance().getReference()
                .child("Feedback").child(id)
                .setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {

                    new FeedbackModel(id,
                            map.get("name").toString(),
                            map.get("email").toString(),
                            map.get("feed").toString()
                    );
                    finish();

                } else {
                    Toast.makeText(FeedbackActivity.this, "Something went wrong !!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}