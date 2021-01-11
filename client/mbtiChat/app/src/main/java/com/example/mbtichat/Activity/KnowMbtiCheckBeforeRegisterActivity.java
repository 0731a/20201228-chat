package com.example.mbtichat.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mbtichat.R;

public class KnowMbtiCheckBeforeRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowmbticheckbeforeregister);

        Button registerWithMbtiTest  = (Button) findViewById(R.id.registerWithMbtiTest);
        Button registerWithOutMbtiTest  = (Button) findViewById(R.id.registerWithOutMbtiTest);

        registerWithOutMbtiTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KnowMbtiCheckBeforeRegisterActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        registerWithMbtiTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KnowMbtiCheckBeforeRegisterActivity.this, MbtiTestActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
