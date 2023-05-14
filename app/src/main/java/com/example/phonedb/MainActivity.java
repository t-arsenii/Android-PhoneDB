package com.example.phonedb;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements PhoneListAdapter.OnItemClickListener  {
    private PhoneViewModel mPhoneViewModel;
    private PhoneListAdapter mAdapter;
    private FloatingActionButton fab_;
    private ActivityResultLauncher<Intent> mActivityResultLauncher;
    public static final String RESULT_PHONEUPDATE = "result_phoneupdate";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPhoneViewModel = new ViewModelProvider(this).get(PhoneViewModel.class);

        fab_ = findViewById(R.id.fabMain);
        fab_.setOnClickListener(v -> {
            startSecondActivity(null);
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT |
                ItemTouchHelper.RIGHT)
        {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder
                                         viewHolder, int direction) {
                int adapterPosition = viewHolder.getAdapterPosition();
                mPhoneViewModel.delete(mPhoneViewModel.getAllPhones().getValue().get(adapterPosition).getId());

            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        itemTouchHelper.attachToRecyclerView(recyclerView);

        mAdapter = new PhoneListAdapter(this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mPhoneViewModel.getAllPhones().observe(this,
                elements -> {
                    mAdapter.setPhoneList(elements);
                });


        mActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(
                            ActivityResult result) {
                        if (result.getResultCode() == InsertActivity.RESULT_SAVE) {
                            Intent resultIntent = result.getData();

                            Phone element = (Phone) resultIntent.getSerializableExtra(InsertActivity.RESULT_PHONE);
                            mPhoneViewModel.insert(element);
                        }
                        if(result.getResultCode() == InsertActivity.RESULT_UPDATE){
                            Intent resultIntent = result.getData();
                            Phone element = (Phone) resultIntent.getSerializableExtra(InsertActivity.RESULT_PHONE);
                            mPhoneViewModel.update(element);
                        }

                    }});
    }
    private void startSecondActivity(Phone phone) {
        Intent intent = new Intent(this, InsertActivity.class);
        intent.putExtra(RESULT_PHONEUPDATE, phone);
        mActivityResultLauncher.launch(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.deleteAll) {
            mPhoneViewModel.deleteAll();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClickListener(Phone element) {
        startSecondActivity(element);
    }
}