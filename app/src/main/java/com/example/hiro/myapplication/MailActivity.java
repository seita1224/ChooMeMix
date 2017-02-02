package com.example.hiro.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by 2130085 on 2016/11/21.
 */

public class MailActivity extends Activity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);

        Button mailRegiButton = (Button) findViewById(R.id.mailRegiButton);
        mailRegiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText mail = (EditText) findViewById(R.id.mailText);
                EditText pass = (EditText) findViewById(R.id.passText);
                EditText passReview = (EditText) findViewById(R.id.passReviewText);

                TextView textView = (TextView) findViewById(R.id.textView11);
                TextView textView1 = (TextView) findViewById(R.id.textView13);
                TextView textView2 = (TextView) findViewById(R.id.textView14);

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
    }
    
}
