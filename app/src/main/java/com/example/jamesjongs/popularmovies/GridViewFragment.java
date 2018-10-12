package com.example.jamesjongs.popularmovies;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class GridViewFragment extends Fragment {

    public GridViewFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_grid_view, container, false);

        // Get a reference to the gridview
        GridView gridView = (GridView) rootView.findViewById(R.id.grid_view_of_thumbnails);

        //
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);

        // Create the adapter and give it the resources
        ThumbnailListAdapter thumbnailAdapter = new ThumbnailListAdapter(getContext(), list);

        // Set the adapter on the gridview
        gridView.setAdapter(thumbnailAdapter);

        return rootView;
    }
}
