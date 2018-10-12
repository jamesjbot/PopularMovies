package com.example.jamesjongs.popularmovies;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

import static com.example.jamesjongs.popularmovies.R.drawable.nice_round_chinchilla;

public class ThumbnailListAdapter extends BaseAdapter {

    private Context mContext;
    private List<Integer> mImageIds;

    public ThumbnailListAdapter(Context context, List<Integer> imageIds) {
        mContext = context;
        mImageIds = imageIds;
    }

    @Override
    public int getCount() {
        return 30;
        //return mImageIds.size();
    }

    @Override
    public Object getItem(int position) {
        System.out.println("Error get item called");
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = new ImageView(mContext);
            convertView.setBackgroundColor(Color.BLUE);
            ((ImageView) convertView).setImageResource (R.drawable.nice_round_chinchilla);
        }
        return convertView;
    }
}
