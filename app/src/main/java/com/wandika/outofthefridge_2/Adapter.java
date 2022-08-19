package com.wandika.outofthefridge_2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    LayoutInflater inflater;
    List<Recipe> recipeList;
    Context cnx;


    public Adapter(Context cnx, List<Recipe> recipeList)
    {
        this.cnx = cnx;
        this.inflater = LayoutInflater.from(cnx);
        this.recipeList = recipeList;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_recipe,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        //Bind Data
        holder.recipeId.setText(recipeList.get(position).getId());
        holder.recipeUrl.setText(recipeList.get(position).getUrl());
        holder.recipeLabel.setText(recipeList.get(position).getLabel());
        holder.recipeCalories.setText(recipeList.get(position).getCalories());
        holder.recipeTime.setText(recipeList.get(position).getTime());
        Picasso.get().load(recipeList.get(position).getImgUrl()).into(holder.recipeImg);

        holder.recipeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(recipeList.get(position).getUrl()));
                browserIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                cnx.startActivity(browserIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView recipeLabel, recipeCalories, recipeTime, recipeId, recipeUrl;
        ImageView recipeImg;
        Button recipeBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            recipeId = itemView.findViewById(R.id.it_id_recipe);
            recipeUrl = itemView.findViewById(R.id.it_url_recipe);
            recipeLabel = itemView.findViewById(R.id.it_label_recipe);
            recipeCalories = itemView.findViewById(R.id.it_calories_recipe);
            recipeTime = itemView.findViewById(R.id.it_time_recipe);
            recipeImg = itemView.findViewById(R.id.it_img_recipe);
            recipeBtn = itemView.findViewById(R.id.it_detail_recipe);

        }
    }
}
