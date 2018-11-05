package com.example.jamesjongs.popularmovies;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MovieDBClient {


    // Constants

    private final static String TAG = "MovieDBClient";

    final static String API_KEY = "";

    final static String MOVIE_DB_BASE_URL = "https://api.themoviedb.org";
    final static String POPULAR_API_PATH = "3/movie/popular";
    final static String TOP_RATED_API_PATH = "3/movie/top_rated";


    // Variables

    private Context mContext;


    // Methods

    public MovieDBClient(Context context) {
        mContext = context;
    }


    // Create a url by on sort criteria
    public URL createURL(MovieModel.SortByCriteria sortBy) {

        String apiPath;
        if (sortBy == MovieModel.SortByCriteria.popularity) {
            apiPath = POPULAR_API_PATH;
        } else {
            apiPath = TOP_RATED_API_PATH;
        }

        Uri builtURI = Uri.parse(MOVIE_DB_BASE_URL).buildUpon()
                .appendEncodedPath(apiPath)
                .appendQueryParameter("api_key", API_KEY)
                .appendQueryParameter("language", "en_US")
                .build();

        URL url = null;
        try {
            url = new URL(builtURI.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }


    public boolean isInternetAvailable() {
        try {
            int timeoutMs = 1500;
            Socket sock = new Socket();
            SocketAddress sockaddr = new InetSocketAddress("api.themoviedb.org", 80);

            sock.connect(sockaddr, timeoutMs);
            sock.close();

            return true;
        } catch (IOException e) {
            Log.i(TAG, "isInternetAvailable: Something is wrong with the internet connection");
            e.printStackTrace();
            return false;
        }
    }

    // Makes a request to The Movie Database
    public JSONObject queryTMDBForData(URL url) {
        Log.i(TAG, "queryTMDBForData: Making request for data");
        JSONObject jsonObject = null;

        if (!isInternetAvailable()){
            return null;
        }

        try {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            jsonObject = new JSONObject(response.body().string());

        } catch (IOException e) {
            System.out.println("IoException");
            e.printStackTrace();
        } catch (JSONException e) {
            System.out.println("json formation exception");
            e.printStackTrace();
        }

        return jsonObject;
    }

}
