package com.bradleyeaton.simplewatchface;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class ConfigActivity extends WearableActivity {

//    private TextView mTextView;

    private Preferences preferences;

    private NumberPicker mPickerRed;
    private NumberPicker mPickerGreen;
    private NumberPicker mPickerBlue;

//    private Button mButtonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        //        mTextView = (TextView) findViewById(R.id.text);

        //setup preferences
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("A", MODE_PRIVATE);
        preferences = new Preferences(sharedPref);


        //Setup number picker values
        mPickerRed = (NumberPicker) findViewById(R.id.picker_red);
        mPickerGreen = (NumberPicker) findViewById(R.id.picker_green);
        mPickerBlue = (NumberPicker) findViewById(R.id.picker_blue);
//        mButtonSave = (Button) findViewById(R.id.btn_save);

        mPickerRed.setMinValue(0);
        mPickerRed.setMaxValue(255);

        mPickerGreen.setMinValue(0);
        mPickerGreen.setMaxValue(255);

        mPickerBlue.setMinValue(0);
        mPickerBlue.setMaxValue(255);


        // Enables Always-on
        setAmbientEnabled();
    }

    public void onBtnSave(View view){
        int r = mPickerRed.getValue();
        int g = mPickerGreen.getValue();
        int b = mPickerBlue.getValue();
        int color = Color.argb(255, r, g, b);
        preferences.setSecondColorRGB(color);
    }
}