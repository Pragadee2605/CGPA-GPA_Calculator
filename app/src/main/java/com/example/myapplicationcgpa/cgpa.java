package com.example.myapplicationcgpa;
// CGPAActivity.java

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class cgpa extends AppCompatActivity {

    private LinearLayout gradesContainer;
    private Button addButton;
    private Button calculateButton;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cgpa);

        gradesContainer = findViewById(R.id.container_grades_cgpa);
        addButton = findViewById(R.id.button_add_grade_cgpa);
        calculateButton = findViewById(R.id.button_calculate_cgpa);
        textViewResult = findViewById(R.id.textView_result_cgpa);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addGradeEditText();
            }
        });

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateCGPA();
            }
        });

        // Initially add one EditText for grades
        addGradeEditText();
    }

    private void addGradeEditText() {
        EditText editText = new EditText(this);
        editText.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        editText.setHint("Enter Grade");
        gradesContainer.addView(editText);
    }

    private void calculateCGPA() {
        int count = gradesContainer.getChildCount();
        double[] grades = new double[count];

        try {
            for (int i = 0; i < count; i++) {
                EditText editText = (EditText) gradesContainer.getChildAt(i);
                String gradeString = editText.getText().toString().trim();
                if (!gradeString.isEmpty()) {
                    grades[i] = Double.parseDouble(gradeString);
                } else {
                    grades[i] = 0; // Treat empty as 0 for calculation
                }
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid input format", Toast.LENGTH_SHORT).show();
            return;
        }

        double cgpa = calculateAverage(grades);
        textViewResult.setText(String.format("CGPA: %.2f", cgpa));
    }

    private double calculateAverage(double[] grades) {
        if (grades.length == 0) {
            throw new IllegalArgumentException("Grades array cannot be empty.");
        }

        double total = 0;

        for (double grade : grades) {
            total += grade;
        }

        return total / grades.length;
    }
}
