package com.example.viewpager;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.viewpager.Adapter.MyViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    ViewPager2 viewpager;
    MyViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        adapter = new MyViewPagerAdapter(
                getSupportFragmentManager(),
                getLifecycle()
        );

        adapter.addFragment(new FirstFragment());
        adapter.addFragment(new SecondFragment());
        adapter.addFragment(new ThirdFragment());

        viewpager = findViewById(R.id.viewpager);
        viewpager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        viewpager.setAdapter(adapter);
    }
}