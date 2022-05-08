package com.d.fininfocomtask.actvities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.d.fininfocomtask.R;

public class LoginActivity extends AppCompatActivity {
    Button login_btn;
    EditText uname_et, password_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.login_main );

        login_btn = (Button) findViewById( R.id.button );
        uname_et = (EditText) findViewById( R.id.editText );
        password_et = (EditText) findViewById( R.id.editText2 );

        login_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uname_et.getText().toString().equals( "Fininfocom" )) {
                    if (password_et.getText().toString().equals( "Fin@123" )) {
                        Toast.makeText( getApplicationContext(),
                                "Redirecting...", Toast.LENGTH_SHORT ).show();
                        Intent intent = new Intent( getApplicationContext(), MainActivity.class );
                        intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
                        startActivity( intent );
                    } else {
                        Toast.makeText( getApplicationContext(), "Password must be 7 Characters with 1UpperCase Alphabet and 1SpecialCharacter and Numeric", Toast.LENGTH_SHORT ).show();
                    }
                } else {
                    Toast.makeText( getApplicationContext(), "Username must be 10 characters ", Toast.LENGTH_SHORT ).show();

                }

            }
        } );


    }
}
