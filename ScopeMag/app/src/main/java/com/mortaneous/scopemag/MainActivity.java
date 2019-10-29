package com.mortaneous.scopemag;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

//import java.lang.Math.*;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final int ENTRY_COUNT = 4;
    private static final int MAX_ENTRY = 2;

    private EditText editScopeFocalLength;
    private EditText editScopeAperture;
    private int scopeAperture;
    private EditText[] editEyepiece = new EditText[ENTRY_COUNT];
    private EditText[] editMagnification = new EditText[ENTRY_COUNT];
    private EditText[] editBarlowMag = new EditText[ENTRY_COUNT];

    private ScopeView[] scopeView = new ScopeView[ENTRY_COUNT];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Models
        for(int i=0; i<ENTRY_COUNT; i++) {
            scopeView[i] = new ScopeView();
        }

        // Telescope Focal Length x Aperture
        editScopeFocalLength = findViewById(R.id.editScopeFocalLength);
        editScopeAperture = findViewById(R.id.editScopeAperture);
        scopeAperture = getInputValue(editScopeAperture);

        // Eyepiece Focal Length
        editEyepiece[0] = findViewById(R.id.editEyepiece0);
        editEyepiece[1] = findViewById(R.id.editEyepiece1);
        editEyepiece[2] = findViewById(R.id.editEyepiece2);
        editEyepiece[3] = findViewById(R.id.editEyepiece3);

        // Normal Magnification
        editMagnification[0] = findViewById(R.id.editMagnification0);
        editMagnification[1] = findViewById(R.id.editMagnification1);
        editMagnification[2] = findViewById(R.id.editMagnification2);
        editMagnification[3] = findViewById(R.id.editMagnification3);

        // Barlow Magnification
        editBarlowMag[0] = findViewById(R.id.editBarlowMagnification0);
        editBarlowMag[1] = findViewById(R.id.editBarlowMagnification1);
        editBarlowMag[2] = findViewById(R.id.editBarlowMagnification2);
        editBarlowMag[3] = findViewById(R.id.editBarlowMagnification3);

        // Set initial magnification
        for(int i=0; i<ENTRY_COUNT; i++) {
            Log.d(TAG, "onCreate: initializing scopeView[" + i + "]...");
            scopeView[i].setScopeFocalLength(getInputValue(editScopeFocalLength));
//            scopeView[i].setEyePieceFocalLength(getInputValue(editEyepiece[i]));
            updateScope(i, editEyepiece[i]);
        }

        // Set initial magnification
//        for(int i=0; i<ENTRY_COUNT; i++) {
//            Log.d(TAG, "onCreate: updating magnification[" + i + "]...");
//            editMagnification[i].setText(scopeView[i].getStrMagnificationPower());
//
//            editBarlowMag[i].setText(scopeView[i].getStrMagnificationWithBarlow());
//        }

        // Focus listener
        editScopeFocalLength.setOnFocusChangeListener(this::onFocusChange);
        editScopeAperture.setOnFocusChangeListener(this::onFocusChange);
        for(int i=0; i<ENTRY_COUNT; i++) {
            editEyepiece[i].setOnFocusChangeListener(this::onFocusChange);
        }
    }

    protected int getInputValue(EditText input) {
        Log.d(TAG, "getInputValue: begin");

        int retval = Integer.valueOf(input.getText().toString());
        Log.d(TAG, "getInputValue: '" + retval + "'");

        Log.d(TAG, "getInputValue: end");
        return retval;
    }

    protected void onFocusChange(View view, boolean hasFocus) {
        if (!hasFocus) {
            // edit box just lost focus
            switch (view.getId()) {
                case R.id.editScopeFocalLength:
                    int newScopeFocalLength = getInputValue((EditText) view);
                    for(int i=0; i<ENTRY_COUNT; i++) {
                        scopeView[i].setScopeFocalLength(newScopeFocalLength);
                        editMagnification[i].setText(scopeView[i].getStrMagnificationPower());
                        editBarlowMag[i].setText(scopeView[i].getStrMagnificationWithBarlow());
                    }
                    break;
                case R.id.editScopeAperture:
                    scopeAperture = getInputValue(editScopeAperture);
                    validateAllMagnifications();
                    break;
                case R.id.editEyepiece0:
                    updateScope(0, view);
                    break;
                case R.id.editEyepiece1:
                    updateScope(1, view);
                    break;
                case R.id.editEyepiece2:
                    updateScope(2, view);
                    break;
                case R.id.editEyepiece3:
                    updateScope(3, view);
                    break;
            }
        }
    }

    protected void updateScope(int scopeIndex, View view) {
        scopeView[scopeIndex].setEyePieceFocalLength(getInputValue((EditText) view));

        editMagnification[scopeIndex].setText(scopeView[scopeIndex].getStrMagnificationPower());

        editBarlowMag[scopeIndex].setText(scopeView[scopeIndex].getStrMagnificationWithBarlow());
        validateMagnification(scopeIndex);
    }

    protected void validateMagnification(int scopeIndex) {
        if(scopeView[scopeIndex].getMagnificationPowerWithBarlow() > scopeAperture * 2) {
            editBarlowMag[scopeIndex].setEnabled(false);
        }
        else {
            editBarlowMag[scopeIndex].setEnabled(true);
        }
    }

    protected void validateAllMagnifications() {
        for(int i=0; i<ENTRY_COUNT; i++) validateMagnification(i);
    }
}
