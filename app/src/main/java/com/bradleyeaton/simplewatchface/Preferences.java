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
        sharedPref.edit().putInt(SECOND_COLOR, color).apply();
    }
    /**
     * Gets the color of the second hand
     * @return
     */
    public int getSecondColor(){
        return sharedPref.getInt(SECOND_COLOR, Color.RED);
    }

    private static final String UPDATED = "UPDATED";

    /**
     * Gets whether the preferences have been updated since last check
     * @return
     */
    public boolean getUpdated(){
        boolean value = sharedPref.getBoolean(UPDATED,false);
        if(value) {
            //We've received the update
            sharedPref.edit().putBoolean(UPDATED, false).apply();
        }
        return value;
    }

    /**
     * Notify that preferences have been updated
     */
    public void setUpdated(){
        sharedPref.edit().putBoolean(UPDATED, true).apply();
    }
}
