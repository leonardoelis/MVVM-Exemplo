package com.example.mvvmexample.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mvvmexample.R;
import com.example.mvvmexample.models.Place;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Place> places;

    public RecyclerAdapter(Context context, ArrayList<Place> places){
        this.context = context;
        this.places = places;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Seta o nome do local
        holder.imageName.setText(places.get(position).getImageName());

        // Seta um Drawable padrão se o carregamento da imagem falhar
        RequestOptions defaultOptions = new RequestOptions().error(R.drawable.ic_launcher_background);
        // Carrega a imagem que está no arraylist para dentro da ImageView
        Glide.with(context).setDefaultRequestOptions(defaultOptions).load(places.get(position).getImageURL()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView imageName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            imageName = itemView.findViewById(R.id.imageName);
        }
    }
}
