package com.d.fininfocomtask.actvities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.d.fininfocomtask.R;
import com.d.fininfocomtask.models.DataModal;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import io.realm.Realm;

public class UserListAddActivity extends AppCompatActivity {

    EditText userName, userAge, userCity;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Button add_btn;
    private Realm realm;
    String uName, uAge, uCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.useradd_main );
        realm = Realm.getDefaultInstance();

        userName = findViewById( R.id.uName );
        userCity = findViewById( R.id.uCity );
        userAge = findViewById( R.id.uAge );

        add_btn = findViewById( R.id.addBtn );


        add_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getting data from edittext fields.
                uName = userName.getText().toString();
                uAge = userAge.getText().toString();
                uCity = userCity.getText().toString();

                // validating the text fields if empty or not.
                if (TextUtils.isEmpty( uName )) {
                    userName.setError( "Please enter Course Name" );
                } else if (TextUtils.isEmpty( uAge )) {
                    userAge.setError( "Please enter Course Description" );
                } else if (TextUtils.isEmpty( uCity )) {
                    userCity.setError( "Please enter Course Duration" );
                } else {
                    // calling method to add data to Realm database..
                    addDataToDatabase( uName, uAge, uCity );
                    Toast.makeText( UserListAddActivity.this, "User added to database..", Toast.LENGTH_SHORT ).show();
                    userName.setText( "" );
                    userAge.setText( "" );
                    userCity.setText( "" );
                }
            }
        } );

        /*add_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uName = userName.getText().toString();
                uAge = userAge.getText().toString();
                uCity = userCity.getText().toString();


//                UserRegister userRegister = new UserRegister( uName, uAge, uCity );


                try {
                    UserRegister userRegister = new UserRegister( uName, uAge, uCity );

                    firebaseDatabase = FirebaseDatabase.getInstance();
                    databaseReference = firebaseDatabase.getReference( "users" );
//                    String key = databaseReference.child( "users" ).push().getKey();
                    databaseReference.setValue( userRegister );
                } catch (Exception e) {
                    String functionName = Objects.requireNonNull( new Object() {
                    }.getClass().getEnclosingMethod() ).getName();
                    int i = 0;
                }


                CountDownTimer countDownTimer = new CountDownTimer( 3000, 1000 ) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        Intent intent = new Intent( getApplicationContext(), MainActivity.class );
                        intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
                        startActivity( intent );
                    }
                }.start();

            }
        } );*/
    }

    private void addDataToDatabase(String uName, String uAge, String uCity) {
        DataModal modal = new DataModal();

        // on below line we are getting id for the course which we are storing.
        Number id = realm.where( DataModal.class ).max( "id" );

        // on below line we are
        // creating a variable for our id.
        long nextId;

        // validating if id is null or not.
        if (id == null) {
            // if id is null
            // we are passing it as 1.
            nextId = 1;
        } else {
            // if id is not null then
            // we are incrementing it by 1
            nextId = id.intValue() + 1;
        }
        // on below line we are setting the
        // data entered by user in our modal class.
        modal.setId( nextId );
        modal.setuAge( uAge );
        modal.setuCity( uCity );
        modal.setuName( uName );


        // on below line we are calling a method to execute a transaction.
        realm.executeTransaction( new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // inside on execute method we are calling a method
                // to copy to real m database from our modal class.
                realm.copyToRealm( modal );
            }
        } );
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent =new Intent(getApplicationContext(),MainActivity.class);
        intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
        startActivity( intent );

    }
}
