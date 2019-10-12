package com.mortaneous.buttoncounter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    private final String TEXT_VIEW_KEY = "textViewContents";
    private EditText userInput;
    private Button button;
    private TextView textView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: start");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userInput = findViewById(R.id.editText);
        userInput.setText("");

        textView = findViewById(R.id.textView);
        textView.setText("");

        textView.setMovementMethod(new ScrollingMovementMethod());

        button = findViewById(R.id.button);
        button.setText("Add");
        button.setOnClickListener((v) -> {
                String input = userInput.getText().toString();
                if(!input.isEmpty()) {
                    textView.append(input + "\n");
                    userInput.setText("");

                }
        });

        Log.d(TAG, "onCreate: end");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        Log.d(TAG, "onSaveInstanceState: start");

        super.onSaveInstanceState(outState);
//        outState.putCharSequence(TEXT_VIEW_KEY, textView.getText());
        outState.putString(TEXT_VIEW_KEY, textView.getText().toString());
        Log.d(TAG, "onSaveInstanceState: end");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        Log.d(TAG, "onRestoreInstanceState: start");

        super.onRestoreInstanceState(savedInstanceState);
//        textView.setText(savedInstanceState.getCharSequence(TEXT_VIEW_KEY).toString());
        textView.setText(savedInstanceState.getString(TEXT_VIEW_KEY));

        Log.d(TAG, "onRestoreInstanceState: end");
    }
}
