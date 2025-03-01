package com.example.phoneupdated.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.phoneupdated.model.Contact;
import com.example.phoneupdated.model.Repository;

import java.util.List;

public class MyViewModel extends AndroidViewModel {
    private final Repository myRepository;

    public MyViewModel(@NonNull Application application) {
        super(application);
        this.myRepository = new Repository(application);
    }

    public LiveData<List<Contact>> getAllContacts() {
        return myRepository.getAllContacts();
    }

    public void addContact(Contact contact) {
        myRepository.addContact(contact);
    }

    public void deleteContact(Contact contact) {
        myRepository.deleteContact(contact);
    }

}
