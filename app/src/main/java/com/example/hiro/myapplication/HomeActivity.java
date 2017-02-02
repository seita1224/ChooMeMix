package com.example.hiro.myapplication;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hiro.myapplication.maikeView.CardRecyclerView;
import com.example.hiro.myapplication.maikeView.HomeCardRecyclerAdapter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

import com.example.hiro.myapplication.AnniversaryDateControllre.DateChecker;

//import static com.example.hiro.myapplication.AnniversaryAddDelDeialogActivity.LOCAL_FILE;


public class HomeActivity extends Activity {

    private TextView mOutputText;
    private Button mButton; //テスト用ボタン
    private ArrayList<String> anniversaryDays = new ArrayList<String>();

    //カードビュー
    CardRecyclerView cardRecyclerView = null;
    HomeCardRecyclerAdapter homeCardRecyclerAdapter = null;
    LinearLayoutManager manager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //カードビューの準備


        mOutputText = (TextView)findViewById(R.id.anniversaryTextView);
        mButton = (Button)findViewById(R.id.button);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                anniversaryDays = readFile(LOCAL_FILE);

                Log.d("HomeActivity1", String.valueOf(DateChecker.checkDate(anniversaryDays)));

                if (DateChecker.checkDate(anniversaryDays) >= 100) {
                    mOutputText.setText("次の予定まで100日以上離れています");
                }else if(DateChecker.checkDate(anniversaryDays) == 0){
                    mOutputText.setText(anniversaryDays.get(DateChecker.flg) +"の日です");
                }else{
                    mOutputText.setText("次の予定は" + String.valueOf(DateChecker.checkDate(anniversaryDays)) + "日後です");
                }
            }
        });

        Intent intent = new Intent().setClass(getApplicationContext(),AnniversaryAddDelDeialogActivity.class);
        startActivity(intent);
    }


    //--------------------------ファイル操作用メソッド--------------------------

    //ファイルの読み込み
    public ArrayList<String> readFile(String file) {
        FileInputStream inputStream;
        ArrayList<String> days = new ArrayList<String>();

        try {
            inputStream = openFileInput(file);
            String lineBuffer = null;

            BufferedReader reader= new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
            while( (lineBuffer = reader.readLine()) != null ) {
                Log.d("HomeActivity1",lineBuffer);
                if(lineBuffer == "null"){
                    continue;
                }
                days.add(lineBuffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parseSortList(days);
    }

    //ファイルから文字列の解析とデータの整列と整列済みデータの読み込み
    private ArrayList<String> parseSortList(ArrayList<String> days){
        String daysName,date;
        ArrayList<String> sortList = new ArrayList<String>();
        int point1,point2;
        for (String line:days){
            //"["から"]"までの文字を抜き出す
            point1 = line.indexOf("]");
            point2 = line.lastIndexOf("[");
            daysName = line.substring(1,point1);
            date = line.substring(point2+1,line.length()-1);
            Log.d("HomeActivity..",daysName+date);

            //日付順に整列する準備
            sortList.add(date + "," + daysName);
        }
        //整列
        Collections.sort(sortList);
        Log.d("HomeActivity",sortList.toString());

        //出来上がったソートデータで上書き
        BufferedWriter writer;
//            writer = new BufferedWriter(new OutputStreamWriter(openFileOutput(LOCAL_FILE, MODE_PRIVATE), "UTF-8"));
            //追記する
            for(String data:sortList){
                if(data != null) {
                    String str[] = data.split(",");
//                    writer.append("[" + str[1] + "]" + "[" + str[0] + "]");
//                    writer.newLine();

                }else{
                    Toast.makeText(getApplicationContext(),"データを読み込めません",Toast.LENGTH_SHORT);
                }
            }
//            writer.close();
        return sortList;
    }
}