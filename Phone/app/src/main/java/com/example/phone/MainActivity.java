package com.example.phone;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ContactAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        listView = findViewById(R.id.listView);

        Contact[] contacts = {
                new Contact(R.drawable.paige, "Paige Spara", "9579202787"),
                new Contact(R.drawable.img, "Christina Chang", "9307747984"),
                new Contact(R.drawable.img_1, "Marcus Andrews", "9876504321"),
                new Contact(R.drawable.img_2, "Jane Smith", "9876543211"),
                new Contact(R.drawable.img4, "Sam Wilson", "9876512345"),
                new Contact(R.drawable.img5, "Emma Brown", "9123465789"),
                new Contact(R.drawable.img6, "Olivia White", "9765432109"),
                new Contact(R.drawable.img7, "Liam Miller", "9087654321"),
                new Contact(R.drawable.img8, "Sophia Davis", "9876543102"),
                new Contact(R.drawable.paige, "Paige Spara", "9123456780"),
                new Contact(R.drawable.img, "Christina Chang", "9876543211"),
                new Contact(R.drawable.img_1, "Marcus Andrews", "9876504321"),
                new Contact(R.drawable.img_2, "Jane Smith", "9876543211"),
                new Contact(R.drawable.img4, "Sam Wilson", "9876512345"),
                new Contact(R.drawable.img5, "Emma Brown", "9123465789"),
                new Contact(R.drawable.img6, "Olivia White", "9765432109"),
                new Contact(R.drawable.img7, "Liam Miller", "9087654321"),
                new Contact(R.drawable.img8, "Sophia Davis", "9876543102"),
        };


        adapter = new ContactAdapter(this, contacts);
        listView.setAdapter(adapter);
    }
}