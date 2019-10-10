package com.mortaneous.buttoncounter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TEXT_VIEW_KEY = "textViewContents";
    private EditText userInput;
    private Button button;
    private TextView textView;
    private int clickCount = 0;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userInput = (EditText) findViewById(R.id.editText);
        userInput.setText("");

        textView = (TextView) findViewById(R.id.textView);
        textView.setText("");
        if(savedInstanceState != null) {
            CharSequence textViewContents = savedInstanceState.getCharSequence(TEXT_VIEW_KEY);
            if (textViewContents != null) {
                textView.setText(textViewContents.toString());
            }
        }

        textView.setMovementMethod(new ScrollingMovementMethod());

        button = (Button) findViewById(R.id.button);
        button.setText("Add");
        button.setOnClickListener((v) -> {
                String input = userInput.getText().toString();
                if(!input.isEmpty()) {
                    textView.append(input.toString() + "\n");
                    userInput.setText("");

                }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putCharSequence(TEXT_VIEW_KEY, textView.getText());
    }
}
