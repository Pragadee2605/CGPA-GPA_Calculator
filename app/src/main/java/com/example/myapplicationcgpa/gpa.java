package com.example.myapplicationcgpa;
// GPAActivity.java

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class gpa extends AppCompatActivity {

    private EditText editTextGrades;
    private EditText editTextCredits;
    private Button buttonCalculate;
    private TextView textViewResult;

    // Map to store grade points
    private Map<String, Integer> gradePoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpa);

        // Initialize grade points map
        gradePoints = new HashMap<>();
        gradePoints.put("O", 10);
        gradePoints.put("A+", 9);
        gradePoints.put("A", 8);
        gradePoints.put("B", 7);
        gradePoints.put("B+", 6);
        gradePoints.put("C", 5);

        editTextGrades = findViewById(R.id.editText_grades_gpa);
        editTextCredits = findViewById(R.id.editText_credits_gpa);
        buttonCalculate = findViewById(R.id.button_calculate_gpa);
        textViewResult = findViewById(R.id.textView_result_gpa);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gradesText = editTextGrades.getText().toString().trim();
                String creditsText = editTextCredits.getText().toString().trim();

                if (gradesText.isEmpty() || creditsText.isEmpty()) {
                    Toast.makeText(gpa .this, "Please enter grades and credits", Toast.LENGTH_SHORT).show();
                    return;
                }

                String[] gradeStrings = gradesText.split(",");
                String[] creditStrings = creditsText.split(",");

                if (gradeStrings.length != creditStrings.length) {
                    Toast.makeText(gpa .this, "Number of grades must match number of credits", Toast.LENGTH_SHORT).show();
                    return;
                }

                double[] grades = new double[gradeStrings.length];
                int[] credits = new int[creditStrings.length];

                try {
                    for (int i = 0; i < gradeStrings.length; i++) {
                        String grade = gradeStrings[i].trim();
                        if (!gradePoints.containsKey(grade)) {
                            throw new IllegalArgumentException("Invalid grade entered: " + grade);
                        }
                        grades[i] = gradePoints.get(grade);
                        credits[i] = Integer.parseInt(creditStrings[i].trim());
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(gpa .this, "Invalid input format", Toast.LENGTH_SHORT).show();
                    return;
                } catch (IllegalArgumentException e) {
                    Toast.makeText(gpa .this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }

                double gpa = calculateGPA(grades, credits);
                textViewResult.setText(String.format("GPA: %.2f", gpa));
            }
        });
    }

    // Function to calculate GPA
    private double calculateGPA(double[] grades, int[] credits) {
        if (grades.length != credits.length || grades.length == 0) {
            throw new IllegalArgumentException("Grades and credits arrays must have the same length and cannot be empty.");
        }

        double totalQualityPoints = 0;
        int totalCredits = 0;

        for (int i = 0; i < grades.length; i++) {
            totalQualityPoints += grades[i] * credits[i];
            totalCredits += credits[i];
        }

        return totalQualityPoints / totalCredits;
    }
}

