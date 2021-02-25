package com.example.mbtichat.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mbtichat.R;

public class TestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Button publicButton = (Button)findViewById(R.id.publicChat);
        Button randomButton = (Button)findViewById(R.id.randomChat);
        Button boardListButton = (Button)findViewById(R.id.boardList);
        Button makeBoardButton = (Button)findViewById(R.id.makeBoard);
        Button myPageButton = (Button)findViewById(R.id.myPage);


        publicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestActivity.this, PublicChatActivity.class);
                startActivity(intent);
            }
        });
        randomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestActivity.this, RandomChatActivity.class);
                startActivity(intent);
            }
        });
        boardListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestActivity.this,BoardListActivity.class);
                startActivity(intent);
            }
        });
        makeBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestActivity.this, BoardMakeActivity.class);
                startActivity(intent);
            }
        });
        myPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }


}
