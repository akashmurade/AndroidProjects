package com.example.whatsapp;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.whatsapp.Adapter.NavigationAdapter;

public class MainActivity extends AppCompatActivity {
    ViewPager2 fragment_container;
    NavigationAdapter navigationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        navigationAdapter = new NavigationAdapter(getSupportFragmentManager(), getLifecycle());

        navigationAdapter.addFragment(new ChatsFragment());
        navigationAdapter.addFragment(new UpdatesFragment());
        navigationAdapter.addFragment(new CommunitiesFragment());
        navigationAdapter.addFragment(new CallsFragment());

        fragment_container = findViewById(R.id.fragment_container);
        fragment_container.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        fragment_container.setAdapter(navigationAdapter);

        // onclick
        findViewById(R.id.chats).setOnClickListener(v -> fragment_container.setCurrentItem(0, true));

        findViewById(R.id.updates).setOnClickListener(v -> fragment_container.setCurrentItem(1, true));

        findViewById(R.id.communities).setOnClickListener(v -> fragment_container.setCurrentItem(2, true));

        findViewById(R.id.calls).setOnClickListener(v -> fragment_container.setCurrentItem(3, true));

    }
}