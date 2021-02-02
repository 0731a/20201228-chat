package com.example.mbtichat.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mbtichat.R;

public class RandomChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_randomchat);

        Button sendMessageButton = (Button) findViewById(R.id.sendMessage);
        Button messageListButton = (Button) findViewById(R.id.messageList);

        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RandomChatActivity.this,randChatMatchingActivity.class);
                startActivity(intent);
            }
        });

        messageListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RandomChatActivity.this,RandomChatListActivity.class);
                startActivity(intent);
            }
        });
    }
}