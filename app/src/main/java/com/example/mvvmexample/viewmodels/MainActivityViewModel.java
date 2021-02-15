package com.example.mvvmexample.viewmodels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmexample.background.BackgroundTask;
import com.example.mvvmexample.models.Place;
import com.example.mvvmexample.repositories.PlaceRepository;

import java.util.ArrayList;

public class MainActivityViewModel extends ViewModel {
    // Objeto do tipo MutableLiveData que contém uma lista de Place
    private MutableLiveData<ArrayList<Place>> places;
    private PlaceRepository repo;

    public void init(){
        // Se já pegamos os dados, não faz nada
        if(places != null){
            return;
        }
        // Inicializa o objeto da classe repository
        repo = PlaceRepository.getInstance();
        places = repo.getPlaces();
    }

    public void addNewValue(Place newPlace, Context context){
        ArrayList<Place> currentPlaces = places.getValue();

        BackgroundTask backgroundTask = new BackgroundTask(context, currentPlaces, places);
        backgroundTask.execute(newPlace);
    }

    public LiveData<ArrayList<Place>> getPlaces(){
        return places;
    }
}
