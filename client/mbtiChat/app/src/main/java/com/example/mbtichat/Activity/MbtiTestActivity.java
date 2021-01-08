package com.example.mbtichat.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mbtichat.R;
import com.example.mbtichat.util.MbtiTest;

public class MbtiTestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mbtitest);

        MbtiTest mbtiTest =  new MbtiTest();
        mbtiTest.setTxt(this);

    }
}
