package com.example.mvvmexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.mvvmexample.adapters.RecyclerAdapter;
import com.example.mvvmexample.models.Place;
import com.example.mvvmexample.viewmodels.MainActivityViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton fab;

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerAdapter adapter;
    private ArrayList<Place> places = new ArrayList<>();

    private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setFloatingActionButton();

        setViewModel();

        setRecyclerView();

        listenersButtons(this);
    }

    private void setRecyclerView(){
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerAdapter(this, mainActivityViewModel.getPlaces().getValue());
        recyclerView.setAdapter(adapter);
    }

    private void setFloatingActionButton(){
        fab = findViewById(R.id.floatingActionButton);
    }

    private void setViewModel(){
        // .of(this) => cria um ViewModelProvider, que retém um ViewModel enquanto a Activity está viva
        // .get() => o ViewModelProvider criado no of(this), retorna um ViewModel (MainActivityViewModel) para essa Activity
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        // Pega os dados para exibi-los
        mainActivityViewModel.init();

        // Aqui é verificado quando há mudanças na ViewModel
        // Ou seja, o método getPlaces() retorna o LiveData da ViewModel, que contém uma Lista de Place
        // E o método onChanged() é chamado quando o LiveData for alterado
        mainActivityViewModel.getPlaces().observe(this, new Observer<ArrayList<Place>>() {
            @Override
            public void onChanged(ArrayList<Place> places) {
                // Quando o LiveData for alterado, avisa o adapter, pois o LiveData é a fonte de dados para o adapter da RecyclerView
                adapter.notifyDataSetChanged();
            }
        });


    }

    private void listenersButtons(Context context){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mainActivityViewModel.addNewValue(new Place("https://i.imgur.com/ZcLLrkY.jpg", "Washington"), context);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}