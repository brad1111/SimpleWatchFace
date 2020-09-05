package com.bradleyeaton.simplewatchface.config;

import android.content.SharedPreferences;
import android.graphics.Color;

public class Preferences {

    private SharedPreferences sharedPref;

    public Preferences(SharedPreferences sharedPref) {
        this.sharedPref = sharedPref;
    }

    public static final int SECOND_COLOR = 932;

//    /**
//     * Sets the second hand color
//     * @param color the color of the second hand
//     */
//    public void setSecondColorRGB(int color){
//        sharedPref.edit().putInt(SECOND_COLOR, color).apply();
//    }
//    /**
//     * Gets the color of the second hand
//     * @return
//     */
//    public int getSecondColor(){
//        return sharedPref.getInt(SECOND_COLOR, Color.RED);
//    }

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

    public int getColorPreference(int preferenceName){
        //Switch for default colors
        int defaultColor;
        switch (preferenceName){
            case SECOND_COLOR:
                defaultColor = Color.RED;
                break;
            default:
                defaultColor = Color.BLACK;
                break;
        }
        return sharedPref.getInt(Integer.toString(preferenceName), defaultColor);
    }

    public void setColorPreference(int preferenceName, int color){
        sharedPref.edit().putInt(Integer.toString(preferenceName), color).apply();
    }
}
