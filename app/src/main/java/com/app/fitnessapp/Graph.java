package com.app.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.razerdp.widget.animatedpieview.AnimatedPieView;
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig;
import com.razerdp.widget.animatedpieview.data.SimplePieInfo;

import java.util.ArrayList;
import java.util.List;

public class Graph extends AppCompatActivity {
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        drawPie();
        spinner = findViewById(R.id.spinner); //spinner
        List<String> categories = new ArrayList<>();
        categories.add(0,"choose category");
        categories.add("Home");
        categories.add("Camera");
        categories.add("Targets");
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
                    Intent intent = new Intent(Graph.this,Graph.class);
                    startActivity(intent);
                }
                else
                {

                    if (parent.getItemAtPosition(position).equals("Home"))
                    {
                        Intent intent = new Intent(Graph.this,MainActivity2.class);
                        startActivity(intent);
                    }
                    else{
                        if (parent.getItemAtPosition(position).equals("Camera"))
                        {
                            Intent intent = new Intent(Graph.this,Camera.class);
                            startActivity(intent);
                        }
                        else
                        if (parent.getItemAtPosition(position).equals("Targets"))
                        {
                            Intent intent = new Intent(Graph.this,TargetActivity.class);
                            startActivity(intent);
                        }
                        else
                        if (parent.getItemAtPosition(position).equals("Log In page"))
                        {
                            Intent intent = new Intent(Graph.this, MainActivity.class);
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
    public void drawPie()
    {
        AnimatedPieView mAnimatedPieView = findViewById(R.id.animatedPieView);
        AnimatedPieViewConfig config = new AnimatedPieViewConfig();
        config.startAngle(-90)
                .addData(new SimplePieInfo(30, Color.parseColor("#ff0000"), "Current Weight and Calorie"))
                .addData(new SimplePieInfo(18.0f, Color.parseColor("#00ff00"), "Target Weight and Calorie")).drawText(true).strokeMode(false)

      .duration(2000).textSize(30);


        mAnimatedPieView.applyConfig(config);
        mAnimatedPieView.start();

    }
}