package com.example.phonedb;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private PhoneViewModel mPhoneViewModel;
    private PhoneListAdapter mAdapter;
    private FloatingActionButton fab_;
    private ActivityResultLauncher<Intent> mActivityResultLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab_ = findViewById(R.id.fabMain);
        fab_.setOnClickListener(v -> {
            startSecondActivity();
        });
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        mAdapter = new PhoneListAdapter(this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mPhoneViewModel = new ViewModelProvider(this).get(PhoneViewModel.class);
        mPhoneViewModel.getAllPhones().observe(this,
                elements -> {
                    mAdapter.setPhoneList(elements);
                });
        mActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                //w <> typ wyniku - tutaj ActivityResult, może
                //też być Uri
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(
                            ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            Intent resultIntent = result.getData();
                            System.out.println(resultIntent.getStringExtra(InsertActivity.RESULT_WEBSITE));
                            System.out.println(resultIntent.getStringExtra(InsertActivity.RESULT_MODEL));
                            System.out.println(resultIntent.getStringExtra(InsertActivity.RESULT_MANUFACTOR));
                            System.out.println(resultIntent.getStringExtra(InsertActivity.RESULT_ANDRVER));
                        }}});
    }
    private void startSecondActivity() {
        Intent intent = new Intent(this, InsertActivity.class);
        mActivityResultLauncher.launch(intent);
    }
}