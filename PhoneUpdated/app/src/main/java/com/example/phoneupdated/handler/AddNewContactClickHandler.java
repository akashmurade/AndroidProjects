package com.example.phoneupdated.handler;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.phoneupdated.MainActivity;
import com.example.phoneupdated.model.Contact;
import com.example.phoneupdated.viewmodel.MyViewModel;

public class AddNewContactClickHandler {
    Contact contacts;
    Context context;
    MyViewModel myViewModel;

    public AddNewContactClickHandler(Contact contacts, Context context, MyViewModel viewModel) {
        this.contacts = contacts;
        this.context = context;
        this.myViewModel = viewModel;
    }

    public void onSubmitBtnclicked(View view) {
        if(contacts.getName().isEmpty() || contacts.getEmailId().isEmpty() || contacts.getMobileNo().isEmpty()) {
            Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(context, MainActivity.class);

            Contact c = new Contact(contacts.getName(), contacts.getMobileNo(), contacts.getEmailId());
            myViewModel.addContact(c);
            context.startActivity(intent);
        }
    }


}
