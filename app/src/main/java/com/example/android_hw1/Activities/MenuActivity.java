package com.example.android_hw1.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;

import com.example.android_hw1.BackgroundSound;
import com.example.android_hw1.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class MenuActivity extends AppCompatActivity {

    // view
    private MaterialButton menu_BTN_play;
    private MaterialButton menu_BTN_topGrades;
    private SwitchMaterial  menu_SW_sensor;
    private SwitchMaterial  menu_SW_fast;


    // mode
    private boolean isFast;
    private boolean isSensorMode;

    //sound
    private BackgroundSound backgroundSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        findViews();
        switchListener();
        buttonListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.backgroundSound = new BackgroundSound(this,R.raw.paper_rip_new);
    }

    private void buttonListener(){
        menu_BTN_play.setOnClickListener(view -> {
            openGameScreen();
        });

        menu_BTN_topGrades.setOnClickListener(view -> {
            openGradesScreen();
        });
    }

    private void switchListener(){
        menu_SW_fast.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean fast) {
                isFast = fast;
            }
        });
        menu_SW_sensor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean sensor) {
                isSensorMode = sensor;
            }
        });
    }

    private void findViews(){
        menu_BTN_play = findViewById(R.id.menu_BTN_play);
        menu_BTN_topGrades = findViewById(R.id.menu_BTN_topGrades);
        menu_SW_sensor = findViewById(R.id.menu_SW_sensor);
        menu_SW_fast = findViewById(R.id.menu_SW_fast);
    }

    private void openGradesScreen() {
        Intent scoreIntent = new Intent(this, ScoreActivity.class);
        startActivity(scoreIntent);
        backgroundSound.execute();
        finish();
    }

    private void openGameScreen() {
        Intent gameIntent = new Intent(this, GameActivity.class);
        gameIntent.putExtra(GameActivity.KEY_SENSOR, isSensorMode);
        gameIntent.putExtra(GameActivity.KEY_SPEED, isFast);
        startActivity(gameIntent);
        finish();
    }
}