package com.example.phonedb;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private PhoneViewModel mPhoneViewModel;
    private PhoneListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        mAdapter = new PhoneListAdapter(this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mPhoneViewModel = new ViewModelProvider(this).get(PhoneViewModel.class);
        mPhoneViewModel.getAllPhones().observe(this,
                elements -> {
                    mAdapter.setPhoneList(elements);
                });

    }
}