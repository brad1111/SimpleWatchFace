package com.bradleyeaton.simplewatchface.config;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
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


    /**
     * A list item in the RecyclerView
     * @param label Label of button
     * @param imageType ImageTypeEnum for type of Image to use
     * @param tintColor the color of the tint for the background
     */
    public ListItem(Context context, String label, int imageType, int tintColor) {
        this.label = label;
        Resources res = context.getResources();
        switch (imageType){
            case ImageTypeEnum.COLOR:
                this.foreground = ResourcesCompat.getDrawable(res, R.drawable.ic_round_color_lens_24, context.getTheme());
                break;
            case ImageTypeEnum.BOOLEAN:
                this.foreground = ResourcesCompat.getDrawable(res, R.drawable.ic_cc_checkmark, context.getTheme());
                break;
            default:
                this.foreground = ResourcesCompat.getDrawable(res, R.drawable.ic_cc_clear, context.getTheme());
                break;
        }
        this.background = ResourcesCompat.getDrawable(res, R.drawable.circle, context.getTheme());
        background.setTintList(ColorStateList.valueOf(tintColor));
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
}
