package com.example.covid19_app_series.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid19_app_series.R;
import com.example.covid19_app_series.pojos.Posts;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    public List<Posts> postList;
    public Context context;

    public PostAdapter(List<Posts> postList, Context context) {
        this.postList = postList;
        this.context = context;
    }

    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.posts_recycleview_layout,parent,false);

        return new ViewHolder(view);
    }

    /**
     * putting details to the view from post queries
     * @param holder
     * @param position
     */

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int position) {
        Posts post = postList.get(position);

        holder.userName.setText(post.getName());
        holder.country.setText(post.getCountry());
        holder.code.setText(post.getCode());
        holder.confirm.setText(post.getConfirm());
        holder.recovered.setText(post.getRecovered());
        holder.critical.setText(post.getCritical());
        holder.death.setText(post.getDeath());
        holder.latitude.setText(post.getLatitude());
        holder.longitude.setText(post.getLongitude());
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView userName,country,code,confirm,recovered,critical,death,latitude,longitude;
        private MaterialButton like;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            userName = itemView.findViewById(R.id.userName);
            country = itemView.findViewById(R.id.country);
            code = itemView.findViewById(R.id.code);
            confirm = itemView.findViewById(R.id.confirm);
            recovered = itemView.findViewById(R.id.recovered);
            critical = itemView.findViewById(R.id.critical);
            death = itemView.findViewById(R.id.death);
            latitude = itemView.findViewById(R.id.latitude);
            longitude = itemView.findViewById(R.id.longitude);
            like = itemView.findViewById(R.id.likeBtn);

        }
    }
}
