package com.example.mbtichat.Service;

import android.content.Context;
import android.util.Log;

import com.example.mbtichat.R;
import com.example.mbtichat.Model.MbtiQuestionModel;
import com.example.mbtichat.util.ReadTxt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MbtiTestService {
    private ReadTxt readTxt;
    public static int maxPageLength = 4;
    public static ArrayList<MbtiQuestionModel> questionList = new ArrayList<MbtiQuestionModel>();

    public MbtiTestService(){

    }


    public static void setTxt(Context context){
        String result = "";
        InputStream inputStream = context.getResources().openRawResource(R.raw.mbtitest);


        InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader buffreader = new BufferedReader(inputreader);
        String line;
        StringBuilder text = new StringBuilder();

        int i = 0;

        try {
            while (( line = buffreader.readLine()) != null) {
                Log.d("line",line);
                MbtiQuestionModel question = new MbtiQuestionModel();
                question.setAnswer(0);
                question.setType(line.charAt(0)-'0');
                question.setIndex(i);
                String[] array = line.substring(2).split("\\. ");

                question.setFirstQuestion(array[0]);
                question.setSecondQuestion(array[1]);

                questionList.add(question);
                Log.d("log",i+" : "+ line);
                i++;
            }
        } catch (IOException e) {
            Log.d("log","읽기 실패");
        }

    }

    public static void getAnswer( int index , boolean answer ){
        if( answer ) questionList.get(index).setAnswer(1);
        else questionList.get(index).setAnswer(2);
    }

    public ArrayList<Integer> submitAnswer(){
        ArrayList<Integer> notCheckedAnswers = new ArrayList<Integer>();
        for( int i= 0; i < questionList.size() ; i ++ ){
            if( questionList.get(i).getAnswer() == 0 )
                notCheckedAnswers.add(i);
        }

        return notCheckedAnswers;
    }

    public void submitAnswerResult(){
        ArrayList<Integer> result = submitAnswer();
        if( result.size() == 0 ) Log.d("result", "모두 작성되었습니다.");
        else{
            String s = "";
            for(int i= 0; i < result.size(); i ++ )
                s += ( "," + result.get(i) +" " );
            s+= "문항이 아직 완료되지 않았습니다.";
            Log.d("result",s);
        }
    }

    public String getResult(){
        String result = "";
        int eCount = 0;
        int sCount = 0;
        int tCount = 0;
        int jCount = 0;

        for(int i= 0; i < questionList.size(); i++){
            int type = questionList.get(i).getType();
            int answer = questionList.get(i).getAnswer();
            Log.d("type",type+"");

            switch( type ){
                case 1 : if(answer == 1) eCount++;
                          else eCount--;
                          break;
                case 2 : if(answer == 1) sCount++;
                           else sCount--;
                           break;
                case 3 : if(answer == 1) tCount++;
                           else tCount--;
                           break;
                case 4 : if(answer == 1) jCount++;
                           else jCount--;
                           break;
            }
        }

        Log.d("sCount",sCount+"");

        if( eCount > 0 ) result += "E";
        else result += "I";
        if( sCount > 0 ) result += "S";
        else result += "N";
        if( tCount > 0 ) result += "T";
        else result += "F";
        if( jCount > 0 ) result += "J";
        else result += "P";

        Log.d("result",result+" 결과 입니다. ");
        return result;

    }



}
