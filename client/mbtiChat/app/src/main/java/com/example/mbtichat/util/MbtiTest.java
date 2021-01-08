package com.example.mbtichat.util;

import android.content.Context;
import android.util.Log;

import com.example.mbtichat.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MbtiTest {
    private ReadTxt readTxt;
    ByteArrayOutputStream byteArrayOutputStream;
    private int eCount = 0;
    private int sCount = 0;
    private int tCount = 0;
    private int jCount = 0;
    ArrayList<Mbti> questionList;


    public MbtiTest(){

    }

    public void setTxt(Context context){
        String result = "";
        InputStream txtResource = context.getResources().openRawResource(R.raw.mbtitest);

        InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader buffreader = new BufferedReader(inputreader);
        String line;
        StringBuilder text = new StringBuilder();

        try {
            while (( line = buffreader.readLine()) != null) {
                myView.append(line);
                Log.d("log",line);
                myView.append("\n");
            }
        } catch (IOException e) {
            Log.d("log","읽기 실패");
        }



        /*
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i;
        try {
            i = txtResource.read();
            while (i != -1) {
                byteArrayOutputStream.write(i);
                i = txtResource.read();
            }
            result = new String(byteArrayOutputStream.toByteArray(), "UTF-8");
            txtResource.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */

    }

    public String getTestResult(){
        String result = "";

        if(eCount>0) result += 'E' ;
        else result += "I";
        if(sCount>0) result += 'S' ;
        else result += 'N';
        if(tCount>0) result += 'T';
        else result += 'F';
        if(jCount>0) result +='J';
        else result += 'P';

        return result;
    }
}
