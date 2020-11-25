package com.app.fitnessapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Camera extends AppCompatActivity {
ImageView imageView;
Button btOpen;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);


        imageView=findViewById(R.id.image_view);
        btOpen=findViewById(R.id.bt_open);

        if(ContextCompat.checkSelfPermission(Camera.this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(Camera.this,
                    new String[]{
                            Manifest.permission.CAMERA
                    },
                    100);

        }
        btOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,100);
            }
        });
        spinner = findViewById(R.id.spinner); //spinner
        List<String> categories = new ArrayList<>();
        categories.add(0,"choose category");
        categories.add("Home");
        categories.add("Graph");
        categories.add("targets");
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
                    Intent intent = new Intent(Camera.this,Camera.class);
                    startActivity(intent);
                }
                else
                {

                    if (parent.getItemAtPosition(position).equals("Home"))
                    {
                        Intent intent = new Intent(Camera.this,MainActivity2.class);
                        startActivity(intent);
                    }
                    else{
                        if (parent.getItemAtPosition(position).equals("Graph"))
                        {
                            Intent intent = new Intent(Camera.this,Graph.class);
                            startActivity(intent);
                        }
                        else
                        if (parent.getItemAtPosition(position).equals("targets"))
                        {
                            Intent intent = new Intent(Camera.this,TargetActivity.class);
                            startActivity(intent);
                        }
                        else
                        if (parent.getItemAtPosition(position).equals("Log in page"))
                        {
                            Intent intent = new Intent(Camera.this, MainActivity.class);
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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            Bitmap captureImage = (Bitmap) data.getExtras().get("data");

            imageView.setImageBitmap(captureImage);
        }
    }

}