package com.example.dynamicapplicationtwo.learn;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dynamicapplicationtwo.practice.CategoryModel;
import com.example.dynamicapplicationtwo.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class LearnCategoryAdapter extends RecyclerView.Adapter<LearnCategoryAdapter.viewholder>{

    private List<CategoryModel> categoryModelList;

    public LearnCategoryAdapter(List<CategoryModel> categoryModelList) {
        this.categoryModelList = categoryModelList;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_itm, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

        holder.setData(categoryModelList.get(position).getUrl()
                ,categoryModelList.get(position).getName()
                ,position);
    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    class viewholder extends RecyclerView.ViewHolder {

        private CircleImageView imageview;
        private TextView title;
        public viewholder(@NonNull View itemView) {
            super(itemView);

            imageview = itemView.findViewById(R.id.image_view);
            title = itemView.findViewById(R.id.title);
        }

        private void setData(String url, final String title, final int position){
            Glide.with(itemView.getContext()).load(url).into(imageview);
            this.title.setText(title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent setIntent = new Intent(itemView.getContext(), LearnSetsActivity.class);
                    setIntent.putExtra("title",title);
                    setIntent.putExtra("position", position);
                    itemView.getContext().startActivity(setIntent);
                }
            });
        }

    }

}
