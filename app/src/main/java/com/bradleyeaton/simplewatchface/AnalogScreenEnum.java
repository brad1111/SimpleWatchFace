package com.bradleyeaton.simplewatchface;

import androidx.annotation.Nullable;

public class AnalogScreenEnum {
    public static final int ANALOG = 0;
    public static final int ANALOG_DETAILS = 1;
    public static final int DIGITAL = 2;
    public static final int DIGITAL_DETAILS = 3;

    private static final int LENGTH = 4;

    public static int nextScreen(int screen){
        return (screen + 1)%LENGTH;
    }
}
