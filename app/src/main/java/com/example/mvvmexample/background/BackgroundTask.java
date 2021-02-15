package com.example.mvvmexample.background;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmexample.models.Place;

import java.util.ArrayList;

public class BackgroundTask extends AsyncTask<Place, String, ArrayList<Place>> {

    private ProgressDialog progressDialog;
    private Context context;
    private ArrayList<Place> currentPlaces;
    private MutableLiveData<ArrayList<Place>> places;

    public BackgroundTask(Context context, ArrayList<Place> currentPlaces, MutableLiveData<ArrayList<Place>> places){
        this.context = context;
        this.currentPlaces = currentPlaces;
        this.places = places;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Carregando imagem...");
        progressDialog.show();
    }

    @Override
    protected ArrayList<Place> doInBackground(Place... params) {
        publishProgress("Carregando imagem...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        currentPlaces.add(params[0]);
        publishProgress("Imagem Carregada!");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return currentPlaces;
    }

    @Override
    protected void onProgressUpdate(String... params) {
        progressDialog.setMessage(params[0]);
    }

    @Override
    protected void onPostExecute(ArrayList<Place> params) {
        places.postValue(params);
        progressDialog.dismiss();
    }

}
