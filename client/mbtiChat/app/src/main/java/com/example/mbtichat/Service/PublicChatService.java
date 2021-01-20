package com.example.mbtichat.Service;

import com.example.mbtichat.Model.MbtiQuestionModel;
import com.example.mbtichat.Model.PublicChatModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PublicChatService {
    public static ArrayList<PublicChatModel> publicChatList = new ArrayList<PublicChatModel>();

    void setJsonToPublicChatModelList(JSONObject response){

    }

    private void jsonParsing(String json)
    {
        try{
            JSONObject jsonObject = new JSONObject(json);
            JSONArray chatArray = jsonObject.getJSONArray("Chats");

            for(int i=0; i< chatArray.length(); i++)
            {
                JSONObject publicChatJsonObject = chatArray.getJSONObject(i);

                PublicChatModel item = new PublicChatModel();

                item.setText(publicChatJsonObject.getString("text"));
                item.setMbti(publicChatJsonObject.getString("mbti"));
                item.setDate(publicChatJsonObject.getString("date"));
                item.setNickName(publicChatJsonObject.getString("nickName"));

                publicChatList.add(item);
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
