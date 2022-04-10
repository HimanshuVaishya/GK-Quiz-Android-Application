package com.example.dynamicapplicationtwo.learn;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dynamicapplicationtwo.practice.QuestionModel;
import com.example.dynamicapplicationtwo.R;

import java.util.List;

public class LearnQuestionAdapter extends RecyclerView.Adapter<LearnQuestionAdapter.Viewholder>{

    private List<QuestionModel> list;
    private String category;

    public LearnQuestionAdapter(List<QuestionModel> list, String category) {
        this.list = list;
        this.category = category;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_item, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        String question= list.get(position).getQuestion();
        String answer= list.get(position).getCorrectANS();

        holder.setData(question, answer, position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Viewholder extends RecyclerView.ViewHolder {

        private TextView question, answer;
        public Viewholder(@NonNull View itemView) {
            super(itemView);

            question = itemView.findViewById(R.id.question);
            answer = itemView.findViewById(R.id.answer);
        }

        private void setData(String question, String answer, int position){
            this.question.setText(position+1+". "+question);
            this.answer.setText("Ans. "+answer);
        }
    }
}
