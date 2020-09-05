package com.bradleyeaton.simplewatchface.config;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.widget.ImageView;
import androidx.core.content.res.ResourcesCompat;
import com.bradleyeaton.simplewatchface.R;
import org.xmlpull.v1.XmlPullParser;

import java.util.Random;
import java.util.concurrent.Future;

public class ListItem {

    
    private String label;
    private Drawable foreground;
    private Drawable background;
    private Intent intent;
    private int requestCode;
    private int tintColor;


    /**
     * A list item in the RecyclerView
     * @param label Label of button
     * @param imageType ImageTypeEnum for type of Image to use
     * @param tintColor the color of the tint for the background
     * @param intent the action to do on click
     * @param requestCode requestCode for intent
     */
    public ListItem(Context context, String label, int imageType, int tintColor, Intent intent, int requestCode) {
        this.label = label;
        this.intent = intent;
        this.requestCode = requestCode;
        Resources res = context.getResources();
        Drawable foregroundUnscaled;
        switch (imageType){
            case ImageTypeEnum.COLOR:
                foregroundUnscaled = ResourcesCompat.getDrawable(res, R.drawable.ic_round_color_lens_24, context.getTheme());
                break;
            case ImageTypeEnum.BOOLEAN:
                foregroundUnscaled = ResourcesCompat.getDrawable(res, R.drawable.ic_cc_checkmark, context.getTheme());
                break;
            default:
                foregroundUnscaled = ResourcesCompat.getDrawable(res, R.drawable.ic_cc_clear, context.getTheme());
                break;
        }
        final int FOREGROUND_DIAMETER = 140; //24px

        this.foreground = new ScaleDrawable(foregroundUnscaled, 0, FOREGROUND_DIAMETER, FOREGROUND_DIAMETER).getDrawable();
        this.foreground.setBounds(0,0,FOREGROUND_DIAMETER,FOREGROUND_DIAMETER);
        this.background = ResourcesCompat.getDrawable(res, R.drawable.circle, context.getTheme()).mutate();
        this.background.setTintList(ColorStateList.valueOf(tintColor));
        setBackgroundColor(tintColor);
    }

    public String getLabel() {
        return label;
    }

    public Drawable getBackground() {
        return background;
    }

    public Drawable getForeground() {
        return foreground;
    }

    public Intent getIntent() {
        return intent;
    }

    public int getBackgroundColor(){
        return tintColor;
    }

    public void setBackgroundColor(int tintColor){
        this.tintColor = tintColor;
        background.setTintList(ColorStateList.valueOf(tintColor));
    }

    public int getRequestCode() {
        return requestCode;
    }
}
