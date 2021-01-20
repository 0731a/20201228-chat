package com.example.mbtichat.Service;

import com.example.mbtichat.Model.MbtiQuestionModel;
import com.example.mbtichat.Model.PublicChatModel;

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

            JSONArray movieArray = jsonObject.getJSONArray("Movies");

            for(int i=0; i<movieArray.length(); i++)
            {
                JSONObject movieObject = movieArray.getJSONObject(i);

                Movie movie = new Movie();

                movie.setTitle(movieObject.getString("title"));
                movie.setGrade(movieObject.getString("grade"));
                movie.setCategory(movieObject.getString("category"));

                movieList.add(movie);
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
