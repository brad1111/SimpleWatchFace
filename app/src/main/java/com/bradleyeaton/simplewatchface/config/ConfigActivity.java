package com.bradleyeaton.simplewatchface.config;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import androidx.wear.widget.WearableLinearLayoutManager;
import androidx.wear.widget.WearableRecyclerView;
import com.bradleyeaton.simplewatchface.R;
import org.jraf.android.androidwearcolorpicker.ColorPickActivity;

import java.util.HashMap;
import java.util.Random;

public class ConfigActivity extends WearableActivity {

    private Preferences preferences;
    private WearableRecyclerView mWearableRecyclerView;
    private HashMap<Integer, ListItem> preferenceMatcher = new HashMap<>(); //matches preference constant to a listItem

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        //setup preferences
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(getString(R.string.preference_file_key), MODE_PRIVATE);
        preferences = new Preferences(sharedPref);

        //temp create a list of items
        Random rng = new Random();
        ListItem[] items = new ListItem[20];
        for (int i = 0; i < 20; i++) {
            int imageType = i % 2 == 0
                    ? ImageTypeEnum.COLOR
                    : ImageTypeEnum.BOOLEAN;
            //set random colour

            int color = preferences.getColorPreference(Preferences.SECOND_COLOR);
            items[i] = new ListItem(this, "Item " + i , imageType, color, getBtnSecondsHandColorIntent(), Preferences.SECOND_COLOR);
            preferenceMatcher.put(Preferences.SECOND_COLOR, items[i]);
        }


        //Recyclerview
        mWearableRecyclerView = findViewById(R.id.wearableRecyclerView);
        mWearableRecyclerView.setEdgeItemsCenteringEnabled(true); //round view on round watch faces
        RecyclerView.LayoutManager layoutManager = new WearableLinearLayoutManager(this);
        mWearableRecyclerView.setLayoutManager(layoutManager);
        ListAdapter listAdapter = new ListAdapter(items);
        listAdapter.onBind = (viewHolder, position) -> {
            viewHolder.listItem.setOnClickListener(view -> {
                startActivityForResult(getBtnSecondsHandColorIntent(), items[position].getRequestCode());
            });
        };
        mWearableRecyclerView.setAdapter(listAdapter);

        // Enables Always-on
        setAmbientEnabled();
    }

//    public void onBtnSecondsHandColor(View view){
//        //show color picker
//        Intent intent = new ColorPickActivity.IntentBuilder()
//                            .oldColor(preferences.getSecondColor())
//                            .build(this);
//        startActivityForResult(intent, REQUEST_PICK_COLOR);
//    }

    public Intent getBtnSecondsHandColorIntent(){
        return new ColorPickActivity.IntentBuilder()
                .oldColor(preferences.getColorPreference(Preferences.SECOND_COLOR))
                .build(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case Preferences.SECOND_COLOR:
                if(resultCode == RESULT_OK){
                    int color = ColorPickActivity.Companion.getPickedColor(data);
                    preferences.setColorPreference(requestCode, color);
                    preferences.setUpdated();
                    preferenceMatcher.containsKey(requestCode);
                    preferenceMatcher.get(requestCode).setBackgroundColor(color);
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        preferences = null;
        mWearableRecyclerView = null;
        preferenceMatcher = null;
    }
}