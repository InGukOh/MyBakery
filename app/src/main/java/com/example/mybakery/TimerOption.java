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
    int SavedData_Length; //저장된 데이터 갯수
    int count; // 타이머 갯수
    String schedule; // 일정 내용
    int time; // 일정의 시간

    LinearLayout add_Timer_Layout;
    EditText make_Schedule;
    EditText make_Time;
    Button make_dellBtn;

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    public int saveDATA(int count){
        count = sp.getInt("count",SavedData_Length);;
        return count;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.option_timer_setting);

        sp = getSharedPreferences("shared", MODE_PRIVATE);
        editor = sp.edit();

        add_Timer_Layout = (LinearLayout) findViewById(R.id.add_Timer_Layout);

        SavedData_Length = sp.getInt("count",0);

        for(int i = 0; i <= SavedData_Length; i++){
            int count = sp.getInt("count",0);
            String schedule = sp.getString("schedule"+i, "");
            int ScheduleCount = sp.getInt("time"+i,0);
            TextView tvData = (TextView)findViewById(R.id.tv_data);
            tvData.setText(SavedData_Length + schedule + ScheduleCount);
        }

    }

    public void onSaveData(View view){

        sp = getSharedPreferences("shared", MODE_PRIVATE);
        editor = sp.edit();

        make_Schedule = new EditText(getApplicationContext());
        make_Time = new EditText(getApplicationContext());
        make_dellBtn = new Button(getApplicationContext());

        if(SavedData_Length >= 8){
            Toast.makeText(getApplicationContext(), "더 이상 만들 수 없음", Toast.LENGTH_SHORT).show();
        } else {
            SavedData_Length ++;
            Toast.makeText(getApplicationContext(), " " + SavedData_Length + " ", Toast.LENGTH_SHORT).show();

            LinearLayout add_Schedule = new LinearLayout(getApplicationContext());
            add_Schedule.setOrientation(LinearLayout.HORIZONTAL);
            add_Schedule.setGravity(Gravity.CENTER);

            LinearLayout.LayoutParams setWnH =
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 210);
            LinearLayout.LayoutParams Input_Schedule =
                    new LinearLayout.LayoutParams(500, 200);
            LinearLayout.LayoutParams InPut_Timer =
                    new LinearLayout.LayoutParams(300, 200);
            LinearLayout.LayoutParams Input_delBtn =
                    new LinearLayout.LayoutParams(200, 200);

            for (int i = 0; i <= SavedData_Length; i++) {


                add_Schedule.setLayoutParams(setWnH);
                make_Schedule.setLayoutParams(Input_Schedule);
                make_Time.setLayoutParams(InPut_Timer);
                make_dellBtn.setLayoutParams(Input_delBtn);

                make_Schedule.setHint("일정내용");
                make_Time.setHint("시간");
                make_dellBtn.setText("삭제");
                make_dellBtn.setTextSize(15);

                make_Schedule.setId(SavedData_Length);
                make_Time.setId(SavedData_Length);
                make_dellBtn.setId(SavedData_Length);
                if(add_Schedule.getParent() != null){
                    ((ViewGroup) add_Schedule.getParent()).removeView(add_Schedule);
                    add_Schedule.addView(make_Schedule);
                    add_Schedule.addView(make_Time);
                    add_Schedule.addView(make_dellBtn);
                }


                add_Timer_Layout.addView(add_Schedule);

                editor.putInt("count", SavedData_Length);
                editor.putString("schedule" + i, "텍스트표기" + SavedData_Length);
                editor.putInt("time" + i, SavedData_Length);
                editor.commit(); //완료한다.


                schedule = sp.getString("schedule" + i, "");
                time = sp.getInt("time" + i, 0);


                TextView tvData = (TextView) findViewById(R.id.tv_data);
                tvData.setText(SavedData_Length + schedule + time);
            }
        }


    }


    public void onDeleteData(View v) {
        sp = getSharedPreferences("shared", MODE_PRIVATE);
        editor = sp.edit();
        SavedData_Length = 0;
        editor.clear();
        editor.commit();
        String schedule = sp.getString("schedule", "");
        int time = sp.getInt("time", 0);
        TextView tvData = (TextView) findViewById(R.id.tv_data);
        tvData.setText(schedule + time);
    }
}