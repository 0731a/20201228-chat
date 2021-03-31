package com.example.mbtichat.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mbtichat.data.entity.BoardModel;
import com.example.mbtichat.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BoardListAdapter  extends BaseAdapter {
    ArrayList<BoardModel> items = new ArrayList<BoardModel>();
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
        final BoardModel listItem = items.get(position);

        if( convertView == null ){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_boardlist_item, parent, false);
        }

        TextView boardTitleText = convertView.findViewById(R.id.boardTitle);
        TextView boardDescriptionText = convertView.findViewById(R.id.boardDescription);

        boardTitleText.setText(listItem.getName());
        boardDescriptionText.setText(listItem.getDescription());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("click", listItem.getIdx()+"idx출력 ----------------------");
            }
        });
        return convertView;
    }

    public void addItem(BoardModel item){
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

                BoardModel item = new BoardModel();

                item.setName(boardJsonObject.getString("board_title"));
                item.setIdx(Integer.getInteger(boardJsonObject.getString("idx")));
                item.setDescription(boardJsonObject.getString("board_description"));

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
