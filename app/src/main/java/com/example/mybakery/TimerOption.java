package com.example.mybakery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

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

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.add_Btn:
                        BtnCount++;
                        EditText scadule = new EditText(getApplicationContext());
                        EditText setTimer = new EditText(getApplicationContext());
                        LinearLayout.LayoutParams p =
                                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                              ViewGroup.LayoutParams.WRAP_CONTENT);
                        scadule.setLayoutParams(p);
                        scadule.setText("일정내용");
                        scadule.setId(BtnCount);
                        add_Timer_Layout.addView(scadule);
                        break;
                }
            }
        };
    }
}
