package com.example.viewmodels;

import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {
    public MutableLiveData<Integer> counter = new MutableLiveData<>(0);

    public void onIncrease(View view) {
        counter.setValue(counter.getValue() + 1);
    }

    public void onDecrease(View view) {
        counter.setValue(counter.getValue() - 1);
    }
}
