package com.example.mybakery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;

public class TimerOption extends AppCompatActivity {
    Button add_Btn;
    LinearLayout add_Timer_Layout;

    int BtnCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.option_timer);

        add_Timer_Layout = (LinearLayout) findViewById(R.id.add_Timer_Layout);
        Button add_Btn = (Button) findViewById(R.id.add_Btn);

        add_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.add_Btn:
                        BtnCount++;
                        EditText schedule = new EditText(getApplicationContext());
                        EditText setTimer = new EditText(getApplicationContext());
                        Button delThis = new Button(getApplicationContext());
                        LinearLayout add_Schedule = new LinearLayout(getApplicationContext());
                        LinearLayout.LayoutParams setWH = new LinearLayout.LayoutParams(5000,4000);
                        LinearLayout.LayoutParams Input_Schedule =
                                new LinearLayout.LayoutParams(500, 200);
                        LinearLayout.LayoutParams InPut_Timer =
                                new LinearLayout.LayoutParams(300, 200);
                        LinearLayout.LayoutParams Del_Btn =
                                new LinearLayout.LayoutParams(200, 200);
                        add_Schedule.setLayoutParams(setWH);
                        schedule.setLayoutParams(Input_Schedule);
                        setTimer.setLayoutParams(InPut_Timer);
                        delThis.setLayoutParams(Del_Btn);
                        schedule.setHint("일정내용");
                        setTimer.setHint("시간");
                        delThis.setText("삭제");
                        delThis.setTextSize(15);
                        schedule.setId(BtnCount);
                        setTimer.setId(BtnCount);
                        delThis.setId(BtnCount);


                        add_Timer_Layout.addView(schedule);
                        add_Timer_Layout.addView(setTimer);
                        add_Timer_Layout.addView(delThis);
                        break;
                }
            }
        });
    }
    @Override
    protected void onPause(){
        super.onPause();
        saveState();
    }

    protected  void saveState(){
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("BtnCount", BtnCount);

        editor.commit();
    }
    protected void restoreState(){  // 데이터를 복구한다.
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        if((pref!=null) && (pref.contains("BtnCount"))){
            BtnCount = pref.getInt("BtnCount", BtnCount);
        }

    }
    protected void clearPref(){  // sharedpreference에 쓰여진 데이터 지우기
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        BtnCount = 0;
        editor.commit();
    }
}
