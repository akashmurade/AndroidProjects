package com.example.activitycomponents;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

public class OnClickHandler {
    private Context context;

    public OnClickHandler(Context context) {
        this.context = context;
    }

    public void onPlayButtonClick(View view) {
        Toast.makeText(context, "Playing Music", Toast.LENGTH_SHORT).show();
        context.startService(new Intent(context, MusicService.class));
    }

    public void onPauseButtonClick(View view) {
        Toast.makeText(context, "Pausing Music", Toast.LENGTH_SHORT).show();
        context.stopService(new Intent(context, MusicService.class));
    }
}
