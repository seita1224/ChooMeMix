package com.example.hiro.myapplication.Hollday;


import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by ie4a on 2017/01/26.
 */
public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        //----------------connect取得の非同期を実装して下さい---------------------

        context.startService(new Intent(context, StatusService.class));
    }

    public static class StatusService extends IntentService {

        public StatusService(){
            super("StatusService");
        }

        //日付差分を求めるメソッド
        public static int getDiffDays(Date fromDate, Date toDate){
            int  diffDays = 0;

            if(fromDate != null && toDate != null) {
                // // getTimeメソッドで経過ミリ秒を取得し、２つの日付の差を求める
                long fromDateTime = fromDate.getTime();
                long toDateTime = toDate.getTime();

                // 経過ミリ秒÷(1000ミリ秒×60秒×60分×24時間)。端数切り捨て。
                diffDays = (int)(( toDateTime - fromDateTime  ) / (1000 * 60 * 60 * 24 ));
            }

            return diffDays;
        }

        @Override
        protected void onHandleIntent(Intent intent) {

            //データベースOpen
            DatabaseHelper helper = new DatabaseHelper(this);
            SQLiteDatabase mydb = helper.getWritableDatabase();

            //現在の日付を取得
            Calendar now = Calendar.getInstance();
            Date nowdate = now.getTime();

            String sql =
                    "SELECT date,name,comment " +
                            "FROM holiday_table;";
            ArrayList<String> a = new ArrayList<String>();

            //SQLの実行
            Cursor cursor = mydb.rawQuery(sql, null);

//            if(cursor.moveToFirst()){
//                do{
//                    //DBに登録されているholidayの日付を順次格納する
//                    String holiday = cursor.getString(0);
//                    String title = cursor.getString(1);
//                    String comment = cursor.getString(2);
//
//                    //holidayはString型なのでDate型に変換する
//                    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
//                    int diff = 0;
//
//                    try {
//                        Date holidate = format.parse(holiday);
//                        diff = getDiffDays(nowdate,holidate) + 1;
//                        Log.d("test",title + "：でぃふ" + Integer.toString(diff));
//
//                        //日付差が１０日なら
//                        if(diff == 10){
//                            //ステータスバークリック時に開くページを指定する
//                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
//
//                            PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, i, 0);
//
//                            //アイコン設定
//                            Bitmap largeIcon = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.chihaya);
//
//
//                            NotificationCompat.Builder builder =  new NotificationCompat.Builder(getApplicationContext());
//                            builder.setContentIntent(pi);
//                            //タイトル
//                            builder.setTicker("10日後は" + title + "です！");
//                            //大きいアイコン
//                            builder.setLargeIcon(largeIcon);
//                            builder.setSmallIcon(R.drawable.chihaya);
//                            //サブタイトル
//                            builder.setContentTitle("10日後は" + title + "です！");
//                            //コメント
//                            builder.setContentText(title + "ランキングを見る");
//                            builder.setAutoCancel(true);
//
//                            //ステータスバー作成
//                            NotificationManager manager = (NotificationManager)getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
//                            manager.notify(0, builder.build());
//
//                        //日付が明日なら
//                        }else if(diff == 1){
//                            //ステータスバークリック時に開くページを指定する
//                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
//
//
//
//                            PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
//
//
//
//                            //アイコン設定
//                            Bitmap largeIcon = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.chihaya);
//
//                            NotificationCompat.Builder builder =  new NotificationCompat.Builder(getApplicationContext());
//                            builder.setContentIntent(pi);
//                            //タイトル
//                            builder.setTicker("明日は" + title + "です！");
//                            //大きいアイコン
//                            builder.setLargeIcon(largeIcon);
//                            builder.setSmallIcon(R.drawable.chihaya);
//                            //サブタイトル
//                            builder.setContentTitle("明日は" + title + "です！");
//                            //コメント
//                            builder.setContentText("プレゼントを贈りましょう！");
//                            builder.setAutoCancel(true);
//
//                            //ステータスバー作成
//                            NotificationManager manager = (NotificationManager)getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
//                            manager.notify(0, builder.build());
//                        }
//
//                    } catch ( ParseException e ) {
//
//                        Log.d("error","エラー");
//                    }
//
//                }while(cursor.moveToNext());
//            }
//
//            mydb.close();
//
        }
    }


}
