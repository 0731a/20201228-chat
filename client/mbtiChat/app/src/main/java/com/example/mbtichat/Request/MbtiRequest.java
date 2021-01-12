package com.example.mbtichat.Request;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mbtichat.Activity.MainActivity;
import com.example.mbtichat.Util.Config;

import org.json.JSONException;
import org.json.JSONObject;

public class MbtiRequest {
    public static int getIdxByType(String mbti, Context context) {
        //url 요청주소 넣는 editText를 받아 url만들기
        String url = Config.IP_ADDRESS + "/mbti/getIdxByType";
        final int[] result = {-1};

        JSONObject testjson = new JSONObject();
        try {
            //입력해둔 edittext의 id와 pw값을 받아와 put해줍니다 : 데이터를 json형식으로 바꿔 넣어주었습니다.
            testjson.put("type", mbti);
            String jsonString = testjson.toString(); //완성된 json 포맷

            //이제 전송해볼까요?
            final RequestQueue requestQueue = Volley.newRequestQueue(context);
            final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, testjson, new Response.Listener<JSONObject>() {
                //데이터 전달을 끝내고 이제 그 응답을 받을 차례입니다.
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        Log.d("Test", "데이터전송 성공");
                        JSONObject jsonObject = new JSONObject(response.toString());
                        result[0] = Integer.parseInt(jsonObject.getString("idx"));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //서버로 데이터 전달 및 응답 받기에 실패한 경우 아래 코드가 실행됩니다.
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(jsonObjectRequest);
            Log.d("Test", "요청 보냄");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result[0];
    }
}


