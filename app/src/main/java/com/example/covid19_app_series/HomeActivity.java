package com.example.covid19_app_series;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.covid19_app_series.adapters.PostAdapter;
import com.example.covid19_app_series.api.RequestPlaceholder;
import com.example.covid19_app_series.api.RetrofitBuilder;
import com.example.covid19_app_series.pojos.Posts;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {


    public RecyclerView postRecycleView;
    public List<Posts> postsList;
    public PostAdapter postAdapter;

    public RetrofitBuilder retrofitBuilder;
    public RequestPlaceholder requestPlaceholder;

    private SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        retrofitBuilder = new RetrofitBuilder();
        requestPlaceholder = retrofitBuilder.getRetrofit().create(RequestPlaceholder.class);

        swipeRefresh = findViewById(R.id.swipeRefresh);

        postRecycleView = (RecyclerView) findViewById(R.id.postRecycleView);

        postsList = new ArrayList<>();
        postAdapter = new PostAdapter(postsList,this);
        postRecycleView.setAdapter(postAdapter);
        postRecycleView.setLayoutManager(new LinearLayoutManager(this));

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                populatePost();
            }
        });

        populatePost();

    }
    public void populatePost(){

        try{
            postsList.clear();

            Call<List<Posts>> postsCall = requestPlaceholder.getAllPost();

            postsCall.enqueue(new Callback<List<Posts>>() {
                @Override
                public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                    if(response.isSuccessful()){
                        if(response.code() == 200){
                            postsList.addAll(response.body());

                            postAdapter.notifyDataSetChanged();
                            swipeRefresh.setRefreshing(false);
                        }else{
                            Log.e("ERR_GET_POSTS", response.message()+"");
                            Toast.makeText(HomeActivity.this, "Error getting posts", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Log.e("ERR_GET_POSTS", response.message()+"");
                        Toast.makeText(HomeActivity.this, "Error getting posts", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<Posts>> call, Throwable t) {

                }
            });

        }catch (Exception e){
            Log.e("ERR_GET_POSTS", e.getMessage());
        }

//        postsList.add(new Posts("1","USA","123",null,null,null,null,null,null,null,null,"Chadie Gil Augis"));
        //        postAdapter.notifyDataSetChanged();

    }
}
//    public Posts(String id, String country, String code, String confirm, String recovered, String critical, String death, String latitude, String longitude, String created_at, String updated_at,String name) {
