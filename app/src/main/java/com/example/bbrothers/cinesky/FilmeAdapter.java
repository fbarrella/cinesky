package com.example.bbrothers.cinesky;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class FilmeAdapter extends ArrayAdapter<Filme>{

    private Context context;
    private ArrayList<Filme> elems;

    public FilmeAdapter(Context context, ArrayList<Filme> elems){
        super(context, R.layout.grid_object, elems);
        this.context = context;
        this.elems = elems;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inf =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View objView = inf.inflate(R.layout.grid_object, parent, false);

        ImageView imgFilm = (ImageView) objView.findViewById(R.id.imgFilme);
        TextView txtFilm = (TextView) objView.findViewById(R.id.txtFilme);

        loadImage(elems.get(position).getCover_url(), imgFilm);
        txtFilm.setText(elems.get(position).getTitle());

        return objView;
    }

    public void loadImage(String u, ImageView iv){
        Picasso.get()
                .load(u)
                .resize(300, 450)
                .centerCrop()
                .placeholder(R.drawable.cover_loading)
                .error(R.drawable.cover_missing)
                .into(iv);
    }
}
