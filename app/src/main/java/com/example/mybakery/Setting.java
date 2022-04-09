package com.example.mybakery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

    public class Setting extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.option_main);

        Intent TimerSetting_Btn_Active = new Intent(this,TimerOption.class);
        Button Setting = (Button) findViewById(R.id.TimerSetting);
        Setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(TimerSetting_Btn_Active);
            }
        });
    }
}
