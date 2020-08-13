package com.bradleyeaton.simplewatchface;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import androidx.wear.widget.WearableLinearLayoutManager;
import androidx.wear.widget.WearableRecyclerView;
import org.jraf.android.androidwearcolorpicker.ColorPickActivity;
import org.jraf.android.androidwearcolorpicker.R.id.*;

public class ConfigActivity extends WearableActivity {

//    private TextView mTextView;

    private Preferences preferences;

//    private NumberPicker mPickerRed;
//    private NumberPicker mPickerGreen;
//    private NumberPicker mPickerBlue;

//    private Button mButtonSave;
    private WearableRecyclerView mWearableRecyclerView;

    private static final int REQUEST_PICK_COLOR = 202;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        //temp create a list of items
        String[] items = new String[100];
        for (int i = 0; i < 100; i++) {
            items[i] = Integer.toString(i);
        }


        mWearableRecyclerView = findViewById(R.id.wearableRecyclerView);
        mWearableRecyclerView.setEdgeItemsCenteringEnabled(true);
        RecyclerView.LayoutManager layoutManager = new WearableLinearLayoutManager(this);
        mWearableRecyclerView.setLayoutManager(layoutManager);
        ListAdapter listAdapter = new ListAdapter(items);
        mWearableRecyclerView.setAdapter(listAdapter);

        //        mTextView = (TextView) findViewById(R.id.text);

        //setup preferences
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("A", MODE_PRIVATE);
        preferences = new Preferences(sharedPref);


        //Setup number picker values
//        mPickerRed = (NumberPicker) findViewById(R.id.picker_red);
//        mPickerGreen = (NumberPicker) findViewById(R.id.picker_green);
//        mPickerBlue = (NumberPicker) findViewById(R.id.picker_blue);
//        mButtonSave = (Button) findViewById(R.id.btn_save);

//        mPickerRed.setMinValue(0);
//        mPickerRed.setMaxValue(255);
//
//        mPickerGreen.setMinValue(0);
//        mPickerGreen.setMaxValue(255);
//
//        mPickerBlue.setMinValue(0);
//        mPickerBlue.setMaxValue(255);


        // Enables Always-on
        setAmbientEnabled();
    }

//    public void onBtnSave(View view){
//        int r = mPickerRed.getValue();
//        int g = mPickerGreen.getValue();
//        int b = mPickerBlue.getValue();
//        int color = Color.argb(255, r, g, b);
//        preferences.setSecondColorRGB(color);
//        preferences.setUpdated();
//    }

    public void onBtnSecondsHandColor(View view){
        //show color picker
        Intent intent = new ColorPickActivity.IntentBuilder()
                            .oldColor(preferences.getSecondColor())
                            .build(this);
        startActivityForResult(intent, REQUEST_PICK_COLOR);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_PICK_COLOR:
                if(resultCode == RESULT_OK){
                    int color = ColorPickActivity.Companion.getPickedColor(data);
                    preferences.setSecondColorRGB(color);
                    preferences.setUpdated();
                }
                break;
        }
    }
}