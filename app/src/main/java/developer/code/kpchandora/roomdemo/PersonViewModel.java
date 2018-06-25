package developer.code.kpchandora.roomdemo;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

public class PersonViewModel extends AndroidViewModel {

    private static final String TAG = "PersonViewModel";
    
    private PersonRepository repository;
    private LiveData<List<PersonEntity>> listLiveData;

    public PersonViewModel(@NonNull Application application) {
        super(application);
        repository = new PersonRepository(application);
        listLiveData = repository.getAllPersonList();
    }

    public LiveData<List<PersonEntity>> getAllData() {
        Log.i(TAG, "getAllData: ");
        return listLiveData;
    }

    public void insert(PersonEntity person){
        Log.i(TAG, "insert: ");
        repository.insert(person);
    }

    public void deletePerson(PersonEntity personEntity){
        Log.i(TAG, "deletePerson: ");
        repository.deletePerson(personEntity);
    }

}
