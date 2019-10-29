package com.mortaneous.scopemag;

import android.util.Log;

class ScopeView {
    static final String TAG = "ScopeView";
    static final int DEF_SCOPE_FOCAL_LENGTH = 700;
    static final int DEF_EYEPIECE_FOCAL_LENGTH = 20;
    static final int DEF_BARLOW_POWER = 2;

    int scopeFocalLength;
    int eyePieceFocalLength;
    int barlowPower;
    double magnificationPower;
    double magnificationPowerWithBarlow;

    public ScopeView() {
        this(
                DEF_SCOPE_FOCAL_LENGTH,
                DEF_EYEPIECE_FOCAL_LENGTH,
                DEF_BARLOW_POWER
        );
    }

    public ScopeView(int scopeFocalLength, int eyePieceFocalLength) {
        this(
                scopeFocalLength,
                eyePieceFocalLength,
                DEF_BARLOW_POWER
        );
    }

    public ScopeView(int scopeFocalLength, int eyePieceFocalLength, int barlowPower) {
        Log.d(TAG, "Constructor: begin");

        this.scopeFocalLength = scopeFocalLength;
        this.eyePieceFocalLength = eyePieceFocalLength;
        this.barlowPower = barlowPower;

        calculateMagnification();

        Log.d(TAG, "Constructor: end");
    }

    public int getScopeFocalLength() {
        return scopeFocalLength;
    }

    public void setScopeFocalLength(int scopeFocalLength) {
        Log.d(TAG, "setScopeFocalLength: begin");

        this.scopeFocalLength = scopeFocalLength;
        calculateMagnification();

        Log.d(TAG, "setScopeFocalLength: end");
    }

    public int getEyePieceFocalLength() {
        return eyePieceFocalLength;
    }

    public void setEyePieceFocalLength(int eyePieceFocalLength) {
        Log.d(TAG, "setEyePieceFocalLength: begin");

        this.eyePieceFocalLength = eyePieceFocalLength;
        calculateMagnification();

        Log.d(TAG, "setEyePieceFocalLength: end");
    }

    public int getBarlowPower() {
        return barlowPower;
    }

    public void setBarlowPower(int barlowPower) {
        this.barlowPower = barlowPower;
        calculateMagnification();
    }

    public double getMagnificationPower() {
        return magnificationPower;
    }

    public double getMagnificationPowerWithBarlow() {
        return magnificationPowerWithBarlow;
    }

    protected void calculateMagnification()
    {
        Log.d(TAG, "calculateMagnification: begin");

        magnificationPower = (double) scopeFocalLength / eyePieceFocalLength;
        magnificationPowerWithBarlow = magnificationPower * barlowPower;

        Log.d(TAG, "calculateMagnification: end");
    }

    public String getStrMagnificationPower() {
        return getStringValue(magnificationPower);
    }

    public String getStrMagnificationWithBarlow() {
        return getStringValue(magnificationPowerWithBarlow);
    }

    protected String getStringValue(double d){
        return String.format("%d", Math.round(d));
    }
}