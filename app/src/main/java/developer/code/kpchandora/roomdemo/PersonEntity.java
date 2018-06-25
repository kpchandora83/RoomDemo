package developer.code.kpchandora.roomdemo;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = DbUtils.PERSON_TABLE_NAME)
public class PersonEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DbUtils.PERSON_ID)
    private int uid;

    @ColumnInfo(name = DbUtils.FIRST_NAME_COLUMN)
    private String firstName;

    @ColumnInfo(name = DbUtils.LAST_NAME_COLUMN)
    private String lastName;

    @ColumnInfo(name = DbUtils.PERSON_MOBILE_NUMBER)
    private String mobileNumber;

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
