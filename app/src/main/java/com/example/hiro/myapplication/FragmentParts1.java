package com.example.hiro.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.TextView;

/**
 * Created by seita_v on 2016/11/26.
 */

public class FragmentParts1 extends Fragment {

    TextView mTextView1;
    TextView mTextView2;
    TextView mTextView3;

    public FragmentParts1(){
    }

    public static TestFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt("page", page);
        TestFragment fragment = new TestFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
