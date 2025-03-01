package com.example.transactiontrack.handler;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.transactiontrack.AddNewTransaction;
import com.example.transactiontrack.viewmodel.TransactionViewModel;

public class RedirectHandler {
    private Context context;
    private TransactionViewModel tvm;

    public RedirectHandler(Context context) {
        this.context = context;
    }

    public void onAddClicked(View view) {
        Intent intent = new Intent(context, AddNewTransaction.class);
        context.startActivity(intent);
    }
}

