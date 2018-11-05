package com.example.jamesjongs.popularmovies;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.PersistableBundle;
import android.support.annotation.RequiresPermission;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.json.JSONObject;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    // Constants

    public static final String TAG = "MainActivity";


    // Variables

    private MovieModel movieModel;


    // Methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create Model
        movieModel = new MovieModel(this);

        // Create Adapter listener to respond to changes from model and notify view
        ThumbnailListAdapter thumbnailAdapterForGridView = new ThumbnailListAdapter(getBaseContext(), movieModel);

        // Register the adapter for updates from the movieModel
        movieModel.setAdapterListener(thumbnailAdapterForGridView);

        // Create the View
        setContentView(R.layout.activity_main);

        // Link the fragment and adapter for updates
        GridViewFragment fragment = (GridViewFragment) getSupportFragmentManager().findFragmentById(R.id.thumbnail_fragment);
        fragment.setAdapter(thumbnailAdapterForGridView);

    }


    public void alertNoInternetDialog() {
        AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setTitle("Internet Connection");
        dialog.setMessage("Internet Connection/Data Unavailable \nPlease try this app again later.");
        dialog.setIcon(android.R.drawable.ic_dialog_alert);
            dialog.setButton(Dialog.BUTTON_POSITIVE,"OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }
            });
        dialog.show();
    }


    // Creates our sorting options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    // Captures the sorting options
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Tell the model how it should sort by now
        if (item.getTitle() == getString(R.string.sort_by_toprating)) {

            movieModel.setSortCriteria(MovieModel.SortByCriteria.toprated);

        } else if (item.getTitle() == getString(R.string.sort_by_popularity)) {

            movieModel.setSortCriteria(MovieModel.SortByCriteria.popularity);

        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i(TAG, "onConfigurationChanged: "+ newConfig.orientation);
    }
}
