package com.example.hiro.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hiro.myapplication.ServerConnectionController.ConnectionCallBacks.AsyncCallBack;
import com.example.hiro.myapplication.ServerConnectionController.ConnectionHelper;
import com.example.hiro.myapplication.ServerConnectionController.JsonParse.RankingJsonPase;
import com.example.hiro.myapplication.ServerConnectionController.ReceiveJsonAsyncTask;

import org.json.JSONObject;

/**
 * Created by 2130085 on 2016/10/28.
 */

public class LoginActivity extends Activity{
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        TextView link = (TextView) findViewById(R.id.textView4);
        TextView link2 = (TextView) findViewById(R.id.textView5);
        Button btn = (Button) findViewById(R.id.button);
        final EditText edt = (EditText) findViewById(R.id.editText);
        EditText edt2 = (EditText) findViewById(R.id.editText2);


        btn.setOnClickListener(new View.OnClickListener() {
            // このメソッドがクリック毎に呼び出される
            public void onClick(View v) {
                intent = new Intent( getApplication(),TabActivity.class);

                ConnectionHelper connection = new ConnectionHelper(getApplicationContext());

//                if(){
                    SharedPreferences data = getSharedPreferences("DataSave", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = data.edit();
                    editor.putInt("LevelSave", 100);
                    editor.apply();
//                }else{
//                    Log.d("ConnectionHelper","reciveRanking_");
//                }

                //次のアクティビティの起動
                startActivity(intent);
            }
        });
        link.setClickable(true);
        link.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                intent = new Intent(getApplication(),MailActivity.class);
                startActivity(intent);
            }
        });
    }
    public void login(){

    }
/**
        link.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(LoginActivity.this,"".class);
                startActivity(intent);
            }
        });

        link2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(LoginActivity.this,"".class);
                startActivity(intent);
            }
        });

        bth.setOnClickListener(new View.OnClickListener() {
        @Override
            public  void onClick(View v){
               Intent intent = new Intent(LoginActivity.this,"".class);
                startActivity(intent);
            }
        });
*/


}
