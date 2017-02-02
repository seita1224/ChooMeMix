package com.example.hiro.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hiro.myapplication.DBController.Userdata;
import com.example.hiro.myapplication.ServerConnectionController.ConnectionCallBacks.main.UserSend;
import com.example.hiro.myapplication.ServerConnectionController.ConnectionHelper;

/**
 * Created by 2130085 on 2016/10/28.
 */

public class LoginActivity extends Activity{
    Intent intent;
    ConnectionHelper connectionHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Userdata userdata = new Userdata();
        //------------ログイン情報の設定

        /*
        このあたりでEmail,Passwordをセットする
         */

        connectionHelper = new ConnectionHelper(getApplicationContext());
        connectionHelper.setConnectionCallBack(new UserSend() {
            @Override
            public void responseUserMessage(String message) {
                //この中に登録後の処理を記述
                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT);
            }
        });
        //--------------------------------------------------
        TextView link = (TextView) findViewById(R.id.textView4);
        TextView link2 = (TextView) findViewById(R.id.textView5);
        Button btn = (Button) findViewById(R.id.button);
        final EditText edt = (EditText) findViewById(R.id.editText);
        EditText edt2 = (EditText) findViewById(R.id.editText2);


        btn.setOnClickListener(new View.OnClickListener() {
            // このメソッドがクリック毎に呼び出される
            public void onClick(View v) {
                intent = new Intent( getApplication(),TabActivity.class);

                //HTTPリクエスト、レスポンスの処理
                connectionHelper.sendRegistrationUser(userdata);

                //次のアクティビティの起動
//                startActivity(intent);
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
