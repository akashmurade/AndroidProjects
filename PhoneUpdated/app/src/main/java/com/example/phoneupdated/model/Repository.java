package com.example.phoneupdated.model;

import android.app.Application;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class Repository {
    private final ContactDAO contactDAO;
    ExecutorService executorService;
    Handler handler;

    public Repository(Application application) {
        ContactsDatabase contactDatabase = ContactsDatabase.getInstance(application);
        contactDAO = contactDatabase.getContactDAO();
        executorService = Executors.newSingleThreadExecutor();
//        handler = new Handler(Looper.getMainLooper());
    }

    public void addContact(Contact contact) {
        executorService.execute(() -> contactDAO.insert(contact));
    }

    public void deleteContact(Contact contact) {
        executorService.execute(() -> contactDAO.delete(contact));
    }

    public LiveData<List<Contact>> getAllContacts() {
        return contactDAO.getAllContacts();
    }
}
