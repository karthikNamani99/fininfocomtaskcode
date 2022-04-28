package com.d.fininfocomtask.actvities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.d.fininfocomtask.R;
import com.d.fininfocomtask.adapters.MyAdapter;
import com.d.fininfocomtask.models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    MyAdapter myAdapter;
    ArrayList<User> list;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_user_list );

        recyclerView = findViewById( R.id.recyclerList );
        databaseReference = FirebaseDatabase.getInstance().getReference( "users" );
        recyclerView.setHasFixedSize( true );
        recyclerView.setLayoutManager( new LinearLayoutManager( this ) );
        add = findViewById( R.id.addUser );
//
        add.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getApplicationContext(), RegistrationActivty.class );
                intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
                startActivity( intent );
            }
        } );
        list = new ArrayList<>();
        myAdapter = new MyAdapter( this, list );
        recyclerView.setAdapter( myAdapter );

        databaseReference.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    User user = dataSnapshot.getValue( User.class );

                    list.add( user );
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );

    }
}