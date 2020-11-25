package com.app.fitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PulledUsers extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("UserInfo");
    List<String> usersList;
    ArrayAdapter adapter;
    ListView usersListView;
    UserInfo userInfo;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulled_users);
        usersListView=findViewById(R.id.lv_users);
        mAuth = FirebaseAuth.getInstance();
        DatabaseReference myRef = database.getReference(mAuth.getCurrentUser().getUid());

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                userInfo = new UserInfo();
                usersList = new ArrayList<String>();
                for (DataSnapshot userFromFirebase: snapshot.getChildren())
                {
                    userInfo = userFromFirebase.getValue(UserInfo.class);
                    usersList.add(userInfo.ToString());
                }

                adapter = new ArrayAdapter(PulledUsers.this,
                        android.R.layout.simple_list_item_1,usersList);
                usersListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {
                Toast.makeText(PulledUsers.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}