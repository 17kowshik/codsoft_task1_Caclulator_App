package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, bp, bs, bm, bd, be, bc;
    EditText e;
    double var1, var2;
    char op;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Pass IDs to the Views
        b0 = findViewById(R.id.b0);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        b4 = findViewById(R.id.b4);
        b5 = findViewById(R.id.b5);
        b6 = findViewById(R.id.b6);
        b7 = findViewById(R.id.b7);
        b8 = findViewById(R.id.b8);
        b9 = findViewById(R.id.b9);
        bp = findViewById(R.id.bp);
        bs = findViewById(R.id.bs);
        bm = findViewById(R.id.bm);
        bd = findViewById(R.id.bd);
        be = findViewById(R.id.be);
        bc = findViewById(R.id.bc);
        e = findViewById(R.id.e);

        // Handle number buttons via appendDigit method
        b0.setOnClickListener(view -> appendDigit("0"));
        b1.setOnClickListener(view -> appendDigit("1"));
        b2.setOnClickListener(view -> appendDigit("2"));
        b3.setOnClickListener(view -> appendDigit("3"));
        b4.setOnClickListener(view -> appendDigit("4"));
        b5.setOnClickListener(view -> appendDigit("5"));
        b6.setOnClickListener(view -> appendDigit("6"));
        b7.setOnClickListener(view -> appendDigit("7"));
        b8.setOnClickListener(view -> appendDigit("8"));
        b9.setOnClickListener(view -> appendDigit("9"));

        // Handle operators via setOperator method
        bp.setOnClickListener(view -> setOperator('+'));
        bs.setOnClickListener(view -> setOperator('-'));
        bm.setOnClickListener(view -> setOperator('*'));
        bd.setOnClickListener(view -> setOperator('/'));

        // Handle equals button via calculateResult method
        be.setOnClickListener(view -> calculateResult());

        // Handle clear button via clearCalculator Method
        bc.setOnClickListener(view -> clearCalculator());
    }

    // Method to append digits to the EditText
    private void appendDigit(String digit) {
        e.setText(e.getText().toString() + digit);
    }

    // Method to set the current operator
    private void setOperator(char operator) {
        op = operator;
        var1 = Double.parseDouble(e.getText().toString());
        e.setText("");
    }

    // Method to calculate and display the result
    private void calculateResult() {
        if (op == 'o') {
            Toast.makeText(getApplicationContext(), "Please Select an Operator", Toast.LENGTH_SHORT).show();
            return;
        }

        if (e.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please Enter a Valid Number", Toast.LENGTH_SHORT).show();
            return;
        }

        var2 = Double.parseDouble(e.getText().toString());
        double result = 0.0;

        switch (op) {
            case '+':
                result = var1 + var2;
                break;
            case '-':
                result = var1 - var2;
                break;
            case '*':
                result = var1 * var2;
                break;
            case '/':
                if (var2 == 0) {
                    Toast.makeText(getApplicationContext(), "Division by zero is not allowed", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    result = var1 / var2;
                }
                break;
        }

        // Check for infinite or NaN
        if (Double.isInfinite(result) || Double.isNaN(result)) {
            Toast.makeText(getApplicationContext(), "Result is too large or invalid", Toast.LENGTH_SHORT).show();
            return;
        }

        // Round the result to four decimal places if it has a decimal value
        if (result == (long) result) {
            e.setText(String.format("%.0f", result)); // No decimal places for whole numbers
        } else {
            e.setText(String.format("%.4f", result));
        }
    }

    // Method to clear the calculator
    private void clearCalculator() {
        e.setText("");
        var1 = 0.0;
        var2 = 0.0;
        op = 'o';
    }
}
