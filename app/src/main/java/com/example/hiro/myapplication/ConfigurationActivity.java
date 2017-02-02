//出来てる！！！！！！！！

package com.example.hiro.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hiro.myapplication.DBController.Userdata;
import com.example.hiro.myapplication.ServerConnectionController.ConnectionCallBacks.main.UserSend;
import com.example.hiro.myapplication.ServerConnectionController.ConnectionHelper;

public class ConfigurationActivity extends Activity {

    //-------通信関係の変数
    Userdata userdata = new Userdata(); //ここにすべてのステータス(名前、メールアドレスなど)を挿入する
    ConnectionHelper connectionHelper;  //送信用クラス
    //-------ここまで

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        //----------------ここから通信処理
        connectionHelper = new ConnectionHelper(getApplicationContext());
        connectionHelper.setConnectionCallBack(new UserSend() {
            @Override
            public void responseUserMessage(String message) {
                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
            }
        });

        connectionHelper.sendRegistrationUser(userdata);

        //----------------ここまで


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item,getResources().getStringArray(R.array.list_age));

        //ポイント②「android.R.layout.simple_spinner_dropdown_item」ではなく、自作のレイアウト「spinner_dropdown_item.xml」を指定する
        //これによりスピナーを選択した際のドロップダウンリストのtextSize等を個別に設定出来るようになる。
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        Spinner spinner = (Spinner) findViewById(R.id.age);
        spinner.setAdapter(adapter);

        // スピナーのアイテムが選択された時の動作を設定
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //スピナー内のアイテムが選択された場合の処理をここに記載
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                //スピナーでは呼ばれない模様。ただし消せないので「おまじない」として残す。
            }
        });

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.spinner_item,getResources().getStringArray(R.array.list_hobbies));

        //ポイント②「android.R.layout.simple_spinner_dropdown_item」ではなく、自作のレイアウト「spinner_dropdown_item.xml」を指定する
        //これによりスピナーを選択した際のドロップダウンリストのtextSize等を個別に設定出来るようになる。
        adapter2.setDropDownViewResource(R.layout.spinner_dropdown_item);
        Spinner spinner2 = (Spinner) findViewById(R.id.hobby);
        spinner2.setAdapter(adapter2);

        // スピナーのアイテムが選択された時の動作を設定
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //スピナー内のアイテムが選択された場合の処理をここに記載
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                //スピナーでは呼ばれない模様。ただし消せないので「おまじない」として残す。
            }
        });
    }
}