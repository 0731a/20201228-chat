package com.example.mbtichat.Activity;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mbtichat.MbtiTestAdapter;
import com.example.mbtichat.R;
import com.example.mbtichat.util.MbtiTest;

import java.util.ArrayList;

public class MbtiTestActivity extends AppCompatActivity {
    ListView questionListView;
    MbtiTestAdapter questionAdapter;
    ArrayList<Integer> result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mbtitest);

        questionListView = findViewById(R.id.questions);
        questionAdapter = new MbtiTestAdapter();

        MbtiTest mbtiTest =  new MbtiTest();
        mbtiTest.setTxt(this);
        questionAdapter.setQuestion(mbtiTest.questionList);
        questionListView.setAdapter(questionAdapter);



    }
}
