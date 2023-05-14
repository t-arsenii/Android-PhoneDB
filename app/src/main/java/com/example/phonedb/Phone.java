package com.example.phonedb;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Phone")
public class Phone implements Serializable {
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

    public Phone(@NonNull String producent, @NonNull String model, int a_version, @NonNull String url) {
        this.producent = producent;
        this.model = model;
        this.a_version = a_version;
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getProducent() {
        return producent;
    }

    public void setProducent(@NonNull String producent) {
        this.producent = producent;
    }

    @NonNull
    public String getModel() {
        return model;
    }

    public void setModel(@NonNull String model) {
        this.model = model;
    }

    public int getA_version() {
        return a_version;
    }

    public void setA_version(int a_version) {
        this.a_version = a_version;
    }

    @NonNull
    public String getUrl() {
        return url;
    }

    public void setUrl(@NonNull String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", producent='" + producent + '\'' +
                ", model='" + model + '\'' +
                ", a_version=" + a_version +
                ", url='" + url + '\'' +
                '}';
    }
}
