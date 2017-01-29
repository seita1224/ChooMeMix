package com.example.hiro.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.FileDescriptor;
import java.io.IOException;

/**
 * Created by 2130085 on 2016/11/24.
 */

public class ProductregistrationActivity extends Activity{
    private static  final  int RESULT_PICK_IMAGEFILE = 1001;
    private ImageView imageView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productregistration);

        //ratingBar
        RatingBar bar = (RatingBar) findViewById(R.id.scoreBar);
        bar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                String str = String.valueOf(ratingBar.getRating());
                TextView textView = (TextView) findViewById(R.id.text4);
                textView.setText(str);
            }
        });
        //

        //spinner設定
        final Spinner spinner = (Spinner) findViewById(R.id.sceneSpinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Spinner spinner1 = (Spinner) adapterView;
                String spi = spinner.getSelectedItem().toString();

                TextView test3 = (TextView)findViewById(R.id.test3);
                test3.setText(spi + " " + (i+1));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        final Spinner spinner1 = (Spinner)findViewById(R.id.sceneS) ;
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Spinner spinner2 = (Spinner) adapterView;
                String spi2 = spinner1.getSelectedItem().toString();

                TextView text4 = (TextView)findViewById(R.id.text4);
                text4.setText(spi2 + "" + (i+1));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        final Spinner spinner2 = (Spinner)findViewById(R.id.spinnerw) ;
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Spinner spinner3 = (Spinner) adapterView;
                String spi3 = spinner2.getSelectedItem().toString();

                TextView text5 = (TextView)findViewById(R.id.text5);
                text5.setText(spi3 + "" + (i+1));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //

        //登録ボタン
        Button pregiButton = (Button)findViewById(R.id.pregiButton);
        pregiButton.setOnClickListener(new View.OnClickListener() {

            //
            @Override
            public void onClick(View view) {
                EditText syname = (EditText)findViewById(R.id.syname);
                EditText reviewText = (EditText)findViewById(R.id.reviewText);

                TextView test1 = (TextView)findViewById(R.id.test1);
                TextView test2 = (TextView)findViewById(R.id.test2);




                String sy = syname.getText().toString();
                test1.setText(sy);

                String re = reviewText.getText().toString();
                test2.setText(re);



                //Intent intent = new Intent(ProductregistrationActivity.this,ConfirmationresultActivity.class);
                //startActivity(intent);
            }
        });


        //画像参照ボタン
        Button RefeButton = (Button) findViewById(R.id.RefeButton);
        RefeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("iamge/*");
                startActivityForResult(intent, RESULT_PICK_IMAGEFILE);
            }
        });
    }

    private String getGalleryPath(){
        return Environment.getExternalStorageDirectory() + "/" + Environment.DIRECTORY_DCIM + "/";}

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        if (requestCode == RESULT_PICK_IMAGEFILE && resultCode == Activity.RESULT_OK) {
            Uri uri = null;
            if (resultData != null) {
                uri = resultData.getData();
                Log.i("", "Uri:" + uri.toString());

                try {
                    Bitmap bmp = getBitmapFromUri(uri);
                    imageView.setImageBitmap(bmp);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =
                getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }

    }

