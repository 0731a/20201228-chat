package com.example.mbtichat.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.mbtichat.Model.MbtiQuestionModel;
import com.example.mbtichat.R;
import com.example.mbtichat.Service.MbtiTest;

import java.util.ArrayList;

public class MbtiTestAdapter extends BaseAdapter {
    MbtiTest mbtiTest = null;
    ArrayList<MbtiQuestionModel> items = new ArrayList<MbtiQuestionModel>();
    Context context;

    public void setMbtiTest(MbtiTest mbtiTest){
        this.mbtiTest = mbtiTest;
    }
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        context = parent.getContext();
        final MbtiQuestionModel listItem = items.get(position);

        if( convertView == null ){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_mbtitest_item, parent, false);
        }

        TextView question = convertView.findViewById(R.id.question);
        final TextView index = convertView.findViewById(R.id.index);
        RadioGroup radioGroup = convertView.findViewById(R.id.answer);
        RadioButton yes = convertView.findViewById(R.id.yes);
        RadioButton no = convertView.findViewById(R.id.no);

        yes.setText(listItem.getFirstQuestion());
        no.setText(listItem.getSecondQuestion());
        index.setText(listItem.getIndex()+"번째 질문");
        Log.d("listItem.getAnswer",listItem.getIndex()+"는 "+listItem.getAnswer());

        switch( listItem.getAnswer() ){
            case 0 : radioGroup.clearCheck(); break;
            case 1 : yes.setChecked(true); break;
            case 2 : no.setChecked(true); break;
        }

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mbtiTest.getAnswer(listItem.getIndex(),true);
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mbtiTest.getAnswer(listItem.getIndex(),false);
            }
        });

        return convertView;
    }

    private void addItem(MbtiQuestionModel item){
        items.add(item);
    }

    public void clearQuestions(){
        items.clear();
    }

    public void setQuestions(int page ){
        clearQuestions();
        for( int i= page*9; i < page*9 + 9 && i < mbtiTest.questionList.size() ; i++ ){
                addItem(mbtiTest.questionList.get(i));
        }
    }

    public boolean allQuestionsComplete(int page){
        for( int i= page*9; i < page*9 + 9 && i < mbtiTest.questionList.size() ; i++ ){
            if( mbtiTest.questionList.get(i).getAnswer() == 0 ) return false;
        }
        return true;
    }


}
