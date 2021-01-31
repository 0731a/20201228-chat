package com.example.mbtichat.Activity;

import android.os.Bundle;
import android.util.Log;
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
import com.example.mbtichat.Adapter.BoardListAdapter;
import com.example.mbtichat.R;
import com.example.mbtichat.Service.PublicChatService;
import com.example.mbtichat.Util.Config;

import org.json.JSONObject;

public class BoardListActivity extends AppCompatActivity {
    ListView boardListView;
    BoardListAdapter boardListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_list);

        boardListView = findViewById(R.id.boardList);
        boardListAdapter = new BoardListAdapter();

        boardListView.setAdapter(boardListAdapter);
    }

    public void requestGetChats() {
        //url 요청주소 넣는 editText를 받아 url만들기
        String url = Config.IP_ADDRESS+"/board/getBoardList";

        //JSON형식으로 데이터 통신을 진행합니다!
        JSONObject testjson = new JSONObject();

        //이제 전송해볼까요?
        final RequestQueue requestQueue = Volley.newRequestQueue(BoardListActivity.this);
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, testjson, new Response.Listener<JSONObject>() {

            //데이터 전달을 끝내고 이제 그 응답을 받을 차례입니다.
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d("Test", response.toString());
                    boardListAdapter.jsonParsing( response.toString());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //서버로 데이터 전달 및 응답 받기에 실패한 경우 아래 코드가 실행됩니다.
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(BoardListActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonObjectRequest);
        Log.d("Test", "요청 보냄");

    }
}
