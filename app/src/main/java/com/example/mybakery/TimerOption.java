package com.example.mybakery;

import androidx.appcompat.app.AppCompatActivity;


import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.Gravity;
import android.view.View;

import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TimerOption extends AppCompatActivity {
    int SavedData_Length; //저장된 데이터 갯수
    int count; // 타이머 갯수
    String schedule; // 일정 내용
    int time; // 일정의 시간

    LinearLayout add_Timer_Layout;
    ListView listView;
    EditText make_Schedule;
    EditText make_Time;
    Button make_dellBtn;

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    ArrayList<Integer> SD_Index = new ArrayList<>(8);
    ArrayList<String> Schedule_Index = new ArrayList<>(8);
    ArrayList<Integer> Time_Index = new ArrayList<>(8);

    public int saveDATA(int count){
        count = sp.getInt("count",SavedData_Length);;
        return count;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.option_timer_setting);

        ArrayAdapter<Integer> SD_Adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,SD_Index);

        listView = findViewById(R.id.TimerList);

        listView.setAdapter(SD_Adapter);

        sp = getSharedPreferences("shared", MODE_PRIVATE);
        editor = sp.edit();

        add_Timer_Layout = (LinearLayout) findViewById(R.id.add_Timer_Layout);

        SavedData_Length = sp.getInt("SavedData_Length",0);
        
        if(SavedData_Length == 0){
            Toast.makeText(getApplicationContext(), "타이머를 생성 해주세요", Toast.LENGTH_SHORT).show();
        } else {
            for(int i = 0; i <= SavedData_Length; i++) {
                String schedule = sp.getString("schedule", "텍스트 표기" + SavedData_Length);
                int ScheduleCount = sp.getInt("time", 0);
                TextView tvData = (TextView) findViewById(R.id.tv_data);
                tvData.setText(SavedData_Length + schedule + ScheduleCount);
            }
        }
        listView.setAdapter(SD_Adapter);

    }

    public void onSaveData(View view){

        sp = getSharedPreferences("shared", MODE_PRIVATE);
        editor = sp.edit();

        ArrayAdapter<Integer> SD_Adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,SD_Index);

        if(SavedData_Length >= 8){
            Toast.makeText(getApplicationContext(), "더 이상 만들 수 없음", Toast.LENGTH_SHORT).show();
        } else {
            SavedData_Length ++;
            Toast.makeText(getApplicationContext(), " " + SavedData_Length + " ", Toast.LENGTH_SHORT).show();

            SD_Index.add(SavedData_Length);

            listView.setAdapter(SD_Adapter);


            for (int i = 0; i <= SavedData_Length; i++) {

                editor.putInt("SavedData_Length", SavedData_Length);
                editor.putString("schedule", "텍스트 표기" + SavedData_Length);
                editor.putInt("time", SavedData_Length);
                editor.commit(); //완료한다.


                SavedData_Length = sp.getInt("SavedData_Length", SavedData_Length);
                schedule = sp.getString("schedule", "");
                time = sp.getInt("time", 0);

                SD_Index.add(SavedData_Length);
                Schedule_Index.add(schedule);
                Time_Index.add(time);

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

        SavedData_Length = sp.getInt("SavedData_Length",0);
        schedule = sp.getString("schedule", "");
        time = sp.getInt("time", 0);
        TextView tvData = (TextView) findViewById(R.id.tv_data);
        tvData.setText(SavedData_Length + schedule + time);
    }
}