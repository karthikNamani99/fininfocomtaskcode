package com.d.fininfocomtask.actvities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.d.fininfocomtask.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivty extends AppCompatActivity {

    EditText userName, userAge, userCity;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Button add_btn;

    String uName, uAge, uCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_registration_activty );
        userName = findViewById( R.id.uName );
        userCity = findViewById( R.id.uCity );
        userAge = findViewById( R.id.uAge );

        add_btn = findViewById( R.id.addBtn );

        add_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uName = userName.getText().toString();
                uAge = userAge.getText().toString();
                uCity = userCity.getText().toString();

                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference( "users" );

                UserRegister userRegister = new UserRegister( uName, uAge, uCity );

                databaseReference.setValue( userRegister );


            }
        } );
    }
}