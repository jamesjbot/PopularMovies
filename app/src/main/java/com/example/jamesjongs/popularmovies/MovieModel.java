package com.example.jamesjongs.popularmovies;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.BaseAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

public class MovieModel {

    // Constants

    private static final String TAG = "MovieModel";

    public enum SortByCriteria {
        popularity, toprated
    }


    // Variables

    private ArrayList<MovieRecord> movies;

    private MovieDBClient tmdbService;

    private BaseAdapter mAdapter;

    private Context mContext;

    private SortByCriteria currentSortCriteria;


    // Methods

    public MovieModel(Context context) {

        mContext = context;

        // Internal model
        movies = new ArrayList<MovieRecord>();

        // Service that retrieves data
        tmdbService = new MovieDBClient(mContext);

        // Initialize sort criteria
        currentSortCriteria = SortByCriteria.popularity;

        // Initially populate the model
        launchInternetRequestToTMDB();
    }


    // Actually send an asynchronous request to the resource
    private void launchInternetRequestToTMDB() {

        // Launch async internet request
        AsyncTask task = new AsyncTask() {
            private JSONObject jsonData = null;

            @Override
            protected Object doInBackground(Object[] objects) {

                // Form URL
                URL url = tmdbService.createURL(currentSortCriteria);

                // Request data
                jsonData = tmdbService.queryTMDBForData(url);
                Log.i(TAG, "doInBackground: Returned from getting data from TMDB");

                if (jsonData != null) {
                    return true;
                } else {
                    return false;
                }

            }

            @Override
            protected void onPostExecute(Object o) {

                super.onPostExecute(o);

                Log.i(TAG, "onPostExecute: Now building model");
                if (jsonData != null) {
                    buildModel(jsonData);
                } else { // No data to build model
                    ((MainActivity)mContext).alertNoInternetDialog();
                }
                Log.i(TAG, "onPostExecute: Finished building model");
            }
        };

        task.execute();
    }


    // Creates and internal cached model
    private void buildModel(JSONObject jsonObject) {

        // Convert JSON into iterable array
        JSONArray resultsArray = null;
        try {
            resultsArray = jsonObject.getJSONArray("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Early return if the JSON results are empty
        if (resultsArray == null) return;

        try {

            // Clear the current model out before building the new one
            movies.clear();

            // Create all the movie records returned in the JSON results
            for (int i = 0; i < resultsArray.length(); ++i) {

                // Create components for a movie record
                JSONObject movieJSON = (JSONObject) resultsArray.get(i);
                String title = movieJSON.getString("title");
                String posterPath = movieJSON.getString("poster_path");
                Double voteAverage = movieJSON.getDouble("vote_average");
                String synopsis = movieJSON.getString("overview");
                String realeaseDate = movieJSON.getString("release_date");

                // Create the movie record from components
                movies.add(new MovieRecord(title, synopsis, voteAverage, realeaseDate, posterPath));

                Log.i(TAG, "buildModel: Created movie record for title:" + title + " at:" + posterPath);
            }

        } catch (JSONException e) {

            e.printStackTrace();
        }

        // Notify any views attached to this model that the model has changed
        notifyListener();
    }


    // Get a specific movie record from the internal cache
    public MovieRecord getMovie(int position) {

        Log.i(TAG, "getMovie: Getting movie record at position:" + position);
        return movies.get(position);
    }


    // Getting the size of the internal movie cache
    public int getMovieCount() {
        return movies.size();
    }


    // Notify the view attached that the dataset has changed.
    public void notifyListener() {

        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
    }


    // Set the adapter that needs to reformat data for the view
    public void setAdapterListener(BaseAdapter adapter) {

        mAdapter = adapter;
    }


    // Set sort criteria and refetch data
    public void setSortCriteria(SortByCriteria input) {

        currentSortCriteria = input;

        // Refetch data according to new sort criteria
        launchInternetRequestToTMDB();
    }

}
