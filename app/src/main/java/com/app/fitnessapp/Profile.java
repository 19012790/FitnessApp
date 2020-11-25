package com.app.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Profile extends AppCompatActivity {
    TextInputLayout email, password;

    String _email, _password;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        reference = FirebaseDatabase.getInstance().getReference("user");

        email = findViewById(R.id.Ti_email);
        password = findViewById(R.id.Ti_password);


        showAllUserData();
    }
        private void showAllUserData () {
            Intent intent = getIntent();
            _email = intent.getStringExtra("email");
            email.getEditText().setText(_email);
            _password = intent.getStringExtra("password");
            password.getEditText().setText(_password);
        }

        public void update (View view){
            if (isNameChanged() || isPasswordChanged()) {
                Toast.makeText(this, "data has been changed", Toast.LENGTH_LONG);
            } else Toast.makeText(this, "error,data is the same", Toast.LENGTH_LONG);
        }

        private boolean isPasswordChanged () {
            if (!_password.equals(password.getEditText().getText().toString())) {
                reference.child(_password).child("password").setValue(password.getEditText().getText().toString());
                _password = password.getEditText().getText().toString();
                return true;
            } else {

                return false;
            }
        }

        private boolean isNameChanged () {
            if (!_email.equals(email.getEditText().getText().toString())) {
                reference.child(_email).child("email").setValue(email.getEditText().getText().toString());
                _email = email.getEditText().getText().toString();
                return true;
            } else {
                return false;
            }
        }
    }
