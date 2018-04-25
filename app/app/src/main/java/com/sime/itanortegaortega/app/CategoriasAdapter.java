package com.sime.itanortegaortega.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by itanortegaortega on 24/04/18.
 */

public class CategoriasAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Categoria> categorias;

    public CategoriasAdapter(Context context, ArrayList<Categoria> categorias) {
        this.context = context;
        this.categorias = categorias;
    }

    @Override
    public int getCount() {
        return categorias.size();
    }

    @Override
    public Categoria getItem(int position) {
        return categorias.get(position);
    }

    @Override
    public long getItemId(int position) {
        return categorias.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.cardview_categoria, parent, false);
        }

        TextView Cv_Categoria = (TextView) convertView.findViewById(R.id.Cv_Categoria);
        ImageView Cv_Imagen_categoria = (ImageView) convertView.findViewById(R.id.Cv_Imagen_categoria);
        TextView Txt_estado = (TextView) convertView.findViewById(R.id.Txt_estado);

        final Categoria item = getItem(position);
        Cv_Categoria.setText(item.getNombre());
        Glide.with(Cv_Imagen_categoria.getContext())
                .load(R.drawable.img)
                .into(Cv_Imagen_categoria);
        Cv_Categoria.setText(item.getNombre());

        return convertView;
    }
}
