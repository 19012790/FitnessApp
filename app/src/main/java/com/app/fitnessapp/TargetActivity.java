package com.app.fitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TargetActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    EditText targetWeight,targetCalorie;
    Button submitData,viewData;
    String enteredWeightGoal;
    String enteredCaloricGoal;
    private FirebaseAuth mAuth;
    TargetGoals targetGoals;
    TextView weightPull,caloriePull;
    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);
        submitData = findViewById(R.id.bt_submitTarget);
        targetWeight=findViewById(R.id.et_targetWeight);
        targetCalorie=findViewById(R.id.et_targetCalorie);
        viewData = findViewById(R.id.bt_ViewData);
        weightPull =findViewById(R.id.txt_weightPull);
        caloriePull =findViewById(R.id.txt_pullCalorie);
        mAuth = FirebaseAuth.getInstance();
        submitData.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                mAuth = FirebaseAuth.getInstance();
                enteredWeightGoal = targetWeight.getText().toString().trim();
                enteredCaloricGoal = targetCalorie.getText().toString().trim();


                targetGoals = new TargetGoals(enteredWeightGoal, enteredCaloricGoal);
                DatabaseReference myRef = database.getReference(mAuth.getCurrentUser().getUid());

                myRef.child("targetGoals").push().setValue(targetGoals)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(TargetActivity.this, "sucess",
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                Toast.makeText(TargetActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        });


            }

        });
        spinner = findViewById(R.id.spinner); //spinner
        List<String> categories = new ArrayList<>();
        categories.add(0,"choose category");
        categories.add("Home");
        categories.add("Camera");
        categories.add("Graph");
        categories.add("Log in page");
        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {


                if(parent.getItemAtPosition(position).equals("choose catagory"))
                {
                    Intent intent = new Intent(TargetActivity.this,TargetActivity.class);
                    startActivity(intent);
                }
                else
                {

                    if (parent.getItemAtPosition(position).equals("Home"))
                    {
                        Intent intent = new Intent(TargetActivity.this,MainActivity2.class);
                        startActivity(intent);
                    }
                    else{
                        if (parent.getItemAtPosition(position).equals("Camera"))
                        {
                            Intent intent = new Intent(TargetActivity.this,Camera.class);
                            startActivity(intent);
                        }
                        else
                        if (parent.getItemAtPosition(position).equals("Graph"))
                        {
                            Intent intent = new Intent(TargetActivity.this,Graph.class);
                            startActivity(intent);
                        }
                        else
                        if (parent.getItemAtPosition(position).equals("Log In page"))
                        {
                            Intent intent = new Intent(TargetActivity.this, MainActivity.class);
                            startActivity(intent);
                        }



                    }

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO AUTO-generated method stub

            }
        });

viewData.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        DatabaseReference myRef = database.getReference(mAuth.getCurrentUser().getUid());
myRef.child("targetGoals").addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        for (DataSnapshot goals: dataSnapshot.getChildren())
        {
            targetGoals = goals.getValue(TargetGoals.class);
        }

        weightPull.setText(targetGoals.getTargetWeight());
        caloriePull.setText(targetGoals.getTargetCalorie());
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
});



    }
});
    }
}