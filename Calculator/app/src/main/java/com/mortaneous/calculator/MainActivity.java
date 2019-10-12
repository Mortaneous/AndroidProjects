package com.mortaneous.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button clear;
    private Button allClear;
    private Button[] digits = new Button[10];
    private Button[] operator = new Button[4];
    private Button decimal;
    private Button equals;
    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.outputDisplay);
        display.setText("");

        decimal = findViewById(R.id.buttonDecimal);
        equals = findViewById(R.id.buttonEquals);

        // Digit buttons
        digits[0] = findViewById(R.id.button0);
        digits[1] = findViewById(R.id.button1);
        digits[2] = findViewById(R.id.button2);
        digits[3] = findViewById(R.id.button3);
        digits[4] = findViewById(R.id.button4);
        digits[5] = findViewById(R.id.button5);
        digits[6] = findViewById(R.id.button6);
        digits[7] = findViewById(R.id.button7);
        digits[8] = findViewById(R.id.button8);
        digits[9] = findViewById(R.id.button9);

        for(Button button : digits) {
            button.setOnClickListener(this::OnDigit);
        }

        // Clear button
        clear = findViewById(R.id.buttonC);
        clear.setOnClickListener(this::OnClear);

        // All Clear button
        allClear = findViewById(R.id.buttonAC);
        allClear.setOnClickListener(this::OnClear);
    }

    private void OnClear(View v) {
        display.setText("");
    }

    private void OnDigit(View v) {
        Button button = (Button) v;
        String currentDisplay = display.getText().toString();
        String buttonText = button.getText().toString();

        if(!currentDisplay.isEmpty() || !buttonText.equals("0")) {
            display.setText(currentDisplay + buttonText);
        }

    }
}
