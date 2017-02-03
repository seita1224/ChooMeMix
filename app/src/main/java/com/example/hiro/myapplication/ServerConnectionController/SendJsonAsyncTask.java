package com.example.hiro.myapplication.ServerConnectionController;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import com.example.hiro.myapplication.ServerConnectionController.ConnectionCallBacks.SendCallBack;
import com.example.hiro.myapplication.ServerConnectionController.JsonParse.UserJsonParse;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.R.attr.data;

/**
 * Created by seita on 2016/10/21.
 */

public class SendJsonAsyncTask extends AsyncTask<String,Void,String> {
    //フィールド
    private JSONObject jsonObject = null;
    private JSONArray jsonArray = null;
    private URL url = null;
    private String request;
    private SendCallBack sendCallBack;
    private byte bitByte[];
    String base64Bitmap;
    int flg;

    //---------------------------------コンスラクタ---------------------------------
    public SendJsonAsyncTask(){}

    public SendJsonAsyncTask(URL url){
        this.url = url;
    }

    public SendJsonAsyncTask(URL url, JSONObject json){
        this.url = url;
        this.jsonObject = json;
        flg = 0;
        Log.d(getClass().getName(),json.toString());
    }

    public SendJsonAsyncTask(URL url, JSONArray json){
        this.url = url;
        this.jsonArray = json;
        flg = 1;
        Log.d(getClass().getName(),json.toString());
    }

    public SendJsonAsyncTask(URL url, String request){
        this.url = url;
        this.request = request;
        flg = 2;
        Log.d(getClass().getName(),request);
    }

    public SendJsonAsyncTask(URL url, String request, Bitmap bm){
        this.url = url;
        this.request = request;
        flg = 3;
        if(bm != null){
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            bitByte = baos.toByteArray();
            base64Bitmap = Base64.encodeToString(bitByte,Base64.DEFAULT);
            Log.d(getClass().getName(),base64Bitmap);
        }
    }

    //------------------------------------------------------------------------------


    //非同期処理(文字列送信してる)
    @Override
    protected String doInBackground(String... str) {
        HttpURLConnection httpc = null; //Http通信用コネクター
        OutputStream out = null;    //出力用OutPutStream
        String message = null;

        try {
            InputStream is = null;
            //POST送信用プロパティの設定
            httpc = (HttpURLConnection)url.openConnection();
            httpc.setDoOutput(true);
            httpc.setRequestMethod("POST");
            httpc.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            httpc.setRequestProperty("Content-Length","50000000");
            httpc.setChunkedStreamingMode(0);

            //出力用のOutputStreamの生成
            out = httpc.getOutputStream();
            //POST送信処理

            if(flg == 3){
                //送信データの登録
                if(base64Bitmap != null){
                    request += base64Bitmap;
                    out.write(request.getBytes());
                    out.write(bitByte);
                }else{
                    Log.d(getClass().getName(),"画像ファイルがありません");
                }
            }else {
                //送信データの登録
                out.write(request.getBytes());
            }

            Log.d(getClass().getName(),request);


            //送信
            out.flush();
            Log.d(getClass().getName(),out.toString());

            message = httpc.getResponseMessage();

            //もしOutputStreamオブジェクトがある場合クローズ
            if(out != null){
                out.close();
            }
            if(httpc.getInputStream() != null){
                is = httpc.getInputStream();
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            StringBuilder sb = new StringBuilder();

            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            message = sb.toString();
        } catch (IOException e) {
            Log.e("error",e.toString());
            Log.e(getClass().getName(),httpc.getRequestMethod());
        }finally {
            //接続破棄
            httpc.disconnect();
        }
        if(message == null){
            message = "通信に失敗しました。";
        }
        Log.d(getClass().getName(),message);
        return message;
    }

    @Override
    protected void onPostExecute(String message) {
        super.onPostExecute(message);

        sendCallBack.SendCallBack(message);
    }

    public void setSendCallBack(SendCallBack sc){
        sendCallBack = sc;
    }

}
