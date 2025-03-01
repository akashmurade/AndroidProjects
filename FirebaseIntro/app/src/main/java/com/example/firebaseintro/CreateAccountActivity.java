package com.example.firebaseintro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class CreateAccountActivity extends AppCompatActivity {

    private TextInputEditText nameInput, emailInput, passwordInput;
    private Button createAccountButton;
    private FirebaseAuth mAuth;
    private TextView signInText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        mAuth = FirebaseAuth.getInstance();

        nameInput = findViewById(R.id.nameInput);
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        createAccountButton = findViewById(R.id.createAccountButton);
        signInText = findViewById(R.id.signInText);

        createAccountButton.setOnClickListener(v -> createAccount());

        signInText.setOnClickListener(v -> {
            startActivity(new Intent(CreateAccountActivity.this, SignInActivity.class));
            finish();
        });
    }

    private void createAccount() {
        String name = nameInput.getText().toString().trim();
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        createAccountButton.setEnabled(false); // Disable button to prevent multiple clicks

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            String fullName = nameInput.getText().toString().trim(); // Get name from input field

                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(fullName) // Set the full name
                                    .build();

                            user.updateProfile(profileUpdates).addOnCompleteListener(updateTask -> {
                                if (updateTask.isSuccessful()) {
                                    Toast.makeText(this, "Account created successfully!", Toast.LENGTH_LONG).show();
                                }
                            });
                        }

                        startActivity(new Intent(CreateAccountActivity.this, SignInActivity.class));
                        finish();
                    } else {
                        Toast.makeText(this, "Account creation failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });


    }
}
