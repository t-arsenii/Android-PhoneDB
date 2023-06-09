package com.example.phonedb;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PhoneRepository {
    private PhoneDao mPhoneDao;
    private LiveData<List<Phone>> mAllPhones;


    PhoneRepository(Application application) {
        PhoneRoomDatabase phoneRoomDatabase = PhoneRoomDatabase.getDatabase(application);
        mPhoneDao =  phoneRoomDatabase.PhoneDao();
        mAllPhones = mPhoneDao.getAlphabetizedElements();
    }
    LiveData<List<Phone>> getAllElements() {
        return mAllPhones;
    }
    void deleteAll() {
        PhoneRoomDatabase.databaseWriteExecutor.execute(() -> {
            mPhoneDao.deleteAll();
        });
    }
    void delete(long id) {
        PhoneRoomDatabase.databaseWriteExecutor.execute(() -> {
            mPhoneDao.deleteById(id);
        });
    }
    void insert(Phone element) {
        PhoneRoomDatabase.databaseWriteExecutor.execute(() -> {
            mPhoneDao.insert(element);
        });
    }
    void update(Phone element) {
        PhoneRoomDatabase.databaseWriteExecutor.execute(() -> {
            mPhoneDao.update(element);
        });
    }
}
