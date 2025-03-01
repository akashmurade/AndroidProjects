package com.example.spotify;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ViewDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView username = findViewById(R.id.user);
        TextView password = findViewById(R.id.pwd);
        TextView courses = findViewById(R.id.courses);
        TextView gender = findViewById(R.id.viewgender);
        TextView address = findViewById(R.id.viewaddress);
        TextView dob = findViewById(R.id.dob);
        Button mainpage = findViewById(R.id.mainpage);
        ImageView image = findViewById(R.id.image);

        String usernameText = getIntent().getStringExtra("username");
        String passwordText = getIntent().getStringExtra("password");
        String musicTypes = getIntent().getStringExtra("musicTypes");
        String genderText = getIntent().getStringExtra("gender");
        String addressText = getIntent().getStringExtra("address");
        String dobText = getIntent().getStringExtra("DOB");
        String photoUriString = getIntent().getStringExtra("photoUri");

        mainpage.setOnClickListener( v -> {
            // on click direct to the main page
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        if (photoUriString != null) {
            Uri photoUri = Uri.parse(photoUriString);
            image.setImageURI(photoUri);
        }

        username.setText(getFallbackText(usernameText, "No username provided"));
        password.setText(getFallbackText(passwordText, "No password provided"));
        courses.setText(getFallbackText(musicTypes, "No music types selected"));
        gender.setText(getFallbackText(genderText, "No gender selected"));
        address.setText(getFallbackText(addressText, "No address provided"));
        dob.setText(getFallbackText(dobText, "No date of birth provided"));
    }

    private String getFallbackText(String input, String fallback) {
        return input != null ? input : fallback;
    }

}