package com.example.phonedb;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PhoneViewModel extends AndroidViewModel {
    private final PhoneRepository mRepository;
    private final LiveData<List<Phone>> mAllElements;
    public PhoneViewModel(@NonNull Application application)
    {
        super(application);
        mRepository = new PhoneRepository(application);
        mAllElements = mRepository.getAllElements();
    }
    LiveData<List<Phone>> getAllPhones() {
        return mAllElements;
    }
    public void deleteAll() {
        mRepository.deleteAll();
    }
    public void delete(long id) {
        mRepository.delete(id);
    }
    public void insert(Phone elem){mRepository.insert(elem);}
    public void update(Phone elem){mRepository.update(elem);}
}
