package com.example.hiro.myapplication.ServerConnectionController.JsonParse;

import android.util.Log;

import com.example.hiro.myapplication.DBController.Goodsdata;
import com.example.hiro.myapplication.DBController.Userdata;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by seita on 2016/10/24.
 */

//Jsonデータをパース(解析)するクラス
public class UserJsonParse {
    //フィールド
    private JSONObject rootJsonObject = null;
    private Userdata userdata;


    //コンストラクタ
    //デフォルトコンストラクタ
    public UserJsonParse(){}

    public UserJsonParse(Userdata userdata){
        this.userdata = userdata;
    }



//    //JSONのデータを判別するためのメソッド
//    public boolean serectInfo() {
//        Log.d("GoodsJsonPase","serectInfo");
//        try {
//            //ここにデータ種別が増えたときcase文追加
//            if("SerchResult" == rootJsonObject.getString("Type")){
//                return true;
//            }else{
//                return false;
//            }
//        } catch (JSONException e) {
//            Log.e("GoodsJsonPase", e.toString());
//        }
//        return false;
//    }


    //
    public JSONObject getUserJson(){

        try {
            rootJsonObject = new JSONObject();
            rootJsonObject.put("name",userdata.getName());
            rootJsonObject.put("email",userdata.getEmail());
            rootJsonObject.put("password",userdata.getPassword());
            rootJsonObject.put("age",userdata.getAge());
            rootJsonObject.put("sex",userdata.getSex());
            rootJsonObject.put("hobbies_id",userdata.getHobbies());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return rootJsonObject;
    }

    //JSONObjectのgetter,setter
    public JSONObject getJo() {return rootJsonObject;}
    public void setJo(JSONObject jo) {this.rootJsonObject = jo;}

}
