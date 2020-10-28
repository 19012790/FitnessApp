package com.app.fitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.Transliterator;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("UserInfo");
    EditText weight,height;
    Button submit,pull;
    String enteredWeight;
    String enteredHeight;
    UserInfo userInfo;

    TextView txt,txt2;
    Button btn,btn2;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) //metric and impierial
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        txt = (TextView) findViewById(R.id.myText);
        txt2= (TextView) findViewById(R.id.myText2);
        btn =(Button) findViewById(R.id.bt_imperial);
        btn2=(Button) findViewById(R.id.bt_metric);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt.setText("lb's");
                txt2.setText("inches");
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt.setText("kg's");
                txt2.setText("cm");
            }
        });

        spinner = findViewById(R.id.spinner); //spinner
        List<String> categories = new ArrayList<>();
        categories.add(0,"choose category");
        categories.add("camera");
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
                    Intent intent = new Intent(MainActivity2.this,MainActivity2.class);
                    startActivity(intent);
                }
                else
                {

                    if (parent.getItemAtPosition(position).equals("camera"))
                    {
                        Intent intent = new Intent(MainActivity2.this,Camera.class);
                        startActivity(intent);
                    }
                    else{
                        if (parent.getItemAtPosition(position).equals("Log in page"))
                        {
                            Intent intent = new Intent(MainActivity2.this,MainActivity.class);
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
        weight = findViewById(R.id.et_weight);
        height = findViewById(R.id.et_height);
        submit = findViewById(R.id.btn_submit);
        pull = findViewById(R.id.btn_pull);

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {

                enteredWeight = weight.getText().toString().trim();
                enteredHeight=height.getText().toString().trim();
                userInfo = new UserInfo(enteredWeight,enteredHeight);

                myRef.push().setValue(userInfo)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(MainActivity2.this, "sucess",
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity2.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });
        pull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openUsers = new Intent(MainActivity2.this,PulledUsers.class);
                startActivity(openUsers);
            }
        });

    }


}