package com.example.jamesjongs.popularmovies;

import android.net.Uri;

import java.net.MalformedURLException;
import java.net.URL;

public class MovieDBClient {

    final static String MOVIE_DB_BASE_URL = "http://api.themoviedb.org";
    final static String API_PATH = "3/discover/movie";
    final static String API_KEY = "4a6f4b83c04e81b9c5814879c6646500";

    public void createURL() {
        Uri builtURI = Uri.parse(MOVIE_DB_BASE_URL).buildUpon()
                .appendEncodedPath(API_PATH)
                .appendQueryParameter("api_key", API_KEY)
                .appendQueryParameter("language", "en_US")
                .appendQueryParameter("sort_by", "popularity.desc")
                .appendQueryParameter("include_adult", "false")
                .appendQueryParameter("include_video","false")
                .build();

        URL url = null;
        try {
            url = new URL(builtURI.toString());
            System.out.println(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

}
