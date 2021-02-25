package com.example.mbtichat.Activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mbtichat.R;
import com.example.mbtichat.util.Config;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    EditText id;
    EditText nickName;
    EditText email;
    EditText password;
    EditText passwordConfirm;
    EditText phone;
    EditText age;
    TextView passwordConfirmResult;

    int colorOk;
    int colorWarning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        id = (EditText)findViewById(R.id.id);
        nickName = (EditText)findViewById(R.id.nickName);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        passwordConfirm = (EditText) findViewById(R.id.passwordConfirm);
        phone =  (EditText)findViewById(R.id.phone);
        age =  (EditText)findViewById(R.id.age);

        passwordConfirmResult = (TextView) findViewById(R.id.passwordConfirmResult);

        Button emailConfirm = (Button)findViewById(R.id.emailConfirm);
        Button phoneConfirm = (Button)findViewById(R.id.phoneConfirm);
        Button idConfirm = (Button) findViewById(R.id.idConfirm);
        final TextView idConfirmResult = (TextView)findViewById(R.id.idConfirmTextView);
        Button register = (Button)findViewById(R.id.register);

        colorWarning = ContextCompat.getColor(this,R.color.colorWarning);
        colorOk = ContextCompat.getColor(this,R.color.colorOk);

        emailConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        idConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestDuplicateId(id.getText().toString(),idConfirmResult);
            }
        });
        phoneConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idS = id.getText().toString().trim();
                String nickNameS = nickName.getText().toString().trim();
                String emailS = email.getText().toString().trim();
                String passwordS = password.getText().toString().trim();
                String phoneS = phone.getText().toString().trim();
                String ageS = age.getText().toString().trim();
                requestRegister(idS,nickNameS,emailS,ageS,passwordS,phoneS);
            }
        });

        passwordConfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 입력되는 텍스트에 변화가 있을 때
                if( passwordConfirm.getText().toString().length() > 0  && passwordConfirm.getText().toString().equals(password.getText().toString())){
                    passwordConfirmResult.setText("비밀번호와 비밀번호확인이 일치합니다");
                    passwordConfirmResult.setTextColor(colorOk);
                }else{
                    passwordConfirmResult.setText("비밀번호와 비밀번호확인이 일치하지 않습니다.");
                    passwordConfirmResult.setTextColor(colorWarning);
                }
            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // 입력이 끝났을 때
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // 입력하기 전에
            }

        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 입력되는 텍스트에 변화가 있을 때
                if( passwordConfirm.getText().toString().equals(password.getText().toString())){
                    passwordConfirmResult.setText("비밀번호와 비밀번호확인이 일치합니다");
                    passwordConfirmResult.setTextColor(colorOk);
                }else{
                    passwordConfirmResult.setText("비밀번호와 비밀번호확인이 일치하지 않습니다.");
                    passwordConfirmResult.setTextColor(colorWarning);
                }

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // 입력이 끝났을 때
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // 입력하기 전에
            }

        });


    }

    public void requestDuplicateId(String id, final TextView idConfirmResult ){
        //url 요청주소 넣는 editText를 받아 url만들기
        String url = Config.IP_ADDRESS+"/user/DuplicateId";

        //JSON형식으로 데이터 통신을 진행합니다!
        JSONObject registerJson = new JSONObject();
        try {
            //입력해둔 edittext의 id와 pw값을 받아와 put해줍니다 : 데이터를 json형식으로 바꿔 넣어주었습니다.
            registerJson.put("id", id);

            //이제 전송해볼까요?
            final RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);
            final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,registerJson, new Response.Listener<JSONObject>() {

                //데이터 전달을 끝내고 이제 그 응답을 받을 차례입니다.
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        Log.d("Test","데이터전송 성공");

                        //받은 json형식의 응답을 받아
                        JSONObject jsonObject = new JSONObject(response.toString());

                        //key값에 따라 value값을 쪼개 받아옵니다.
                        String result= jsonObject.getString("message");
                        idConfirmResult.setText(result);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //서버로 데이터 전달 및 응답 받기에 실패한 경우 아래 코드가 실행됩니다.
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Toast.makeText(RegisterActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
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

    public void requestRegister(String id, String nickName, String email, String age, String password, String phone){
        //url 요청주소 넣는 editText를 받아 url만들기
        String url = Config.IP_ADDRESS+"/user/register";

        //JSON형식으로 데이터 통신을 진행합니다!
        JSONObject registerJson = new JSONObject();
        try {
            //입력해둔 edittext의 id와 pw값을 받아와 put해줍니다 : 데이터를 json형식으로 바꿔 넣어주었습니다.
            registerJson.put("id", id);
            registerJson.put("nickName",nickName);
            registerJson.put("email",email);
            registerJson.put("phone",phone);
            registerJson.put("age",age);
            registerJson.put("password",password);

            //이제 전송해볼까요?
            final RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);
            final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,registerJson, new Response.Listener<JSONObject>() {

                //데이터 전달을 끝내고 이제 그 응답을 받을 차례입니다.
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        Log.d("Test","데이터전송 성공");

                        //받은 json형식의 응답을 받아
                        JSONObject jsonObject = new JSONObject(response.toString());

                        //key값에 따라 value값을 쪼개 받아옵니다.
                        String resultId = jsonObject.getString("approve_id");
                        String resultPassword = jsonObject.getString("approve_pw");

                        //만약 그 값이 같다면 로그인에 성공한 것입니다.
                        if(resultId.equals("OK") & resultPassword.equals("OK")){
                            Log.d("Register","회원가입 성공");
                            //이 곳에 성공 시 화면이동을 하는 등의 코드를 입력하시면 됩니다.
                        }else{
                            Log.d("Register","회원가입 실패");
                            //로그인에 실패했을 경우 실행할 코드를 입력하시면 됩니다.
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //서버로 데이터 전달 및 응답 받기에 실패한 경우 아래 코드가 실행됩니다.
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Toast.makeText(RegisterActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
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

