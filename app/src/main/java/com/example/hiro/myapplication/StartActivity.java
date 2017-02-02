package com.example.hiro.myapplication;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.hiro.myapplication.DBController.Userdata;

public class StartActivity extends AppCompatActivity {

    Userdata userdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);

        //現在のユーザ情報(登録済みの場合)を取得する
        userdata = (Userdata) getApplication();

        //起動画面:ロゴのアニメーション設定
        ImageView img = (ImageView)findViewById(R.id.robot);
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.alpha);
        set.setTarget(img);
        set.start();

        Handler handler = new Handler();
        // 2000ms遅延させてsplashHandlerを実行。
        handler.postDelayed(new splashHandler(), 1500);
    }

    class splashHandler implements Runnable {
        public void run() {
//            //インテントを生成し、遷移先のアクティビティクラスを指定する。
//            Intent intent = new Intent( getApplication(),LoginActivity.class);
            Intent intent;

            if(userdata.getToken() == null){
                intent = new Intent(getApplication(),LoginActivity.class);
            }else{
                intent = new Intent(getApplication(),TabActivity.class);
            }

            //次のアクティビティの起動
            startActivity(intent);
            //スプラッシュの終了。
            finish();
        }
    }
}