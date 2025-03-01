package com.example.phone;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends BaseAdapter
{
    Context context;
    Contact[] contacts;

    public ContactAdapter(Context context, Contact[] contacts) {
        this.context = context;
        this.contacts = contacts;
    }

    @Override
    public int getCount() {
        return contacts.length;
    }

    @Override
    public Object getItem(int position) {
        return contacts[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.spinner, null);
            holder = new ViewHolder();
            holder.name = convertView.findViewById(R.id.name);
            holder.contact = convertView.findViewById(R.id.contact);
            holder.image = convertView.findViewById(R.id.image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(contacts[position].name);
        holder.contact.setText(contacts[position].phoneNo);
        holder.image.setImageResource(contacts[position].image);

        ImageView call = convertView.findViewById(R.id.call);
        call.setOnClickListener(v -> {
            String phoneNumber = "tel:" + contacts[position].phoneNo;  // Ensure the phone number starts with "tel:"
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse(phoneNumber));

            if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                context.startActivity(intent);
            } else {
                ActivityCompat.requestPermissions((Activity) context, new String[]{android.Manifest.permission.CALL_PHONE}, 1);
            }
        });


        return convertView;
    }

    private static class ViewHolder {
        TextView name, contact;
        ImageView image;
    }
}
