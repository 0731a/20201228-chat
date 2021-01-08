package com.example.mbtichat.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    MbtiTest mbtiTest;
    int page = 0;
    int MaxPage =  7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mbtitest);

        Button next = (Button) findViewById(R.id.next);
        Button before = (Button) findViewById(R.id.before);
        questionListView = findViewById(R.id.questions);
        questionAdapter = new MbtiTestAdapter();

        mbtiTest =  new MbtiTest();
        mbtiTest.setTxt(this);
        questionAdapter.setQuestion(page,mbtiTest.questionList);
        questionListView.setAdapter(questionAdapter);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( page == MaxPage ) return;
                page++;
                //questionListView.removeAllViews();
                questionAdapter.setQuestionVisible(page,questionListView);
                //questionAdapter.setQuestion(page,mbtiTest.questionList);
                //questionListView.setAdapter(questionAdapter);


            }
        });

        before.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( page == 0 ) return;
                page--;
               // questionListView.removeAllViews();
                questionAdapter.setQuestionVisible(page,questionListView);
                //questionAdapter.setQuestion(page,mbtiTest.questionList);
                //questionListView.setAdapter(questionAdapter);
            }
        });



    }
}
