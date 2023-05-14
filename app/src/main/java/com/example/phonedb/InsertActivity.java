package com.example.phonedb;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class InsertActivity extends AppCompatActivity {

    Button btn_website;
    Button btn_cancel;
    Button btn_save;
    EditText et_manufactor;
    EditText et_model;
    EditText et_andrver;
    EditText et_website;
    private boolean is_producer = false;
    private boolean is_model = false;
    private boolean is_aver = false;
    private boolean is_url= false;

    public static final String RESULT_PHONE = "result_phone";
    public static final int RESULT_SAVE = 1;
    public static final int RESULT_UPDATE = 2;
    Phone element = null;
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
        element = (Phone) getIntent().getSerializableExtra(MainActivity.RESULT_PHONEUPDATE);
        CheckEditText();
        System.out.println(element);
        btn_cancel.setOnClickListener(v -> {
            finish();
        });
        btn_website.setOnClickListener(v->{
            openUrl();
        });
        et_manufactor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(et_manufactor.getText().toString().isEmpty()){
                    is_producer = false;
                    return;
                }
                is_producer = true;

            }

            @Override
            public void afterTextChanged(Editable editable) {
                CheckEditText();
            }
        });
        et_manufactor.setOnFocusChangeListener(
                new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if(!is_producer && !hasFocus){
                            et_manufactor.setError(getString(R.string.error_producer));
                        };
                    }
                }
        );
        et_website.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(et_website.getText().toString().isEmpty() || !et_website.getText().toString().startsWith("http://")){
                    is_url = false;
                    return;
                }
                is_url = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {
                CheckEditText();
            }
        });
        et_website.setOnFocusChangeListener(
                new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if(!is_url && !hasFocus){
                            et_website.setError(getString(R.string.error_website));
                        };
                    }
                }
        );
        et_andrver.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(et_andrver.getText().toString().isEmpty()){
                    is_aver = false;
                    return;
                }
                is_aver = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {
                CheckEditText();
            }
        });
        et_andrver.setOnFocusChangeListener(
                new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if(!is_aver && !hasFocus){
                            et_andrver.setError(getString(R.string.error_aver));
                        };
                    }
                }
        );
        et_model.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(et_model.getText().toString().isEmpty()){
                    is_model = false;
                    return;
                }
                is_model = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {
                CheckEditText();
            }
        });
        et_model.setOnFocusChangeListener(
                new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if(!is_model && !hasFocus){
                            et_model.setError(getString(R.string.error_model));
                        };
                    }
                }
        );
        if(element == null){
            btn_save.setOnClickListener(v -> {
                returnToMainSave();
            });
            return;
        }
        et_manufactor.setText(element.getProducent());
        et_model.setText(element.getModel());
        et_andrver.setText(String.valueOf(element.getA_version()));
        et_website.setText(element.getUrl());
        btn_save.setOnClickListener(v -> {
            returnToMainUpdate();
        });
    }
    private void returnToMainSave() {
        Intent intent = new Intent();
        String producent = et_manufactor.getText().toString();
        String model = et_model.getText().toString();
        int a_ver = Integer.parseInt(et_andrver.getText().toString());
        String url =  et_website.getText().toString();
        Phone element = new Phone(producent,model,a_ver,url);
        intent.putExtra(RESULT_PHONE, element);
        setResult(RESULT_SAVE, intent);
        finish();
    }
    private void returnToMainUpdate() {
        Intent intent = new Intent();
        element.setA_version(Integer.parseInt(et_andrver.getText().toString()));
        element.setModel(et_model.getText().toString());
        element.setProducent(et_manufactor.getText().toString());
        element.setUrl(et_website.getText().toString());
        intent.putExtra(RESULT_PHONE, element);
        setResult(RESULT_UPDATE, intent);
        finish();
    }
    private void openUrl(){
        Intent webBrowser = new Intent("android.intent.action.VIEW", Uri.parse(et_website.getText().toString()));
        startActivity(webBrowser);
    }
    private boolean isManufacturerValid() {
        String manufacturer = et_manufactor.getText().toString();
        if (manufacturer.isEmpty()) {
            et_manufactor.setError(getString(R.string.error_producer));
            return false;
        }
        return true;
    }

    protected void CheckEditText(){
        if(is_aver && is_url && is_producer && is_model){
            btn_save.setEnabled(true);
            return;
        }
        btn_save.setEnabled(false);
    }
}