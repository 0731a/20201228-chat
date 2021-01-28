package com.example.mbtichat.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mbtichat.Model.PublicChatModel;
import com.example.mbtichat.R;

import java.util.ArrayList;

public class PublicChatAdapter extends BaseAdapter {
    ArrayList<PublicChatModel> items = new ArrayList<PublicChatModel>();
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
        PublicChatModel listItem = items.get(position);

        if( convertView == null ){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_publicchat_item, parent, false);
        }

        TextView nickNameText = convertView.findViewById(R.id.nickName);
        TextView timeText = convertView.findViewById(R.id.time);
        TextView mbtiText = convertView.findViewById(R.id.writer);
        TextView textText = convertView.findViewById(R.id.text);

        nickNameText.setText(listItem.getNickName());
        timeText.setText(listItem.getDate());
        mbtiText.setText(listItem.getMbti());
        textText.setText(listItem.getText());

        return convertView;
    }

    public void addItem(PublicChatModel item){
        items.add(item);
    }

    public void resetItem(){
        items.clear();
    }
}
