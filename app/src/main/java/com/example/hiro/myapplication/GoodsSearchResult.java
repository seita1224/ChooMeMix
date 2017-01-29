package com.example.hiro.myapplication;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.hiro.myapplication.DBController.Goodsdata;
import com.example.hiro.myapplication.ServerConnectionController.ConnectionCallBacks.main.GoodsReceive;
import com.example.hiro.myapplication.ServerConnectionController.ConnectionHelper;
import com.example.hiro.myapplication.maikeView.CardRecyclerView;
import com.example.hiro.myapplication.maikeView.GoodsCardRecyclerAdapter;

import java.util.ArrayList;

public class GoodsSearchResult extends AppCompatActivity {

    GoodsCardRecyclerAdapter goodsCardRecyclerAdapter;
    CardRecyclerView cardRecyclerView;

    ConnectionHelper connectionHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_search_result);

        Intent intent = getIntent();
        String goodsName = intent.getStringExtra("goodsName");
        intent = null;

        connectionHelper = new ConnectionHelper(getApplicationContext());

        connectionHelper.setConnectionCallBack(new GoodsReceive() {
            @Override
            public void goodsReceive(ArrayList<Goodsdata> goodsdatas, String connectionStatus) {
                if(!(goodsdatas.isEmpty())){
                    cardRecyclerView = new CardRecyclerView(getApplicationContext());
                    cardRecyclerView.setGoodsRecyclerAdapter(getApplicationContext(),goodsdatas);
                    setContentView(cardRecyclerView);
                }

            }
        });
        connectionHelper.receiveGoods(goodsName);
    }
}
