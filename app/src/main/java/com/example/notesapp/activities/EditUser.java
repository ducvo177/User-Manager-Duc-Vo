package com.example.notesapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.notesapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EditUser extends AppCompatActivity {

    Intent data;
    EditText meditnameofuser, meditemailofuser;
    FloatingActionButton msaveedituser;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edituseractivity);
        meditnameofuser = findViewById(R.id.editnameofuser);
        meditemailofuser = findViewById(R.id.editemailofuser);
        msaveedituser = findViewById(R.id.saveedituser);

        data = getIntent();

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        Toolbar toolbar = findViewById(R.id.toolbarofedituser);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        msaveedituser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newname = meditnameofuser.getText().toString();
                String newemail = meditemailofuser.getText().toString();

                if (newname.isEmpty() || newemail.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Something is empty", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    DocumentReference documentReference = firebaseFirestore.collection("users").document(firebaseUser.getUid()).collection("myUsers").document(data.getStringExtra("noteId"));
                    Map<String, Object> user = new HashMap<>();
                    user.put("name", newname);
                    user.put("email", newemail);
                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getApplicationContext(), "User is updated", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(EditUser.this, UsersActivity.class));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Failed to update", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });

        String username = data.getStringExtra("name");
        String useremail = data.getStringExtra("email");
        meditemailofuser.setText(useremail);
        meditnameofuser.setText(username);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}