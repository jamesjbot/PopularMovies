package com.example.jamesjongs.popularmovies;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;


public class AsyncThumbnailFetching {


    // Constants

    private final String baseURL = "https://image.tmdb.org/t/p/w185";


    // Methods

    public AsyncThumbnailFetching() {
    }


    // Uses Picasso library to fetch an image and place it in the specified ImageView
    public void fetchImageAtForImageview(String posterPath, ImageView imageView) {
        if (imageView == null) {
            throw new RuntimeException("The passed imageView is null?");
        }
        Picasso.get()
                .load(baseURL+posterPath)
                .into(imageView);
    }


    // Create a URL from just a string location provided by TMDB
    private URL createURLFrom(String imageLocation) {
        URL url = null;
        try {
            url = new URL(baseURL+imageLocation);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
}
