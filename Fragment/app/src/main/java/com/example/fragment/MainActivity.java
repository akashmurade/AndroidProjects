package com.example.fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button firstbutton = findViewById(R.id.firstbutton);
        firstbutton.setOnClickListener(v -> loadFragment(new FirstFragment()));

        Button secondbutton = findViewById(R.id.secondbutton);
        secondbutton.setOnClickListener(v -> loadFragment(new SecondFragment()));

        Button thirdbutton = findViewById(R.id.thirdbutton);
        thirdbutton.setOnClickListener(v -> loadFragment(new ThirdFragment()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_home) {
            return true;
        } else if (id == R.id.action_about) {
            return true;
        } else if (id == R.id.action_service) {
            return true;
        } else if (id == R.id.action_career) {
            return true;
        } else if (id == R.id.action_contact) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void loadFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, fragment);
        ft.commit();
    }
}