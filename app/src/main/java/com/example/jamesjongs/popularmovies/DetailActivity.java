package com.example.jamesjongs.popularmovies;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    // Constants
    public static class INTENT_CONSTANTS {
        public static String RATING = "Detail Activity Rating";
        public static String SYNOPSIS = "Detail Activity Synopsis";
        public static String TITLE = "Detail Activity Title";
        public static String RELEASE_DATE = "Detail Activity Release Date";
        public static String POSTER_IMAGE = "Detail Activity Poster Image";
    }


    private View mContentView;

    // Methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        String title = intent.getStringExtra(INTENT_CONSTANTS.TITLE);
        TextView textView = findViewById(R.id.movie_title);
        textView.setText(title);


        String releaseDate = intent.getStringExtra(INTENT_CONSTANTS.RELEASE_DATE);
        TextView releaseView = findViewById(R.id.release_date_view);
        releaseView.setText(releaseDate.substring(0,4));


        String synopsis = intent.getStringExtra(INTENT_CONSTANTS.SYNOPSIS);
        TextView synopsisView = findViewById(R.id.synopsis_view);
        synopsisView.setText(synopsis);


        Double rating = intent.getDoubleExtra(INTENT_CONSTANTS.RATING, 0.0);
        TextView ratingView = findViewById(R.id.rating_view);
        ratingView.setText(rating.toString()+"/10 Rating");


        String posterPath = intent.getStringExtra(INTENT_CONSTANTS.POSTER_IMAGE);
        ImageView movieThumbnail = findViewById(R.id.detail_movie_thumbnail);
        new AsyncThumbnailFetching().fetchImageAtForImageview(posterPath, movieThumbnail);
    }
}
