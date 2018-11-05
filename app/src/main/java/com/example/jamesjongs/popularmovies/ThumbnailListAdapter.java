package com.example.jamesjongs.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

import static com.example.jamesjongs.popularmovies.R.drawable.nice_round_chinchilla;

public class ThumbnailListAdapter extends BaseAdapter {

    // Constants

    private static final String THUMBNAILLISTADAPTER = "THUMBNAILLISTADAPTER";
    private String baseURL = "https://image.tmdb.org/t/p/w185";


    // Variables

    private Context mContext;
    private MovieModel movieModel;


    // Methods

    public ThumbnailListAdapter(Context context, MovieModel iMovieModel) {
        mContext = context;
        movieModel = iMovieModel;
        movieModel.setAdapterListener(this);
    }


    @Override
    public int getCount() {
        return movieModel.getMovieCount();
    }


    @Override
    public Object getItem(int position) {

        Log.i(THUMBNAILLISTADAPTER, "Getting movie record from model at:" + position);
        return movieModel.getMovie(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            // Inflate thumbnail layout
            convertView = LayoutInflater.from(mContext).inflate(R.layout.movie_item, parent, false);
        }
        Log.i(THUMBNAILLISTADAPTER, "replacing view at position:" + position);
        Log.i(THUMBNAILLISTADAPTER, "with image at " + ((MovieRecord) getItem(position)).posterpath);

        // Get the ImageView object
        ImageView movieImage = convertView.findViewById(R.id.movie_image);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(THUMBNAILLISTADAPTER, "onClick called on:" + v.toString());
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra(DetailActivity.INTENT_CONSTANTS.TITLE, movieModel.getMovie(position).title);
                intent.putExtra(DetailActivity.INTENT_CONSTANTS.SYNOPSIS, movieModel.getMovie(position).synopsis);
                intent.putExtra(DetailActivity.INTENT_CONSTANTS.RATING, movieModel.getMovie(position).userRating);
                intent.putExtra(DetailActivity.INTENT_CONSTANTS.RELEASE_DATE, movieModel.getMovie(position).releaseDate);
                intent.putExtra(DetailActivity.INTENT_CONSTANTS.POSTER_IMAGE, movieModel.getMovie(position).posterpath);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });

        Picasso.get()
                .load(baseURL + ((MovieRecord) getItem(position)).posterpath)
                .into(movieImage);

        Log.i(THUMBNAILLISTADAPTER, "Getting a new view at position:" + position);

        return convertView;
    }
}
