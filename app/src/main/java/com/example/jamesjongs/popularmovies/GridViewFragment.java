package com.example.jamesjongs.popularmovies;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class GridViewFragment extends Fragment {

    // Constants

    private static final String TAG = "GridViewFragment";

    private GridView gridView;


    // Methods

    public GridViewFragment() {

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_grid_view, container, false);

        // Get a reference to the GridView
        gridView = rootView.findViewById(R.id.grid_view_of_thumbnails);

        return rootView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated: GridView fragment has been created");
    }


    // Creates a connection to receive updates from the model
    public void setAdapter(ThumbnailListAdapter adapter) {

        gridView.setAdapter(adapter);
    }
}
