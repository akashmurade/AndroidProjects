package com.example.firebaseintro;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private TextView welcomeText, messageText;
    private Button logoutButton;
    private FirebaseAuth mAuth;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase Auth and Database
        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message");

        // Initialize UI components
        welcomeText = findViewById(R.id.welcome_text);
        messageText = findViewById(R.id.message_text);
        logoutButton = findViewById(R.id.logout_button);

        // Fetch and display user's name
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            String userName = user.getDisplayName(); // Get full name set during account creation
            if (userName == null || userName.isEmpty()) {
                userName = user.getEmail();  // Fallback to email if no name is set
            }
            welcomeText.setText("Welcome, " + userName + "!");
        } else {
            welcomeText.setText("No user logged in.");
        }

        // Fetch and display the message from Firebase Realtime Database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String newMessage = snapshot.getValue(String.class);
                messageText.setText(newMessage);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Failed to load message.", Toast.LENGTH_SHORT).show();
            }
        });

        // Logout button functionality
        logoutButton.setOnClickListener(v -> {
            mAuth.signOut();
            startActivity(new Intent(MainActivity.this, SignInActivity.class));
            finish();
        });
    }
}
