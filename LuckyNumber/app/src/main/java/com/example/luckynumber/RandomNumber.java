package com.example.luckynumber;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RandomNumber extends AppCompatActivity {
    TextView name, numberOutput;
    Button share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_random_number);

        name = findViewById(R.id.name);
        numberOutput = findViewById(R.id.numberOutput);
        share = findViewById(R.id.share);

        String nameValue = getIntent().getStringExtra("name");
        name.setText(nameValue);

        int randomNumber = (int) (Math.random() * 100);
        numberOutput.setText(String.valueOf(randomNumber));

        share.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Lucky Number");
            intent.putExtra(Intent.EXTRA_TEXT, "Your lucky number is: " + randomNumber);
            startActivity(intent);
        });
    }
}