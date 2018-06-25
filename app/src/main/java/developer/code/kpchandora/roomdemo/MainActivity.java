package developer.code.kpchandora.roomdemo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends AppCompatActivity implements PersonAdapter.PersonClickListener{

    public static final int NEW_PERSON_ACTIVITY_REQUEST_CODE = 1;
    private static final String TAG = "MainActivity";
    
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private PersonViewModel personViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.floatingActionButton);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        personViewModel = ViewModelProviders.of(this).get(PersonViewModel.class);

        final PersonAdapter adapter = new PersonAdapter(this);
        recyclerView.setAdapter(adapter);

        personViewModel.getAllData().observe(this, new Observer<List<PersonEntity>>() {
            @Override
            public void onChanged(@Nullable List<PersonEntity> people) {
                Log.i(TAG, "onChanged: ");
                adapter.setWord(people);
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewPersonActivity.class);
                startActivityForResult(intent, NEW_PERSON_ACTIVITY_REQUEST_CODE);
            }
        });

    }

    @Override
    public void onPersonClick(PersonEntity personEntity) {
//        personViewModel.deletePerson(personEntity);
        PersonRepository repository = new PersonRepository(getApplication());
        repository.deletePerson(personEntity);
        Toast.makeText(this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_PERSON_ACTIVITY_REQUEST_CODE &&
                resultCode == RESULT_OK) {
            PersonEntity person = new PersonEntity();
            person.setFirstName(data.getStringExtra(NewPersonActivity.PERSON_FIRST_NAME));
            person.setLastName(data.getStringExtra(NewPersonActivity.PERSON_LAST_NAME));
            person.setMobileNumber(data.getStringExtra(NewPersonActivity.PERSON_MOBILE_NUM));
            personViewModel.insert(person);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }

    }*/
}
