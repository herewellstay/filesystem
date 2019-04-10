package com.herewellstay.filesystem;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@androidx.room.Database(entities = { File.class}, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {
    private static volatile Database INSTANCE;
    private static Callback populateDatabaseCallback= new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            // If you want to keep the data through app restarts,
            // comment out the following line.
            new PopulateDatabaseAsyncTask(INSTANCE).execute();
        }
    };;
    private static Context context;

    public  static Database getDatabase(final Context context) {
        Database.context = context;


        if (INSTANCE == null) {
            synchronized (Database.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),

                            Database.class, "database")
                            //.addCallback(populateDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    public abstract FileDAO fileDAO();


    private static class PopulateDatabaseAsyncTask extends AsyncTask<Void, Void, Void> {

        private final FileDAO fileDAO;

        PopulateDatabaseAsyncTask(Database db) {
            fileDAO = db.fileDAO();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.

            fileDAO.deleteAll();
            return null;
        }
    }
}
