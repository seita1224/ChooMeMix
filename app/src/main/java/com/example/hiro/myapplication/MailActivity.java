package com.example.hiro.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.hiro.myapplication.DBController.Userdata;
import com.example.hiro.myapplication.ServerConnectionController.ConnectionCallBacks.main.UserSend;
import com.example.hiro.myapplication.ServerConnectionController.ConnectionHelper;

/**
 * Created by 2130085 on 2016/11/21.
 */

public class MailActivity extends Activity{

    //----------------ここから通信処理準備
    Userdata userdata;  //登録用ユーザデータ
    ConnectionHelper connectionHelper;
    //----------------ここまで


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);

        //------------ユーザデータの登録処理の設定
        //グローバル変数のユーザデータのオブジェクト取得
        userdata = (Userdata)this.getApplication();
        //ユーザデータの初期化
        userdata.UserdataReset();

        /*
        このあたりでユーザデータをセットする
         */

        connectionHelper = new ConnectionHelper(getApplicationContext());
        connectionHelper.setConnectionCallBack(new UserSend() {
            @Override
            public void responseUserMessage(String message) {
                //この中に登録後の処理を記述
            }
        });
        //--------------------------------------------------


        Button mailRegiButton = (Button) findViewById(R.id.mailRegiButton);
        mailRegiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText mail = (EditText) findViewById(R.id.mailText);
                EditText pass = (EditText) findViewById(R.id.passText);
                EditText passReview = (EditText) findViewById(R.id.passReviewText);


                //判定パターン
                //Pattern p = Pattern.compile("[0-9a-zA-Z]+");



                // 入力された文字を取得
                String ma = mail.getText().toString();

                String pa = pass.getText().toString();

                String pare = passReview.getText().toString();

                if(ma.length() >= 6&&ma.length() <= 20 ) {
                }else if(pa == pare){
                    //画面推移
                    Intent intent = new Intent(MailActivity.this, ConfigurationActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent2 = new Intent(MailActivity.this, StartActivity.class);
                    startActivity(intent2);
                }
            }
        });

        connectionHelper.sendRegistrationUser(userdata);
    }

}
