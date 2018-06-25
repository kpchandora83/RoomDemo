package developer.code.kpchandora.roomdemo;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface PersonDao {

    @Insert
    void insert(PersonEntity person);

    @Update
    void updatePerson(PersonEntity... people);

    @Delete
    void deletePerson(PersonEntity personEntity);

    @Query("DELETE FROM " + DbUtils.PERSON_TABLE_NAME)
    void deleteAll();

    @Query("SELECT * FROM " + DbUtils.PERSON_TABLE_NAME + " ORDER BY " + DbUtils.PERSON_ID + " ASC")
    LiveData<List<PersonEntity>> getAllPerson();

//    @Query("SELECT * FROM " + DbUtils.PERSON_TABLE_NAME + " WHERE " + DbUtils.PERSON_ID + " LIKE :" + DbUtils.PERSON_ID)
//    List<PersonEntity> findPerson(int id);
}
