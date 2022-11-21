package com.professionalandroid.apps.firebasetest2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class MovieDisplay extends AppCompatActivity {

    EditText editname;
    EditText editdirector;
    EditText edityear;
    EditText editnation;
    EditText editrating;

    DatabaseReference firebaseDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_display);

        editname = (EditText) findViewById(R.id.editname);
        editdirector = (EditText) findViewById(R.id.editdirector);
        edityear = (EditText) findViewById(R.id.edityear);
        editnation = (EditText) findViewById(R.id.editnation);
        editrating = (EditText) findViewById(R.id.editrating);

        firebaseDB = FirebaseDatabase.getInstance().getReference().child("movies");

        Intent data = getIntent();
        Bundle bundle = data.getExtras();
        int id = bundle.getInt("id");
        String action = bundle.getString("action");

        if (action.equals("update")) {
            DatabaseReference movieElement = firebaseDB.child(Integer.toString(id));
            movieElement.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                    String name = (String)snapshot.child("name").getValue();
                    String director = (String)snapshot.child("director").getValue();
                    String year = (String)snapshot.child("year").getValue();
                    String nation = (String)snapshot.child("nation").getValue();
                    String rating = (String)snapshot.child("rating").getValue();

                    editname.setText(name);
                    editdirector.setText(director);
                    edityear.setText(year);
                    editnation.setText(nation);
                    editrating.setText(rating);
                }

                @Override
                public void onCancelled(@NonNull @NotNull DatabaseError error) {
                }
            });
        }
    }

    public void buttonSave(View view) {
        Intent data = getIntent();
        Bundle bundle = data.getExtras();
        int id = bundle.getInt("id");

        String name = editname.getText().toString();
        String director = editdirector.getText().toString();
        String year = edityear.getText().toString();
        String nation = editnation.getText().toString();
        String rating = editrating.getText().toString();

        firebaseDB.child(Integer.toString(id)).child("id").setValue(Integer.toString(id));
        firebaseDB.child(Integer.toString(id)).child("name").setValue(name);
        firebaseDB.child(Integer.toString(id)).child("director").setValue(director);
        firebaseDB.child(Integer.toString(id)).child("year").setValue(year);
        firebaseDB.child(Integer.toString(id)).child("nation").setValue(nation);
        firebaseDB.child(Integer.toString(id)).child("rating").setValue(rating);

        finish();
    }

    public void buttonUpdate(View view) {
        Intent data = getIntent();
        Bundle bundle = data.getExtras();
        int id = bundle.getInt("id");

        String name = editname.getText().toString();
        String director = editdirector.getText().toString();
        String year = edityear.getText().toString();
        String nation = editnation.getText().toString();
        String rating = editrating.getText().toString();

        firebaseDB.child(Integer.toString(id)).child("id").setValue(Integer.toString(id));
        firebaseDB.child(Integer.toString(id)).child("name").setValue(name);
        firebaseDB.child(Integer.toString(id)).child("director").setValue(director);
        firebaseDB.child(Integer.toString(id)).child("year").setValue(year);
        firebaseDB.child(Integer.toString(id)).child("nation").setValue(nation);
        firebaseDB.child(Integer.toString(id)).child("rating").setValue(rating);

        finish();
    }

    public void buttonDelete(View view) {
        Intent data = getIntent();
        Bundle bundle = data.getExtras();
        int id = bundle.getInt("id");

        DatabaseReference deleteData = firebaseDB.child(Integer.toString(id));
        deleteData.removeValue();

        finish();
    }
}