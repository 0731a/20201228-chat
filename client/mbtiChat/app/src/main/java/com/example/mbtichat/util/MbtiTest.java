package com.example.mbtichat.util;

import android.content.Context;
import android.util.Log;

import com.example.mbtichat.R;
import com.example.mbtichat.model.MbtiQuestion;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MbtiTest {
    private ReadTxt readTxt;
    private int eCount = 0;
    private int sCount = 0;
    private int tCount = 0;
    private int jCount = 0;
    public ArrayList<MbtiQuestion> questionList = new ArrayList<MbtiQuestion>();
    public ArrayList<Integer> result = new ArrayList<Integer>(8*9);
    public ArrayList<Integer> resultByType = new ArrayList<Integer>(4);

    public MbtiTest(){

    }

    public void setTxt(Context context){
        String result = "";
        InputStream inputStream = context.getResources().openRawResource(R.raw.mbtitest);

        InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader buffreader = new BufferedReader(inputreader);
        String line;
        StringBuilder text = new StringBuilder();

        int i = 0;

        try {
            while (( line = buffreader.readLine()) != null) {
                MbtiQuestion question = new MbtiQuestion();
                question.setAnswer(0);
                question.setType(line.charAt(0));
                question.setIndex(line.charAt(2)-'0');
                question.setQuestion(line.substring(4));

                questionList.add(question);
                Log.d("log",i+" : "+ line);
                i++;
            }
        } catch (IOException e) {
            Log.d("log","읽기 실패");
        }

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

    public void getAnswer( int index , boolean answer ){
        if( true ) result.set(index, 1);
        else result.set(index, 2);
    }

    public void submitAnswer(){
        for( int i = 0; i < 8; i ++ ){
            for( int j = 0; j < 9; j++ ){
                if( result.get( i*9+ j ) == 1){
                    resultByType.set(i, resultByType.get(i)+1);
                }else{
                    resultByType.set(i, resultByType.get(i)-1);
                }
            }
        }
    }

}
