package com.example.mbtichat.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import com.example.mbtichat.Adapter.MbtiTestAdapter;
import com.example.mbtichat.R;
import com.example.mbtichat.Service.MbtiTest;
import com.example.mbtichat.Util.Config;

import org.json.JSONException;
import org.json.JSONObject;

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

        final Button next = (Button) findViewById(R.id.next);
        final Button submit  = (Button) findViewById(R.id.submit);
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
                if( page == MaxPage ){
                    submit.setVisibility(View.VISIBLE);
                }
                page++;
                questionAdapter.setQuestions(page);
                questionAdapter.notifyDataSetChanged();

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mbtiTest.submitAnswerResult();
                String result = mbtiTest.getResult();
                request(result);
            }
        });



    }

    public void request(String type){
        //url 요청주소 넣는 editText를 받아 url만들기
        String url = Config.IP_ADDRESS+"/mbti/getMbtiIdxByType";

        //JSON형식으로 데이터 통신을 진행합니다!
        JSONObject testjson = new JSONObject();
        try {
            //입력해둔 edittext의 id와 pw값을 받아와 put해줍니다 : 데이터를 json형식으로 바꿔 넣어주었습니다.
            testjson.put("type", type);

            //이제 전송해볼까요?
            final RequestQueue requestQueue = Volley.newRequestQueue(MbtiTestActivity.this);
            final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,testjson, new Response.Listener<JSONObject>() {

                //데이터 전달을 끝내고 이제 그 응답을 받을 차례입니다.
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        Log.d("Test","데이터전송 성공");

                        //받은 json형식의 응답을 받아
                        JSONObject jsonObject = new JSONObject(response.toString());

                        //key값에 따라 value값을 쪼개 받아옵니다.
                        String resultIdx= jsonObject.getString("idx");
                        Log.d("result",resultIdx);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //서버로 데이터 전달 및 응답 받기에 실패한 경우 아래 코드가 실행됩니다.
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Toast.makeText(MbtiTestActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
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
