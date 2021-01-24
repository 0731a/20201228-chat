package com.example.mbtichat.Service;

import com.example.mbtichat.Adapter.PublicChatAdapter;
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

    public static void jsonParsing(String json, PublicChatAdapter adapter)
    {
        adapter.resetItem();

        try{
            JSONObject jsonObject = new JSONObject(json);
            JSONArray chatArray = jsonObject.getJSONArray("data");

            for(int i=0; i< chatArray.length(); i++)
            {
                JSONObject publicChatJsonObject = chatArray.getJSONObject(i);

                PublicChatModel item = new PublicChatModel();

                item.setText(publicChatJsonObject.getString("text"));
                item.setMbti(publicChatJsonObject.getString("mbti"));
                item.setDate(publicChatJsonObject.getString("createdAt"));
                item.setNickName(publicChatJsonObject.getString("nickName"));

                adapter.addItem(item);
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }

        adapter.notifyDataSetChanged();
    }
}
