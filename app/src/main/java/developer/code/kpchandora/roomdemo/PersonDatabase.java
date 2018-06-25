package developer.code.kpchandora.roomdemo;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

@Database(entities = {PersonEntity.class}, version = DbUtils.DB_VERSION)
public abstract class PersonDatabase extends RoomDatabase {

    private static final String TAG = "PersonDatabase";
    
    public abstract PersonDao personDao();

    private static PersonDatabase INSTANCE;

    static PersonDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PersonDatabase.class) {
                if (INSTANCE == null) {
                    Log.i(TAG, "getDatabase: ");
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PersonDatabase.class, DbUtils.DATABASE_NAME)
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this codelab.
                            .fallbackToDestructiveMigration()
//                            .addCallback(callback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
//            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void>{

        private final PersonDao personDao;

//        String[] words = {"dolphin", "crocodile", "cobra"};

        PopulateDbAsync(PersonDatabase db){
            personDao = db.personDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
//            personDao.deleteAll();

//            for (int i = 0; i < words.length; i++){
//                PersonEntity person = new PersonEntity();
//                person.setFirstName(words[i]);
//                personDao.insert(person);
//            }

            return null;
        }
    }

}