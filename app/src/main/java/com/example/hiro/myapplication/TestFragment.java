package com.example.hiro.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

//ページの中身？？
public class TestFragment extends Fragment {

    int page;

    public TestFragment() {
    }

    public static TestFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt("page", page);
        TestFragment fragment = new TestFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        page = getArguments().getInt("page", 0);
        View view;
        view = inflater.inflate(R.layout.fragment_test, container, false);

        //レイアウト読み込み
        switch (page -1){
            case 0:
                view = inflater.inflate(R.layout.fragment_test, container, false);
                Log.d("TestFragment", String.valueOf(page - 1));
                break;
            case 1:
                view = inflater.inflate(R.layout.fragment_test2, container, false);
                Log.d("TestFragment", String.valueOf(page- 1));
                break;
            case 2:
                view = inflater.inflate(R.layout.fragment_test3, container, false);
                Log.d("TestFragment", String.valueOf(page- 1));
                break;
            case 3:
                view = inflater.inflate(R.layout.fragment_test4, container, false);
                Log.d("TestFragment", String.valueOf(page- 1));
                break;
        }

        ((TextView) view.findViewById(R.id.page_text)).setText(" " + page);
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("TestFragment","This Message onPouse : " + String.valueOf(page - 1));
    }
}