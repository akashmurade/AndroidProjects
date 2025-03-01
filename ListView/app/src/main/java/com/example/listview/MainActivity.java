package com.example.listview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ListView listView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        // Data Source
        ArrayList<Planet> planets = new ArrayList<>();

        planets.add(new Planet("Mercury", "Smallest planet, closest to the Sun", R.drawable.mercury));
        planets.add(new Planet("Venus", "Hottest planet with thick atmosphere", R.drawable.venus));
        planets.add(new Planet("Earth", "Our home planet, supports life", R.drawable.earth));
        planets.add(new Planet("Mars", "Known as the Red Planet", R.drawable.mars));
        planets.add(new Planet("Jupiter", "Largest planet with a Great Red Spot", R.drawable.jupiter));
        planets.add(new Planet("Saturn", "Famous for its stunning rings", R.drawable.saturn));
        planets.add(new Planet("Uranus", "Ice giant with a tilted axis", R.drawable.uranus));
        planets.add(new Planet("Neptune", "Blue planet with the strongest winds", R.drawable.neptune));

        MyCustomAdapter adapter = new MyCustomAdapter(this, planets);
        listView.setAdapter(adapter);

    }
}