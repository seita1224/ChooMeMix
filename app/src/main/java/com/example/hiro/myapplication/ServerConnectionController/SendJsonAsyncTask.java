package com.example.hiro.myapplication.ServerConnectionController;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by seita on 2016/10/21.
 */

public class SendJsonAsyncTask extends AsyncTask<String,Void,Void> {

    //フィールド
    private JSONObject json = null;
    private URL url = null;

    //---------------------------------コンスラクタ---------------------------------
    public SendJsonAsyncTask(){}

    public SendJsonAsyncTask(URL url){
        this.url = url;
    }

    public SendJsonAsyncTask(URL url, JSONObject json){
        this.url = url;
        this.json = json;
    }
    //------------------------------------------------------------------------------


    //非同期処理(文字列送信してる)
    @Override
    protected Void doInBackground(String... str) {
        HttpURLConnection httpc = null; //Http通信用コネクター
        OutputStream out = null;    //出力用OutPutStream

        try {
            //POST送信用プロパティの設定
            httpc = (HttpURLConnection)url.openConnection();
            httpc.setDoOutput(true);
            httpc.setRequestMethod("POST");
            httpc.setRequestProperty("Content-Type","text/plain");
            httpc.setChunkedStreamingMode(0);

            //出力用のOutputStreamの生成
            out = httpc.getOutputStream();
            //POST送信処理
            try {
                //送信データの登録
                for(int i = 0;i < str.length;i++){
                    out.write(str[i].getBytes("UTF-8"));
                    Log.d("SendJsonAs..._doIn...",str[i]);
                }

                //送信
                out.flush();
                Log.d("debug",out.toString());

                } catch (IOException e) {
                    Log.e("error",e.toString());
            }finally {
                //もしOutputStreamオブジェクトがある場合クローズ
                if(out != null){
                    out.close();
                }
            }
        } catch (IOException e) {
            Log.e("error",e.toString());
        }finally {
            //接続破棄
            httpc.disconnect();
        }
        return null;
    }
}
