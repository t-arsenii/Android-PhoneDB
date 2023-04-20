package com.example.phonedb;

import android.content.Context;
import android.provider.ContactsContract;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {ContactsContract.CommonDataKinds.Phone.class}, version = 1, exportSchema = false)
public abstract class PhoneRoomDatabase extends RoomDatabase {
    public abstract PhoneDao PhoneDao();
    private static volatile PhoneRoomDatabase INSTANCE;
    static PhoneRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PhoneRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                                    PhoneRoomDatabase.class,
                                    "PhoneDB")
                            .addCallback(sRoomDatabaseCallback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                PhoneDao dao = INSTANCE.PhoneDao();

                dao.insert(new Phone("Samsung", "S", 23, "https://www.samsung.com/pl/smartphones/galaxy-s23/buy/?cid=pl_ppc_google.com_SEM-Permanent-IM-2023-MX-Diamond-2023-Launch-Warm_20230217_Smartfony_19637507657%7c152513229344%7csmartphone%20samsung%7cb%7cg%7c%7cc&gad=1&gclid=Cj0KCQjwxYOiBhC9ARIsANiEIfagIznYWtQkBkqhyLxTNldACFVpSx6e0igUnSAdp9LJnbMXELcjeo0aAmgyEALw_wcB&gclsrc=aw.ds"));
                dao.insert(new Phone("Xiaomi", "A", 10, "https://mi-store.pl/main-eng.html?gclid=Cj0KCQjwxYOiBhC9ARIsANiEIfb9ALPIDm3YZHAk-nhNf8vSe3h1Xz2ryDTNHWJjJpqit-WZ09PX2r4aAu9JEALw_wcB"));
                dao.insert(new Phone("IPhone", "X", 11, "https://www.apple.com/pl/iphone/"));
            });
        }
    };
}
