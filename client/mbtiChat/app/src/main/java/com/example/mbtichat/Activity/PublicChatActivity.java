package com.example.mbtichat.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mbtichat.Adapter.PublicChatAdapter;
import com.example.mbtichat.Model.UserModel;
import com.example.mbtichat.R;
import com.example.mbtichat.Service.PublicChatService;
import com.example.mbtichat.Service.UserService;
import com.example.mbtichat.Util.Config;

import org.json.JSONObject;

public class PublicChatActivity extends AppCompatActivity {
    PublicChatAdapter publicChatAdapter;
    EditText text;
    ListView publicChatListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicchat);

        Button writeButton = (Button)findViewById(R.id.write);
        text = (EditText)findViewById(R.id.text);
        publicChatListView = findViewById(R.id.listView);

        publicChatAdapter = new PublicChatAdapter();
        requestGetChats();
        publicChatListView.setAdapter(publicChatAdapter);

        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPost(text.getText().toString(),UserService.myUser);
            }
        });
    }

    public void requestPost(String text, UserModel user){
        //url 요청주소 넣는 editText를 받아 url만들기
        String url = Config.IP_ADDRESS+"/publicChat/writeMessage";


        //JSON형식으로 데이터 통신을 진행합니다!
        JSONObject testjson = new JSONObject();
        try {
            //입력해둔 edittext의 id와 pw값을 받아와 put해줍니다 : 데이터를 json형식으로 바꿔 넣어주었습니다.
            testjson.put("text", text);
            testjson.put("idx", user.getIdx());
        }catch(Exception e){
            e.printStackTrace();
        }

            //이제 전송해볼까요?
            final RequestQueue requestQueue = Volley.newRequestQueue(PublicChatActivity.this);
            final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,testjson, new Response.Listener<JSONObject>() {

                //데이터 전달을 끝내고 이제 그 응답을 받을 차례입니다.
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        Log.d("Test","데이터전송 성공");
                        //PublicChatService.jsonParsing( response.toString(),publicChatAdapter);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //서버로 데이터 전달 및 응답 받기에 실패한 경우 아래 코드가 실행됩니다.
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Toast.makeText(PublicChatActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            });
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(jsonObjectRequest);
            Log.d("Test","요청 보냄");
            //
            requestGetChats();
    }


    public void requestGetChats() {
        //url 요청주소 넣는 editText를 받아 url만들기
        String url = Config.IP_ADDRESS+"/publicChat/getMessage";

        //JSON형식으로 데이터 통신을 진행합니다!
        JSONObject testjson = new JSONObject();
        try {

        }catch( Exception e ){
            e.printStackTrace();
        }
            //이제 전송해볼까요?
            final RequestQueue requestQueue = Volley.newRequestQueue(PublicChatActivity.this);
            final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, testjson, new Response.Listener<JSONObject>() {

                //데이터 전달을 끝내고 이제 그 응답을 받을 차례입니다.
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        Log.d("Test", response.toString());
                        PublicChatService.jsonParsing( response.toString(),publicChatAdapter);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //서버로 데이터 전달 및 응답 받기에 실패한 경우 아래 코드가 실행됩니다.
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Toast.makeText(PublicChatActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            });
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(jsonObjectRequest);
            Log.d("Test", "요청 보냄");




    }


}
