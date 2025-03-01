package com.example.spotify;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class LoginPage extends AppCompatActivity {
    Button loginBtn;
    EditText username, password;
    TextView upload, dob;
    CheckBox rock, pop;
    Spinner address;
    RadioGroup gender;
    ImageView uploadImage;
    private static final int SELECT_PHOTO_REQUEST_CODE = 1;
    private Uri selectedPhotoUri;
    private Bitmap selectedPhotoBitmap;
    Switch toc;

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(LoginPage.this, (view, year1, month1, dayOfMonth) -> dob.setText(dayOfMonth + "/" + (month1 + 1) + "/" + year1), year, month, day);
        datePickerDialog.show();
    }

    private void uploadPhotograph() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, SELECT_PHOTO_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_PHOTO_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            selectedPhotoUri = data.getData();
            try {
                selectedPhotoBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedPhotoUri);
                uploadImage.setImageBitmap(selectedPhotoBitmap);
            } catch (Exception e) {
                Toast.makeText(this, "Failed to load the photo", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setupSpinner() {
        String[] cities = {
                "Select City", "Pune", "Mumbai", "Delhi", "Bangalore",
                "Chennai", "Hyderabad", "Ahmedabad", "Kolkata", "Surat",
                "Jaipur", "Lucknow", "Kanpur", "Nagpur", "Patna"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(LoginPage.this, R.layout.dropdown, cities);
        adapter.setDropDownViewResource(R.layout.dropdown);
        address.setAdapter(adapter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_page);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        rock = findViewById(R.id.rock);
        pop = findViewById(R.id.pop);
        gender = findViewById(R.id.gender);
        upload = findViewById(R.id.upload);
        toc = findViewById(R.id.tac);
        address = findViewById(R.id.address);
        loginBtn = findViewById(R.id.btn);
        dob = findViewById(R.id.birthDate);
        uploadImage = findViewById(R.id.uploadimage);

        setupSpinner();

        dob.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               showDatePickerDialog();
           }
        });

        upload.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              uploadPhotograph();
          }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleLogin();
            }

            private void handleLogin() {
                String usernameText = username.getText().toString().trim();
                String passwordText = password.getText().toString().trim();
                String selectedAddress = address.getSelectedItem().toString();
                Intent intent = new Intent(LoginPage.this, ViewDetails.class);
                intent.putExtra("username", usernameText);
                intent.putExtra("password", passwordText);

                // Validation
                if (usernameText.isEmpty()) {
                    Toast.makeText(LoginPage.this, "Please enter a username", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (passwordText.isEmpty()) {
                    Toast.makeText(LoginPage.this, "Please enter a password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!rock.isChecked() && !pop.isChecked()) {
                    Toast.makeText(LoginPage.this, "Please select at least one music type", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (gender.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(LoginPage.this, "Please select a gender", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!toc.isChecked()) {
                    Toast.makeText(LoginPage.this, "Please accept the Terms and Conditions", Toast.LENGTH_SHORT).show();
                    return;
                }

                String selectedMusicTypes = "";
                if (rock.isChecked()) {
                    selectedMusicTypes += "Rock, ";
                }
                if (pop.isChecked()) {
                    selectedMusicTypes += "Pop, ";
                }
                if (!selectedMusicTypes.isEmpty()) {
                    selectedMusicTypes = selectedMusicTypes.substring(0, selectedMusicTypes.length() - 2);
                }
                intent.putExtra("musicTypes", selectedMusicTypes);

                // Gender
                int selectedGenderId = gender.getCheckedRadioButtonId();
                if (selectedGenderId != -1) {
                    RadioButton selectedGender = findViewById(selectedGenderId);
                    intent.putExtra("gender", selectedGender.getText().toString());
                }

                // Address
                intent.putExtra("address", selectedAddress);
                intent.putExtra("DOB", dob.getText().toString());

                // Image
                if (selectedPhotoUri != null) {
                    intent.putExtra("photoUri", selectedPhotoUri.toString());
                }

                startActivity(intent);
                finish();
            }
        });
    }
}