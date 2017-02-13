package com.example.heinhtet.newsreader;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heinhtet on 2/13/17.
 */
public class GoogleLoader extends AsyncTaskLoader<List<Google>> {
    String mUrl;

    public GoogleLoader(Context activity, String googleUrl) {
        super(activity);
        mUrl = googleUrl;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }


    @Override
    public List<Google> loadInBackground() {
        List<Google> getJSON = new ArrayList<>();
        getJSON = GetDataFromJSON.list(mUrl);
        return getJSON;
    }
}
