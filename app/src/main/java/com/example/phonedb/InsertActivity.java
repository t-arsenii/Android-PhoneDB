package com.example.phonedb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class InsertActivity extends AppCompatActivity {

    Button btn_website;
    Button btn_cancel;
    Button btn_save;
    EditText et_manufactor;
    EditText et_model;
    EditText et_andrver;
    EditText et_website;
    public static final String RESULT_MANUFACTOR = "";
    public static final String RESULT_MODEL = "";
    public static final String RESULT_ANDRVER = "";
    public static final String RESULT_WEBSITE = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        btn_website = findViewById(R.id.button_WebSite);
        btn_cancel = findViewById(R.id.button_Cancel);
        btn_save = findViewById(R.id.button_Save);
        et_manufactor = findViewById(R.id.EditText_Manufactor);
        et_model = findViewById(R.id.EditText_Model);
        et_andrver = findViewById(R.id.EditText_AndroidVersion);
        et_website = findViewById(R.id.EditText_WebSite);

        btn_cancel.setOnClickListener(v -> {
            returnToMain();
        });
        btn_save.setOnClickListener(v -> {
            returnToMainSave();
        });
    }
    private void returnToMain() {
        finish();
    }
    private void returnToMainSave() {
        Intent intent = new Intent();
        intent.putExtra(RESULT_MANUFACTOR, et_manufactor.getText().toString());
        intent.putExtra(RESULT_MODEL, et_model.getText().toString());
        intent.putExtra(RESULT_ANDRVER, et_andrver.getText().toString());
        intent.putExtra(RESULT_WEBSITE, et_website.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }

}