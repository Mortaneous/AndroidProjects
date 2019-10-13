package com.mortaneous.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private MathOp mathOp = new MathOp();

    private Button clear;
    private Button allClear;
    private Button[] digits = new Button[10];
    private Button[] operators = new Button[4];
    private Button decimal;
    private Button equals;
    private EditText display;
    private TextView operation;
    private boolean hasFraction;
    private boolean operatorTapped;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Display
        display = findViewById(R.id.outputDisplay);
        operation = findViewById(R.id.textOperation);

        // Decimal button
        decimal = findViewById(R.id.buttonDecimal);
        decimal.setOnClickListener(this::OnDecimal);
        hasFraction = false;

        // Equals button
        equals = findViewById(R.id.buttonEquals);
        equals.setOnClickListener(this::OnEquals);

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

        // Operation buttons
        operators[0] = findViewById(R.id.buttonAdd);
        operators[1] = findViewById(R.id.buttonSubtract);
        operators[2] = findViewById(R.id.buttonMultiply);
        operators[3] = findViewById(R.id.buttonDivide);

        for(Button button : operators) {
            button.setOnClickListener(this::OnOperator);
        }

        // Clear button
        clear = findViewById(R.id.buttonC);
        clear.setOnClickListener(this::OnClear);

        // All Clear button
        allClear = findViewById(R.id.buttonAC);
        allClear.setOnClickListener(this::OnClear);

        // Initialize app
        ResetState();
    }

    private void ResetState(){
        display.setText("");
        operation.setText("");
        hasFraction = false;
        operatorTapped = false;
    }

    private void OnClear(View v) {
        ResetState();
    }

    private void setDisplayTo(String newText) {
        display.setText(newText);
    }

    private void addToDisplay(String newText) {
        String currentDisplay = display.getText().toString();
        display.setText(currentDisplay + newText);
    }

    private void OnDigit(View v) {
        Button button = (Button) v;
        String buttonText = button.getText().toString();
        String currentDisplay = display.getText().toString();

        if(currentDisplay.isEmpty() || operatorTapped) {
            if(!buttonText.equals("0")) {
                setDisplayTo(buttonText);
                operatorTapped = false;
            }
        }
        else {
            addToDisplay(buttonText);
        }
    }

    private void OnDecimal(View v) {
        Button button = (Button) v;
        String buttonText = button.getText().toString();
        String currentDisplay = display.getText().toString();

        if(!hasFraction) {
            hasFraction = true;
            if(currentDisplay.isEmpty()) {
                addToDisplay("0");
            }
            else if(operatorTapped) {
                setDisplayTo("0");
                operatorTapped = false;
            }
            addToDisplay(buttonText);
        }
    }

    private void OnOperator(View v) {
        Button button = (Button) v;
        String buttonText = button.getText().toString();
        String currentOperation = operation.getText().toString();
        String currentDisplay = display.getText().toString();

        if(!currentDisplay.isEmpty()) {
            operatorTapped = true;
            if(currentOperation.isEmpty()) {
                operation.setText(buttonText);
            }
            hasFraction = false;

            mathOp.setOperand1(Double.valueOf(currentDisplay));
        }
    }

    private void OnEquals(View v) {
        Button button = (Button) v;
        String buttonText = button.getText().toString();
        String currentOperation = operation.getText().toString();
        String currentDisplay = display.getText().toString();

        if(!currentOperation.isEmpty()) {
            operation.setText("");

            mathOp.setOperand2(Double.valueOf(currentDisplay));
            switch(currentOperation) {
                case("+") : mathOp.doAdd(); break;
                case("-") : mathOp.doSub(); break;
                case("*") : mathOp.doMul(); break;
                case("/") :
                    try {
                        mathOp.doDiv();
                    }
                    catch (Exception e) {
                        ResetState();
                    }
                    break;
            }

            setDisplayTo(mathOp.getResult().toString());

        }
    }
}
