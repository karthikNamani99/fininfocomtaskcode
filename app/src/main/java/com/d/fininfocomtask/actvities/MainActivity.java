package com.d.fininfocomtask.actvities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.d.fininfocomtask.R;
import com.d.fininfocomtask.adapters.MyAdapter;
import com.d.fininfocomtask.models.DataModal;
import com.d.fininfocomtask.models.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    MyAdapter myAdapter;
    ArrayList<User> list;
    FloatingActionButton add;
    EditText editTextSearch;

    List<DataModal> dataModals;

    // creating variables for realm,
    // recycler view, adapter and our list.
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_user_list );
        editTextSearch = (EditText) findViewById(R.id.editTextSearch);
        recyclerView = findViewById( R.id.recyclerList );
        add = findViewById( R.id.addUser );

        realm = Realm.getDefaultInstance();
        dataModals = new ArrayList<>();
//        databaseReference = FirebaseDatabase.getInstance().getReference( "users" );
        recyclerView.setHasFixedSize( true );
        recyclerView.setLayoutManager( new LinearLayoutManager( this ) );
        list = new ArrayList<>();

        dataModals = realm.where(DataModal.class).findAll();
        // on below line we are adding our list to our adapter class.
        // on below line we are setting layout manager to our recycler view.
        // at last we are setting adapter to our recycler view.
        myAdapter = new MyAdapter( this, dataModals );
        recyclerView.setAdapter( myAdapter );


        editTextSearch.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence query, int start, int before, int count) {

                query = query.toString().toLowerCase();

                final List<DataModal> filteredList = new ArrayList<>();

                for (int i = 0; i < dataModals.size(); i++) {

                    final String text = dataModals.get(i).getuCity().toLowerCase();
                    if (text.contains(query)) {

                        filteredList.add(dataModals.get(i));

                    }
                }

                for (int i = 0; i < dataModals.size(); i++) {

                    final String text = dataModals.get(i).getuAge().toLowerCase();
                    if (text.contains(query)) {

                        filteredList.add(dataModals.get(i));
                    }
                }
//
                for (int i = 0; i < dataModals.size(); i++) {

                    final String text = dataModals.get(i).getuName().toLowerCase();
                    if (text.contains(query)) {

                        filteredList.add(dataModals.get(i));
                    }
                }
                 // data set changed
                // data set changed

                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                myAdapter = new MyAdapter( MainActivity.this, filteredList );
                recyclerView.setAdapter(myAdapter);
                myAdapter.notifyDataSetChanged();
            }
        });
        /*databaseReference.addValueEventListener( new ValueEventListener() {
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
        } );*/

        add.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getApplicationContext(), UserListAddActivity.class );
                intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
                startActivity( intent );
            }
        } );

    }
}