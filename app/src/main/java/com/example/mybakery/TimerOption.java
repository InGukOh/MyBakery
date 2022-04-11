package com.example.mybakery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.logging.Logger;


public class TimerOption extends AppCompatActivity {

    int SavedData_Length; //저장된 데이터 갯수
    int count; // 타이머 갯수
    String schedule; // 일정 내용
    int time; // 일정의 시간

    ListView listView;
    Button btn_add;
    Button delAll;

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    ArrayAdapter<Integer> SD_Adapter;

    ArrayList<Integer> SD_Index = new ArrayList<>();
    ArrayList<String> array = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.option_timer_setting);

        listView = findViewById(R.id.TimerList);
        listView.setAdapter(SD_Adapter);

        array = getStringArrayList(getApplicationContext(),"test");

        SD_Adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,array);
        listView.setAdapter(SD_Adapter);

        sp = getSharedPreferences("shared", MODE_PRIVATE);
        SavedData_Length = sp.getInt("count",0);
        Toast.makeText(getApplicationContext(), "현재 : " + SD_Index.size() +"&" +SavedData_Length, Toast.LENGTH_SHORT).show();


        btn_add = findViewById(R.id.btn_add);
        delAll = findViewById(R.id.delAll);



        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp = getSharedPreferences("shared", MODE_PRIVATE);
                editor = sp.edit();
                SavedData_Length++;
                if(SavedData_Length > 8){
                    Toast.makeText(getApplicationContext(), "추가 불가능", Toast.LENGTH_SHORT).show();
                } else {
                    editor.putInt("count",SavedData_Length);
                    SD_Index.add(SavedData_Length);
                    editor.commit();
                    listView = findViewById(R.id.TimerList);
                    listView.setAdapter(SD_Adapter);
                    SD_Adapter.notifyDataSetChanged();
                }
            }
        });

        delAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp = getSharedPreferences("shared", MODE_PRIVATE);
                editor = sp.edit();
                SavedData_Length = 0;
                SD_Index.clear();
                editor.clear();
                editor.commit();
                SD_Adapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "삭제 후 : " + SavedData_Length, Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void setStringArrayList(Context context,String key, ArrayList<String> valueList){
        sp = PreferenceManager.getDefaultSharedPreferences(context);

        SharedPreferences.Editor editor = sp.edit();
        JSONArray jsonArray = new JSONArray();

        for(int i = 0; i < valueList.size(); i++){
            jsonArray.put(valueList.get(i));
        }

        if(!valueList.isEmpty()){
            editor.putString(key, jsonArray.toString());
        }else{
            editor.putString(key, null);
        }
    }

    public ArrayList<String> getStringArrayList(Context context,String key){
        sp = PreferenceManager.getDefaultSharedPreferences(context);
        String json = sp.getString(key, null);
        ArrayList<String> valueList = new ArrayList<>();
        if(json != null){
            try{
                JSONArray jArray = new JSONArray(json);
                for(int i = 0; i < jArray.length(); i++){
                    String data = jArray.optString(i);
                    valueList.add(data);
                }
            }catch(JSONException e){
                e.printStackTrace();
            }
        }
        return valueList;
    }
}