package com.example.phoneupdated;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.phoneupdated.databinding.ActivityAddNewContactBinding;
import com.example.phoneupdated.handler.AddNewContactClickHandler;
import com.example.phoneupdated.model.Contact;
import com.example.phoneupdated.viewmodel.MyViewModel;

public class AddNewContactActivity extends AppCompatActivity {


    private AddNewContactClickHandler handler;
    private ActivityAddNewContactBinding binding;
    private Contact contacts;
    MyViewModel myViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_new_contact);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        contacts = new Contact("", "", "");

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_new_contact);
        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        handler = new AddNewContactClickHandler(contacts, this, myViewModel);
        binding.setContact(contacts);
        binding.setClickHandler(handler);

    }
}