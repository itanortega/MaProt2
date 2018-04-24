package com.sime.itanortegaortega.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ArrayList<Categoria> categorias = new ArrayList<Categoria>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cargarCategorias();
    }

    private void cargarCategorias() {


        categorias.add(new Categoria("Animals", "animales", "Local"));
        categorias.add(new Categoria("Family", "familia", "Local"));
        categorias.add(new Categoria("Clothes", "ropa", "Local"));
        categorias.add(new Categoria("Profesions", "profesiones", "Local"));
        categorias.add(new Categoria("The Office", "oficina", "Local"));
        categorias.add(new Categoria("The Body", "cuerpo", "Local"));
        categorias.add(new Categoria("The city", "ciudad", "Local"));
        categorias.add(new Categoria("Foods", "alimentos", "Local"));
        categorias.add(new Categoria("Colors", "colores", "Local"));
        categorias.add(new Categoria("Deports", "deportes", "Local"));

        RecyclerViewAdapterCategorias adapter = new RecyclerViewAdapterCategorias(categorias);
        RecyclerView recyclerview_categorias =  (RecyclerView)findViewById(R.id.recyclerview_categorias);
        recyclerview_categorias.setHasFixedSize(true);
        recyclerview_categorias.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview_categorias.setLayoutManager(llm);
    }
}
