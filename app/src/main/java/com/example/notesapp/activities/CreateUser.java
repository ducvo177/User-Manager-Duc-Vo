package com.example.notesapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

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

public class CreateUser extends AppCompatActivity {

    EditText mcreatenameofuser, mcreateemailofuser;
    FloatingActionButton msaveuser;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore firebaseFirestore;

    ProgressBar mprogressbarofcreateuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createuser);

        msaveuser = findViewById(R.id.saveuser);
        mcreateemailofuser = findViewById(R.id.createemailofuser);
        mcreatenameofuser = findViewById(R.id.createnameofuser);

        mprogressbarofcreateuser = findViewById(R.id.progressbarofcreateuser);
        Toolbar toolbar = findViewById(R.id.toolbarofcreateuser);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        msaveuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mcreatenameofuser.getText().toString();
                String email = mcreateemailofuser.getText().toString();
                if (name.isEmpty() || email.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Both field are require", Toast.LENGTH_SHORT).show();
                } else {

                    mprogressbarofcreateuser.setVisibility(View.VISIBLE);

                    DocumentReference documentReference = firebaseFirestore.collection("users").document(firebaseUser.getUid()).collection("myUsers").document();
                    Map<String, Object> user = new HashMap<>();
                    user.put("name", name);
                    user.put("email", email);

                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getApplicationContext(), "User created successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(CreateUser.this, UsersActivity.class));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Failed to create user", Toast.LENGTH_SHORT).show();
                            mprogressbarofcreateuser.setVisibility(View.INVISIBLE);
                        }
                    });

                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}