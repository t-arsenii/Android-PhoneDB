package com.example.phonedb;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PhoneDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    public void insert(Phone element);

    @Query("DELETE FROM Phone")
    void deleteAll();
    @Query("DELETE FROM Phone WHERE id = :phoneId")
    void deleteById(long phoneId);
//    @Delete
//    void delete(Phone phone);
    @Update
    void update(Phone phone);
    //metoda zwraca listę elementów opakowaną w pojemnik live
    //data pozwalający na odbieranie
    //powiadomień o zmianie danych. Room wykonuje zapytanie
    //w innym wątku
    //live data powiadamia obserwatora w głównym wątku aplikacji
    @Query("SELECT * FROM Phone ORDER BY producent ASC")
    LiveData<List<Phone>> getAlphabetizedElements();
}
