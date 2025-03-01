package com.example.databinding;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class OnClickHandler {
    Context context;
    public OnClickHandler(Context context) {
        this.context = context;
    }

    public void onButtonClick(View view) {
        Toast.makeText(context, "Button Clicked", Toast.LENGTH_SHORT).show();
    }

}
