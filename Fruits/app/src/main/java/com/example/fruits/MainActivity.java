package com.example.fruits;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FruitAdapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Fruit[] fruits = {
                new Fruit("Apple", "Crisp and sweet with a refreshing crunch", R.drawable.apple),
                new Fruit("Avocado", "Creamy texture with a rich, nutty flavor", R.drawable.avocado),
                new Fruit("Banana", "Soft, sweet, and packed with energy", R.drawable.banana),
                new Fruit("Blueberry", "Tiny, tangy, and bursting with flavor", R.drawable.blueberry),
                new Fruit("Cherry", "Juicy, sweet, with a hint of tartness", R.drawable.cherry),
                new Fruit("Coconut", "Hard shell with sweet water and rich, fibrous flesh", R.drawable.coconut),
                new Fruit("Dragonfruit", "Exotic, mildly sweet with a soft texture", R.drawable.dragonfruit),
                new Fruit("Grapes", "Juicy and sweet, perfect for snacking", R.drawable.grapes),
                new Fruit("Guava", "Tropical and fragrant with a sweet-tangy flavor", R.drawable.guava),
                new Fruit("Kiwi", "Tangy and refreshing with vibrant green flesh", R.drawable.kiwi),
                new Fruit("Mango", "Rich and tropical, with a luscious, juicy taste", R.drawable.mango),
                new Fruit("Orange", "Citrusy and refreshing, full of vitamin C", R.drawable.orange),
                new Fruit("Papaya", "Soft, sweet, and rich in tropical flavor", R.drawable.papaya),
                new Fruit("Peach", "Juicy, aromatic, with a sweet and slightly tart taste", R.drawable.peach),
                new Fruit("Pear", "Crisp, juicy, with a subtle sweetness", R.drawable.pear),
                new Fruit("Pineapple", "Tart, juicy, with a tropical burst of flavor", R.drawable.pineapple),
                new Fruit("Pomegranate", "Crunchy seeds with a tangy-sweet burst of juice", R.drawable.pomegranate),
                new Fruit("Strawberry", "Sweet, juicy, with a vibrant red color", R.drawable.strawberry),
                new Fruit("Watermelon", "Refreshing, juicy, and perfect for hot days", R.drawable.watermelon)
        };

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FruitAdapter(fruits);
        recyclerView.setAdapter(adapter);


    }
}