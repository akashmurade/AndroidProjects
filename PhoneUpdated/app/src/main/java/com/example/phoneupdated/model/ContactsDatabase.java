package com.example.phoneupdated.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Contact.class}, version = 1)
public abstract class ContactsDatabase extends RoomDatabase {

    public abstract ContactDAO getContactDAO();

    private static ContactsDatabase dbInstance;

    public static synchronized ContactsDatabase getInstance(Context context) {
        if(dbInstance == null) {
            dbInstance = Room.databaseBuilder(context.getApplicationContext(),
                    ContactsDatabase.class, "contacts_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return dbInstance;
    }
}
