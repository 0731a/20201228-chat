package com.example.mbtichat.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mbtichat.Adapter.MbtiTestAdapter;
import com.example.mbtichat.R;
import com.example.mbtichat.Service.MbtiTest;

import java.util.ArrayList;

public class MbtiTestActivity extends AppCompatActivity {
    ListView questionListView;
    MbtiTestAdapter questionAdapter;
    ArrayList<Integer> result;
    MbtiTest mbtiTest;
    int page = 0;
    int MaxPage =  4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mbtitest);

        Button next = (Button) findViewById(R.id.next);
        Button before = (Button) findViewById(R.id.before);
        Button submit  = (Button) findViewById(R.id.submit);
        questionListView = findViewById(R.id.questions);

        questionAdapter = new MbtiTestAdapter();
        mbtiTest =  new MbtiTest();
        mbtiTest.setTxt(this);
        questionAdapter.setMbtiTest(mbtiTest);
        questionAdapter.setQuestions(page);
        questionListView.setAdapter(questionAdapter);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( !questionAdapter.allQuestionsComplete(page) ){
                    Toast.makeText( getApplicationContext(),"아직 답변되지 않은 질문이 존재합니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if( page == MaxPage ) return;
                page++;
                questionAdapter.setQuestions(page);
                questionAdapter.notifyDataSetChanged();

            }
        });

        before.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( page == 0 ) return;
                page--;
                questionAdapter.setQuestions(page);
                questionAdapter.notifyDataSetChanged();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mbtiTest.submitAnswerResult();
                mbtiTest.getResult();
            }
        });



    }
}
