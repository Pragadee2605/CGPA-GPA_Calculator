package com.example.myapplicationcgpa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Click handler for CGPA button
    public void onCalculateCGPAClick(View view) {
        Intent intent = new Intent(this, cgpa.class);
        startActivity(intent);
    }

    // Click handler for GPA button
    public void onCalculateGPAClick(View view) {
        Intent intent = new Intent(this, gpa.class);
        startActivity(intent);
    }
}
