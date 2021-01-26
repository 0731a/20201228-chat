package com.example.mbtichat.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mbtichat.Model.UserModel;
import com.example.mbtichat.R;
import com.example.mbtichat.Service.UserService;
import com.example.mbtichat.Util.Config;

import org.json.JSONException;
import org.json.JSONObject;

public class TestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void request(String id, String password){
        //url 요청주소 넣는 editText를 받아 url만들기
        String url = Config.IP_ADDRESS+"/user/login";

        //JSON형식으로 데이터 통신을 진행합니다!
        JSONObject testjson = new JSONObject();
        try {
            //입력해둔 edittext의 id와 pw값을 받아와 put해줍니다 : 데이터를 json형식으로 바꿔 넣어주었습니다.
            testjson.put("id", id);
            testjson.put("password",password);
            String jsonString = testjson.toString(); //완성된 json 포맷

            //이제 전송해볼까요?
            final RequestQueue requestQueue = Volley.newRequestQueue(TestActivity.this);
            final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,testjson, new Response.Listener<JSONObject>() {

                //데이터 전달을 끝내고 이제 그 응답을 받을 차례입니다.
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        Log.d("Test","데이터전송 성공");

                        //받은 json형식의 응답을 받아
                        JSONObject jsonObject = new JSONObject(response.toString());

                        //key값에 따라 value값을 쪼개 받아옵니다.
                        String result = jsonObject.getString("result");
                        String message = jsonObject.getString("message");

                        if( result.equals("true")) {
                            Log.d("result","로그인 성공");
                            String user_id = jsonObject.getString("id");
                            String user_idx = jsonObject.getString("idx");
                            String user_nickname = jsonObject.getString("nickName");

                            UserModel user = new UserModel();
                            user.setId(user_id);
                            user.setIdx(Integer.parseInt(user_idx));
                            user.setNickName(user_nickname);

                            UserService.myUser = user;

                            /*
                            Intent intent = new Intent(MainActivity.this, PublicChatActivity.class);
                            startActivity(intent);
                            */
                            Intent intent = new Intent(TestActivity.this, randChatMatchingActivity.class);
                            startActivity(intent);

                        }

                        Toast.makeText(TestActivity.this, message, Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //서버로 데이터 전달 및 응답 받기에 실패한 경우 아래 코드가 실행됩니다.
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Toast.makeText(TestActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            });
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(jsonObjectRequest);
            Log.d("Test","요청 보냄");
            //
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
