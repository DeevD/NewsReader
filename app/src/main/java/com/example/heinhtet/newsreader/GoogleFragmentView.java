package com.example.heinhtet.newsreader;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by heinhtet on 2/13/17.
 */

public class GoogleFragmentView extends android.app.Fragment implements LoaderManager.LoaderCallbacks<List<Google>> {

    public static final String GOOGLE_URL = "https://newsapi.org/v1/articles?source=google-news&sortBy=top&apiKey=d6a6da0807b542ffbb7357837dee84f1";
    public static final int Google_LoaderID = 1;
    RecyclerView googleRecyclerView ;
    GoogleCustomAdapter googleCustomAdapter;


    public static final String LOG = GoogleFragmentView.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View googleView = inflater.inflate(R.layout.google_fragment_view,container,false);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        ConnectivityManager connectivityManager = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo!=null&&networkInfo.isConnected()){
            LoaderManager manager = getLoaderManager();
            manager.initLoader(Google_LoaderID,null,this);
        }
        googleRecyclerView = (RecyclerView)googleView.findViewById(R.id.recycler_list);


        return googleView;
    }


    @Override
    public Loader<List<Google>> onCreateLoader(int i, Bundle bundle) {
        Log.i(LOG,"onCreateLoader");
        return new GoogleLoader(getActivity(),GOOGLE_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Google>> loader, final List<Google> googles) {
        Log.i(LOG,"onLoadFinished");
        View progressbar = getActivity().findViewById(R.id.prograssbar);
        progressbar.setVisibility(View.GONE);


        //googleCustomAdapter = new GoogleCustomAdapter(getActivity(), (ArrayList<Google>) googles);
//        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
//        googleRecyclerView.setLayoutManager(manager);

        googleCustomAdapter = new GoogleCustomAdapter(getActivity(), googles);
        googleRecyclerView.setAdapter(googleCustomAdapter);
        googleRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));





//        if (googles!=null&&!googles.isEmpty()){
//            googles.add(0, (Google) googles);
//            googleRecyclerView.setAdapter(googleCustomAdapter);
//           if (googleCustomAdapter.getItemCount()>1){
//               googles.add((Google)googles);
//               googleCustomAdapter.notifyDataSetChanged();
//
//           }
//
//
//        }

    }

    @Override
    public void onLoaderReset(Loader<List<Google>> loader) {

    }

}
