package com.example.hiro.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hiro.myapplication.DBController.Goodsdata;
import com.example.hiro.myapplication.DBController.Userdata;
import com.example.hiro.myapplication.ServerConnectionController.ConnectionCallBacks.main.GoodsRegister;
import com.example.hiro.myapplication.ServerConnectionController.ConnectionHelper;

import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;

import static android.R.attr.data;

/**
 * Created by 2130085 on 2016/11/24.
 */

public class ProductregistrationActivity extends Activity{

    private static  final  int RESULT_PICK_IMAGEFILE = 1001;
    private ImageView imageView;
    private Button button;

    private int sceneID = 0;    //goodsTypeによって値が変わるための退避用変数
    private int goodsType = 0;  //goodsType判別用


    private EditText goodsNameEditText,goodsCommentEditText;

    private ConnectionHelper connectionHelper;
    private Goodsdata goodsdata;

    private Uri m_uri;
    private static final int REQUEST_CHOOSER = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productregistration);

        //-------通信処理関係のインスタンス生成---------

        Userdata userdata = (Userdata)getApplication();
        userdata.setToken("GVnRbhHAi4N7IqZTh0DHKUrMU0Zq5lTi");
        userdata = null;

        connectionHelper = new ConnectionHelper(getApplicationContext());

        goodsdata = new Goodsdata();

        //-------通信処理関係のインスタンス生成---------


        //EditText
        goodsNameEditText = (EditText)findViewById(R.id.goodsName);
        goodsCommentEditText = (EditText)findViewById(R.id.goodsCommentText);

        Log.d(getClass().getName(),goodsCommentEditText.toString());
        goodsNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s == null){
                    return;
                }
                goodsdata.setGoods_name(String.valueOf(s));
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        goodsCommentEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s == null){
                    return;
                }
                goodsdata.setComment(String.valueOf(s));
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        //ratingBar
        RatingBar bar = (RatingBar) findViewById(R.id.scoreBar);
        bar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                goodsdata.setRate(ratingBar.getRating());
            }
        });

        //spinner設定
        final Spinner genresSpinner = (Spinner) findViewById(R.id.genresSpinner);
        final Spinner sceneSpinner = (Spinner)findViewById(R.id.sceneSpinner) ;
        final Spinner spinnerProducts = (Spinner)findViewById(R.id.spinnerProducts) ;

        genresSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                goodsdata.setGenre(String.valueOf(i));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        sceneSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               goodsdata.setScene(String.valueOf(i));
                sceneID = i;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerProducts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 1){
                    sceneSpinner.setVisibility(View.GONE);
                }else{
                    sceneSpinner.setVisibility(View.VISIBLE);
                    goodsdata.setScene(String.valueOf(sceneID));
                }
                goodsType = i + 1;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //登録ボタン
        Button pregiButton = (Button)findViewById(R.id.pregiButton);
        pregiButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                connectionHelper.setGoodsRegister(new GoodsRegister() {
                    @Override
                    public void registerGoods(String message) {
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
                        Log.d(getClass().getName(),"AlertDialog");

                        // アラートダイアログのタイトルを設定します
                        alertDialogBuilder.setTitle("商品登録");
                        // アラートダイアログのメッセージを設定します
                        alertDialogBuilder.setMessage(message);
                        // アラートダイアログの肯定ボタンがクリックされた時に呼び出されるコールバックリスナーを登録します
                        alertDialogBuilder.setPositiveButton("肯定",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                        // アラートダイアログの中立ボタンがクリックされた時に呼び出されるコールバックリスナーを登録します
                        alertDialogBuilder.setNeutralButton("中立",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                        // アラートダイアログの否定ボタンがクリックされた時に呼び出されるコールバックリスナーを登録します
                        alertDialogBuilder.setNegativeButton("否定",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                        // アラートダイアログのキャンセルが可能かどうかを設定します
                        alertDialogBuilder.setCancelable(true);
                        AlertDialog alertDialog = alertDialogBuilder.create();
                        // アラートダイアログを表示します
                        alertDialog.show();
                    }
                });
                connectionHelper.sendGoodsdata(goodsdata, String.valueOf(goodsType));
            }
        });


        //画像参照ボタン
        Button RefeButton = (Button) findViewById(R.id.RefeButton);
        RefeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String photoName = System.currentTimeMillis() + ".jpg";
                ContentValues contentValues = new ContentValues();
                contentValues.put(MediaStore.Images.Media.TITLE, photoName);
                contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
                m_uri = getContentResolver()
                        .insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);

                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, m_uri);
                Intent intentGallery;
                if (Build.VERSION.SDK_INT < 19) {
                    intentGallery = new Intent(Intent.ACTION_GET_CONTENT);
                    intentGallery.setType("image/*");
                } else {
                    intentGallery = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                    intentGallery.addCategory(Intent.CATEGORY_OPENABLE);
                    intentGallery.setType("image/jpeg");
                }
                Intent intent = Intent.createChooser(intentCamera, "画像の選択");
                intent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {intentGallery});
                startActivityForResult(intent, REQUEST_CHOOSER);
            }
        });
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        if(requestCode == REQUEST_CHOOSER) {
            if(resultCode != RESULT_OK) {
                // キャンセル時
                return ;
            }

            Uri resultUri = (resultData != null ? resultData.getData() : m_uri);

            if(resultUri == null) {
                // 取得失敗
                Log.d(getClass().getName(),"取得失敗");
                return;
            }

            // ギャラリーへスキャンを促す
            MediaScannerConnection.scanFile(
                    this,
                    new String[]{resultUri.getPath()},
                    new String[]{"image/*"},
                    null
            );

            Log.d(getClass().getName(),resultUri.toString());

            // 画像を設定
            ImageView imageView = (ImageView)findViewById(R.id.goodsTakeImageView);
            imageView.setImageURI(resultUri);
            goodsdata.setPicture(((BitmapDrawable)imageView.getDrawable()).getBitmap());
        }
    }
}

