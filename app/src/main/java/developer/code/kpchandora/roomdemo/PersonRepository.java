package developer.code.kpchandora.roomdemo;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

public class PersonRepository {

    private static final String TAG = "PersonRepository";
    
    private PersonDao personDao;
    private LiveData<List<PersonEntity>>allPersonList;

    PersonRepository(Application application){
        PersonDatabase database = PersonDatabase.getDatabase(application);
        personDao = database.personDao();
        allPersonList = personDao.getAllPerson();
    }

    LiveData<List<PersonEntity>>getAllPersonList(){
        Log.i(TAG, "getAllPersonList: ");
        return allPersonList;
    }


    public void insert(PersonEntity person){
        Log.i(TAG, "insert: ");
        new InsertAsynTask(personDao).execute(person);
    }

    public void deletePerson(PersonEntity personEntity){
        Log.i(TAG, "deletePerson: ");
        new DeletePersonAsyncTask(personDao).execute(personEntity);
    }

    private static class InsertAsynTask extends AsyncTask<PersonEntity, Void, Void>{

        private PersonDao personDao;

        InsertAsynTask(PersonDao dao){
            personDao = dao;
        }

        @Override
        protected Void doInBackground(PersonEntity... people) {
            Log.i(TAG, "doInBackground: ");
            personDao.insert(people[0]);
            return null;
        }
    }

    private static class DeletePersonAsyncTask extends AsyncTask<PersonEntity, Void, Void>{

        private PersonDao personDao;

        DeletePersonAsyncTask(PersonDao dao){
            personDao = dao;
        }

        @Override
        protected Void doInBackground(PersonEntity... people) {
            Log.i(TAG, "doInBackground: ");
            personDao.deletePerson(people[0]);
            return null;
        }
    }

}
