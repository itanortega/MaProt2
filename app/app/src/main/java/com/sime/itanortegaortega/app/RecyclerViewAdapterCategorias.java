package com.sime.itanortegaortega.app;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by itanortegaortega on 23/04/18.
 */

public class RecyclerViewAdapterCategorias  extends RecyclerView.Adapter<RecyclerViewAdapterCategorias.MyViewHolderCategorias> {

    public ArrayList<Categoria> myValues;
    public RecyclerViewAdapterCategorias (ArrayList<Categoria> myValues){
        this.myValues= myValues;
    }

    @Override
    public RecyclerViewAdapterCategorias.MyViewHolderCategorias onCreateViewHolder(ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_categoria, parent, false);
        return new MyViewHolderCategorias(listItem);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapterCategorias.MyViewHolderCategorias holder, int position) {
        Categoria cat = new Categoria();
        cat = (Categoria) myValues.get(position);

        holder.Cv_Categoria.setText(cat.getNombre());
        //holder.Cv_Imagen_categoria.setImageDrawable(getResources().getDrawable(R.drawable.animales));
        holder.Txt_estado.setText(cat.getEstado());
    }

    @Override
    public int getItemCount() {
        return myValues.size();
    }

    public static class MyViewHolderCategorias extends RecyclerView.ViewHolder {
        private TextView Cv_Categoria;
        private ImageView Cv_Imagen_categoria;
        private TextView Txt_estado;

        public MyViewHolderCategorias(View itemView) {
            super(itemView);
            Cv_Categoria = (TextView)itemView.findViewById(R.id.Cv_Categoria);
            Cv_Imagen_categoria = (ImageView)itemView.findViewById(R.id.Cv_Imagen_categoria);
            Txt_estado = (TextView)itemView.findViewById(R.id.Txt_estado);
        }
    }
}