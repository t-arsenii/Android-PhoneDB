package com.example.phonedb;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Phone")
public class PhoneModel {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;

    @NonNull
    @ColumnInfo(name = "producent")
    private String producent;

    @NonNull
    @ColumnInfo(name = "model")
    private String model;
    @NonNull
    @ColumnInfo(name = "android_version")
    private int a_version;
    @NonNull
    @ColumnInfo(name = "url")
    private String url;

    public PhoneModel(@NonNull String producent, @NonNull String model, int a_version, @NonNull String url) {
        this.producent = producent;
        this.model = model;
        this.a_version = a_version;
        this.url = url;
    }
    //jeżeli konieczne są dodatkowe konstruktory należy
    //je poprzedzić adnotacją @Ignore
    //żeby biblioteka Room z nich nie korzystała
    //Room może wymagać również getterów i setterów także
    //warto je utworzyć
}
