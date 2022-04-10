package com.example.mybakery;

import androidx.appcompat.app.AppCompatActivity;


import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.Gravity;
import android.view.View;

import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TimerOption extends AppCompatActivity {
    int a;
    int sum ;

    LinearLayout add_Timer_Layout;
    EditText sch;

    public void onSaveData(View v){
        a = 1;
        if(sum+a > 8){
            Toast.makeText(getApplicationContext(), "더 이상 만들 수 없음", Toast.LENGTH_SHORT).show();
        } else {
            sum += a;
            Toast.makeText(getApplicationContext(), " " + sum + " ", Toast.LENGTH_SHORT).show();
            SharedPreferences sp = getSharedPreferences("shared", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            for (int i = 0; i <= sum; i++) {
                editor.putInt("num" + i, sum);
                editor.putString("schedule" + i, "asdasd" + sum);
                editor.putInt("time" + i, 20);
                editor.commit(); //완료한다.

                String schedule = sp.getString("schedule" + i, "");
                int time = sp.getInt("time" + i, 0);


                TextView tvData = (TextView) findViewById(R.id.tv_data);
                tvData.setText(schedule + time);
            }
        }


    }


    public void onDeleteData(View v){
        SharedPreferences sp = getSharedPreferences("shared", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        sum = 0;
        editor.clear();
        editor.commit();
        String schedule = sp.getString("schedule", "");
        int time = sp.getInt("time",0);
        TextView tvData = (TextView)findViewById(R.id.tv_data);
        tvData.setText(schedule + time);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.option_timer_setting);

        SharedPreferences sp = getSharedPreferences("shared", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        int ScheduleCountC = 1;

        add_Timer_Layout = (LinearLayout) findViewById(R.id.add_Timer_Layout);


        for(int i = 0; i <= sum; i++){
            String schedule = sp.getString("schedule"+i, "");
            int ScheduleCount = sp.getInt("time"+i,0);

            LinearLayout add_Schedule = new LinearLayout(getApplicationContext());
            add_Schedule.setOrientation(LinearLayout.HORIZONTAL);
            add_Schedule.setGravity(Gravity.CENTER);
            LinearLayout.LayoutParams setWH =
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 210);
            LinearLayout.LayoutParams Input_Schedule =
                    new LinearLayout.LayoutParams(500, 200);

            sch = new EditText(getApplicationContext());
            add_Schedule.setLayoutParams(setWH);
            sch.setLayoutParams(Input_Schedule);
            add_Schedule.addView(sch);
            TextView tvData = (TextView)findViewById(R.id.tv_data);
            tvData.setText(schedule + ScheduleCount);
        }

    }
}