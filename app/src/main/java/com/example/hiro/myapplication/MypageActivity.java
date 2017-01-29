//出来てない


package com.example.hiro.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Created by HIRO on 2016/11/25.
 */

public class MypageActivity extends Activity {
    ListView lv;
    Intent intent;
    Switch sw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);
        final TextView textView = (TextView) findViewById(R.id.textView11);


        String[] members = {"プロフィール変更", "閲覧履歴", "あなたが投稿したレビュー", "通知設定", "ヘルプ",};
        lv = (ListView) findViewById(R.id.mypage_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1, members){
            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                TextView view = (TextView)super.getView(position, convertView, parent);
                view.setTextSize(24);
                return view;
            }
        };
        lv.setAdapter(adapter);



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,long l) {

                //ListView listView =(ListView)parent;
                //String item=(String)lv.getItemAtPosition(position);

                switch (position){
                    case 0:
                        intent = new Intent(MypageActivity.this,StatuschangeActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(MypageActivity.this,HysteresisActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(MypageActivity.this,ContributionActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(MypageActivity.this,NoticeActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(MypageActivity.this,HelpActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }
}