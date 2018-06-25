package developer.code.kpchandora.roomdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewPersonActivity extends AppCompatActivity {

    public static final String PERSON_FIRST_NAME = "person_first_name";
    public static final String PERSON_LAST_NAME = "person_last_name";
    public static final String PERSON_MOBILE_NUM = "person_mobile_num";

    private EditText firstNameEditText, lastNameEditText, mobileNumEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_person);

        firstNameEditText = findViewById(R.id.person_first_name);
        lastNameEditText = findViewById(R.id.person_last_name);
        mobileNumEditText = findViewById(R.id.person_mobile_num);

        final Button saveButton = findViewById(R.id.button_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent();
                if (TextUtils.isEmpty(firstNameEditText.getText()) || TextUtils.isEmpty(lastNameEditText.getText())
                        || TextUtils.isEmpty(mobileNumEditText.getText())) {
                    Toast.makeText(NewPersonActivity.this, "Fill all data", Toast.LENGTH_SHORT).show();
                } else {

//                    intent.putExtra(PERSON_FIRST_NAME, firstName);
//                    intent.putExtra(PERSON_LAST_NAME, lastName);
//                    intent.putExtra(PERSON_MOBILE_NUM, mobileNum);


                    PersonRepository repository = new PersonRepository(getApplication());
                    repository.insert(getPerson());

                   // setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
    private PersonEntity getPerson(){
        String firstName = firstNameEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();
        String mobileNum = mobileNumEditText.getText().toString();

        PersonEntity person = new PersonEntity();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setMobileNumber(mobileNum);

        return person;
    }
}
