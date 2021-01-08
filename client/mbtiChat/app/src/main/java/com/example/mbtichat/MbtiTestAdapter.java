package com.example.mbtichat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mbtichat.model.MbtiQuestion;
import com.example.mbtichat.util.MbtiTest;

import java.util.ArrayList;

public class MbtiTestAdapter extends BaseAdapter {
    ArrayList<MbtiQuestion> items = new ArrayList<MbtiQuestion>();
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
        MbtiQuestion listItem = items.get(position);

        if( convertView == null ){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_mbtitest_item, parent, false);
        }

        TextView question = convertView.findViewById(R.id.question);
        TextView index = convertView.findViewById(R.id.index);

        question.setText(listItem.getQuestion());
        index.setText(listItem.getIndex()+"번째 질문");

        return convertView;
    }

    private void addItem(MbtiQuestion item){
        items.add(item);
    }

    public void clearQuestions(){
        items.clear();
    }

    public void setQuestions(int page, ArrayList<MbtiQuestion> list){
        clearQuestions();
        for( int i= page*9; i < page*9 + 9 && i < list.size() ; i++ ){
                addItem(list.get(i));
        }
    }


}
