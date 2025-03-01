package com.example.transactiontrack;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.transactiontrack.databinding.ActivityAddNewTransactionBinding;
import com.example.transactiontrack.handler.AddTransactionHandler;
import com.example.transactiontrack.model.Transaction;
import com.example.transactiontrack.viewmodel.TransactionViewModel;

public class AddNewTransaction extends AppCompatActivity {

    private AddTransactionHandler handlers;
    private ActivityAddNewTransactionBinding addTransactionBinding;
    private Transaction transaction;
    private TransactionViewModel transactionViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Set up Data Binding
        addTransactionBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_new_transaction);

        // Initialize ViewModel properly
        transactionViewModel = new ViewModelProvider(this).get(TransactionViewModel.class);

        // Initialize empty Transaction object
        transaction = new Transaction("", "", "Cash"); // Default method as "Cash"

        // Initialize Handler
        handlers = new AddTransactionHandler(transaction, this, transactionViewModel);

        // Bind objects to XML
        addTransactionBinding.setTransaction(transaction);
        addTransactionBinding.setAddTransactionHandler(handlers);

        // Setup Spinner
        setupMethodSpinner();

        // Handle system UI insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void setupMethodSpinner() {
        // Define options for the dropdown
        String[] methods = {"Cash", "Bank Transfer", "Credit Card"};

        // Create ArrayAdapter with predefined methods
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, methods);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set adapter to Spinner
        addTransactionBinding.spinnerMethod.setAdapter(adapter);

        // Set default selection
        addTransactionBinding.spinnerMethod.setSelection(0); // Default: Cash

        // Handle selection event
        addTransactionBinding.spinnerMethod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                transaction.setMethod(methods[position]); // Update transaction method
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                transaction.setMethod("Cash"); // Default if nothing selected
            }
        });
    }
}
