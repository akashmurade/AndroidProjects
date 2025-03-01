package com.example.luckynumber;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText name;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        button = findViewById(R.id.button);

        button.setOnClickListener(v -> {
            if (name.getText().toString().isEmpty()) {
                name.setError("Please enter your name");
            }
            else {
                Intent intent = new Intent(MainActivity.this, RandomNumber.class);
                intent.putExtra("name", name.getText().toString().trim());
                startActivity(intent);
            }
        });
    }
}