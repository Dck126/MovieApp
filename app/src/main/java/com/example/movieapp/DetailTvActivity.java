package com.example.movieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.movieapp.models.tv.TvShowDetail;
import com.example.movieapp.networks.Consts;
import com.example.movieapp.networks.GetRetrofit;
import com.example.movieapp.networks.MovieService;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailTvActivity extends AppCompatActivity {

    ImageView ivBackdrop;
    TextView tvTitle, tvNumOfEpisodes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ivBackdrop = findViewById(R.id.iv_backdrop);
        tvTitle = findViewById(R.id.tv_title);
        tvNumOfEpisodes = findViewById(R.id.tv_number_of_episodes);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Integer tvId = getIntent().getIntExtra("TV ID", 0);
        load(tvId);
    }

    private void load(Integer id) {
        MovieService service = GetRetrofit.getInstance();

        Map<String, String> params = new HashMap<>();
        params.put("api_key", Consts.APIKEY);
        params.put("language", Consts.LANGUAGE);
        Call<TvShowDetail> call = service.tvDetail(id, params);

        call.enqueue(new Callback<TvShowDetail>() {
            @Override
            public void onResponse(Call<TvShowDetail> call, Response<TvShowDetail> response) {
                if(response.isSuccessful() && response.body() != null) {
                    TvShowDetail tvShowDetail = response.body();
                    tvTitle.setText(tvShowDetail.getTitle());
                    tvNumOfEpisodes.setText(tvShowDetail.getEpisodes());

                    String uri = Consts.IMAGEBASEURL + tvShowDetail.getBackdropImage();
                    Glide.with(DetailTvActivity.this).load(uri).into(ivBackdrop);
                    getSupportActionBar().setTitle(tvTitle.getText());

                }else {
                    Log.d(Consts.APIERROR, "error");
                }
            }

            @Override
            public void onFailure(Call<TvShowDetail> call, Throwable t) {

                Log.d(Consts.APIERROR, t.getMessage());
            }
        });
    }
}