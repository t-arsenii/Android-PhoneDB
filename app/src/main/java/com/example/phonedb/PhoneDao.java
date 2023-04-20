package com.example.phonedb;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PhoneDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    public void insert(Phone element);
    @Query("DELETE FROM Phone")
    void deleteAll();
    //metoda zwraca listę elementów opakowaną w pojemnik live
    //data pozwalający na odbieranie
    //powiadomień o zmianie danych. Room wykonuje zapytanie
    //w innym wątku
    //live data powiadamia obserwatora w głównym wątku aplikacji
    @Query("SELECT * FROM Phone ORDER BY producent ASC")
    LiveData<List<Phone>> getAlphabetizedElements();
}
