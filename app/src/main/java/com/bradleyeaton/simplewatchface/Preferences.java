package com.bradleyeaton.simplewatchface;

import android.content.SharedPreferences;
import android.graphics.Color;

public class Preferences {

    private SharedPreferences sharedPref;

    public Preferences(SharedPreferences sharedPref) {
        this.sharedPref = sharedPref;
    }

    private static final String SECOND_COLOR = "SECOND_COLOR";

    /**
     * Sets the second hand color
     * @param color the color of the second hand
     */
    public void setSecondColorRGB(int color){
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(SECOND_COLOR, color);
        editor.apply();
    }
    /**
     * Gets the color of the second hand
     * @return
     */
    public int getSecondColor(){
        return sharedPref.getInt(SECOND_COLOR, Color.RED);
    }
}
