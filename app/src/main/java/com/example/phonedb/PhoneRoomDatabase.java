package com.example.phonedb;

import android.provider.ContactsContract;

import androidx.room.Database;

@Database(entities = {ContactsContract.CommonDataKinds.Phone.class}, version = 1, exportSchema = false)
public abstract class PhoneRoomDatabase {

}
