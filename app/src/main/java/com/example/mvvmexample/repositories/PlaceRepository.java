package com.example.mvvmexample.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmexample.models.Place;

import java.util.ArrayList;

// Classe Singleton: garante que apenas um objeto dessa classe será criado, evitando várias chamadas a um web service, api ou banco de dados
public class PlaceRepository {
    private static PlaceRepository instance;
    private ArrayList<Place> dataSet = new ArrayList<>();

    public static PlaceRepository getInstance(){
        if(instance == null){
            instance = new PlaceRepository();
        }
        return instance;
    }

    public MutableLiveData<ArrayList<Place>> getPlaces(){
        // Aqui poderia ser feita uma consulta a um web service, api ou banco de dados para obter os dados
        setPlaces();

        MutableLiveData<ArrayList<Place>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }

    private void setPlaces(){
        dataSet.add(
                new Place("https://lp-cms-production.imgix.net/image_browser/Havasu%20Falls.jpg",
                        "Havasu Falls")
        );
        dataSet.add(
                new Place("https://i.redd.it/tpsnoz5bzo501.jpg",
                        "Trondheim")
        );
        dataSet.add(
                new Place("https://i.redd.it/qn7f9oqu7o501.jpg",
                        "Portugal")
        );
        dataSet.add(
                new Place("https://i.redd.it/j6myfqglup501.jpg",
                        "Rocky Mountain National Park")
        );
        dataSet.add(
                new Place("https://istoe.com.br/wp-content/uploads/sites/14/2019/08/toquio-divulgacao-governojapones.jpg",
                        "Tokio")
        );
        dataSet.add(
                new Place("https://i.redd.it/k98uzl68eh501.jpg",
                        "Mahahual")
        );
        dataSet.add(
                new Place("https://hips.hearstapps.com/pop.h-cdn.co/assets/16/51/1482505876-gettyimages-487741029.jpg",
                        "Frozen Lake")
        );
        dataSet.add(
                new Place("https://i.redd.it/obx4zydshg601.jpg",
                        "Australia")
        );
    }
}
