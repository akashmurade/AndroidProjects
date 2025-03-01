package com.example.phoneupdated;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phoneupdated.databinding.ActivityMainBinding;
import com.example.phoneupdated.handler.MainActivityClickHandler;
import com.example.phoneupdated.model.Contact;
import com.example.phoneupdated.model.ContactDAO;
import com.example.phoneupdated.model.ContactsDatabase;
import com.example.phoneupdated.viewmodel.MyViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ContactsDatabase contactsDatabase;
    private ArrayList<Contact> contactsArrayList = new ArrayList<>();
    private MyAdapter myAdapter;
    private ActivityMainBinding mainBinding;
    private MainActivityClickHandler handlers;

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

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        handlers = new MainActivityClickHandler(this);
        mainBinding.setClickHandler(handlers);

        // Recycler View
        RecyclerView recyclerView = mainBinding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        // Database
        contactsDatabase = ContactsDatabase.getInstance(this);

        // View Model
        MyViewModel viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        viewModel.getAllContacts().observe(this, contacts -> {
            contactsArrayList.clear();
            contactsArrayList.addAll(contacts);
            myAdapter.notifyDataSetChanged();
        });

        // Adapter
        myAdapter = new MyAdapter(contactsArrayList);
        recyclerView.setAdapter(myAdapter);

        // swipe to delete
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Contact contact = contactsArrayList.get(viewHolder.getAdapterPosition());
                viewModel.deleteContact(contact);
            }
        }).attachToRecyclerView(recyclerView);

        // swipe to delete
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Contact contact = contactsArrayList.get(viewHolder.getAdapterPosition());
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + contact.getMobileNo()));
                startActivity(intent);
                recyclerView.getAdapter().notifyItemChanged(viewHolder.getAdapterPosition());
            }
        }).attachToRecyclerView(recyclerView);

    }
}