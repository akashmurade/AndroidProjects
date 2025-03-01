package com.example.phoneupdated.handler;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.phoneupdated.AddNewContactActivity;

public class MainActivityClickHandler {
    private Context context;

    public MainActivityClickHandler(Context context) {
        this.context = context;
    }

    public void onFABClicked(View view) {
        Intent intent = new Intent(context, AddNewContactActivity.class);
        context.startActivity(intent);
    }
}
