package com.example.phoneupdated;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phoneupdated.databinding.MainItemListBinding;
import com.example.phoneupdated.model.Contact;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ContactViewHolder>{
    private ArrayList<Contact> contactsArrayList;

    public MyAdapter(ArrayList<Contact> contactsArrayList) {
        this.contactsArrayList = contactsArrayList;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MainItemListBinding contactListItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.main_item_list,
                parent,
                false
        );
        return new ContactViewHolder(contactListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact currentContact = contactsArrayList.get(position);
        holder.contactListItemBinding.setContact(currentContact);
    }

    @Override
    public int getItemCount() {
        return (contactsArrayList == null) ? 0 : contactsArrayList.size();
    }

    public void setContactsArrayList(ArrayList<Contact> contactsArrayList) {
        this.contactsArrayList = contactsArrayList;
        notifyDataSetChanged();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder {
        private MainItemListBinding contactListItemBinding;

        public ContactViewHolder(@NonNull MainItemListBinding contactListItemBinding) {
            super(contactListItemBinding.getRoot());
            this.contactListItemBinding = contactListItemBinding;
        }
    }
}
