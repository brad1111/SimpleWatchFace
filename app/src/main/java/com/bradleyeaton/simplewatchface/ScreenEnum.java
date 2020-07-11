package com.bradleyeaton.simplewatchface;

import androidx.annotation.Nullable;

public enum ScreenEnum {
    Analog,
    AnalogDetails,
    Digital,
    DigitalDetails;

    public static int toInt(ScreenEnum se){
        switch (se){
            case Analog:
                return 0;
            case AnalogDetails:
                return 1;
            case Digital:
                return 2;
            case DigitalDetails:
                return 3;
            default:
                return -1;
        }
    }

    public static ScreenEnum toEnum(int i){
        switch (i){
            case 0:
                return Analog;
            case 1:
                return AnalogDetails;
            case 2:
                return Digital;
            case 3:
                return DigitalDetails;
            default:
                return null;
        }
    }

    private static Integer length = null;

    public static int getLength(){
        if(length == null){
            length = values().length;
        }
        return length;
    }

    public static ScreenEnum nextScreen(ScreenEnum se){
        int count = getLength();
        int currentScreen = toInt(se);
        int nextScreen = (currentScreen + 1)%count;
        return toEnum(nextScreen);
    }
}
