package com.example.mbtichat.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mbtichat.Model.BoardPostModel;
import com.example.mbtichat.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PostAdapter  extends BaseAdapter {
    ArrayList<BoardPostModel> items = new ArrayList<BoardPostModel>();
    Context context;

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        context = parent.getContext();
        BoardPostModel listItem = items.get(position);

        if( convertView == null ){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_board_post, parent, false);
        }

        TextView writerText = convertView.findViewById(R.id.writer);
        TextView mbtiText = convertView.findViewById(R.id.mbti);
        TextView textText = convertView.findViewById(R.id.text);
        TextView dateText = convertView.findViewById(R.id.date);

        writerText.setText(listItem.getWriter());
        mbtiText.setText(listItem.getUser_mbti());
        dateText.setText(listItem.getCreatedAt());
        textText.setText(listItem.getText());
        return convertView;
    }

    public void addItem(BoardPostModel item){
        items.add(item);
    }

    public void jsonParsing(String jsonString){

        this.resetItem();

        try{
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray chatArray = jsonObject.getJSONArray("data");

            for(int i=0; i< chatArray.length(); i++)
            {
                JSONObject boardJsonObject = chatArray.getJSONObject(i);

                BoardPostModel item = new BoardPostModel();

                item.setWriter(boardJsonObject.getString("boardpost_writer"));
                item.setIdx(Integer.getInteger(boardJsonObject.getString("boardpost_idx")));
                item.setCreatedAt(boardJsonObject.getString("boardpost_createdAt"));
                item.setText(boardJsonObject.getString("boardpost_text"));
                item.setText(boardJsonObject.getString("user_mbti"));

                this.addItem(item);
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }

        this.notifyDataSetChanged();

    }

    public void resetItem(){
        items.clear();
    }
}
