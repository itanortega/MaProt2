package com.sime.itanortegaortega.app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by itanortegaortega on 25/04/18.
 */

public class PalabrasAdapter  extends RecyclerView.Adapter <PalabrasAdapter.PalabraViewHolder>{

    public List<Palabra> palabras;
    public Context context;

    public PalabrasAdapter(List<Palabra> palabras, Context context) {
        this.palabras = palabras;
        this.context = context;
    }

    @Override
    public PalabraViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View cardview = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_palabra, parent, false);
        PalabrasAdapter.PalabraViewHolder palabraViewHolder = new PalabrasAdapter.PalabraViewHolder(cardview);
        return palabraViewHolder;
    }

    @Override
    public void onBindViewHolder(PalabraViewHolder holder, int position) {
        holder.Cv_Palabra.setText(palabras.get(position).getNombre());
    }

    @Override
    public int getItemCount() {
        return palabras.size();
    }

    public class PalabraViewHolder extends RecyclerView.ViewHolder{
        TextView Cv_Palabra;
        public PalabraViewHolder(View item) {
            super(item);
            Cv_Palabra = (TextView) item.findViewById(R.id.Cv_Palabra);
        }
    }

}
