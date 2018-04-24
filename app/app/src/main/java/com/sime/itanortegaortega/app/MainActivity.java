package com.sime.itanortegaortega.app;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private final static String DOMAIN = "http://181.62.161.249:41062/www/api/";

    private CategoriasAdapter adapter;
    private GridView Gv_Categorias;
    private ExecutorService queue = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Gv_Categorias =  (GridView)findViewById(R.id.Gv_Categorias);
        Gv_Categorias.setOnItemClickListener(this);

        cargarCategorias();
    }

    private void cargarCategorias() {
        JSONArray categoriasJsondos;

        JSONArray categoriasJson;

        Runnable thread = new Runnable() {
            @Override
            public void run() {
                String strUrl = DOMAIN + "categorias.json";
                URL url = null;
                CAFData remoteData = null;


                try {
                    url = new URL(strUrl);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                if(url != null){
                    remoteData = CAFData.dataWithContentsOfURL(url);

                    try {
                        JSONObject root = new JSONObject(remoteData.toText());
                        categoriasJson = root.getJSONArray("Categorias");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        categoriasJsondos = categoriasJson;

        queue.execute(thread);

        /*categorias.add(new Categoria(1,"Animals", "animales", "Local"));
        categorias.add(new Categoria(2, "Family", "familia", "Local"));
        categorias.add(new Categoria(3, "Clothes", "ropa", "Local"));
        categorias.add(new Categoria(4, "Profesions", "profesiones", "Local"));
        categorias.add(new Categoria(5, "The Office", "oficina", "Local"));
        categorias.add(new Categoria(6, "The Body", "cuerpo", "Local"));
        categorias.add(new Categoria(7, "The city", "ciudad", "Local"));
        categorias.add(new Categoria(8, "Foods", "alimentos", "Local"));
        categorias.add(new Categoria(9, "Colors", "colores", "Local"));
        categorias.add(new Categoria(10, "Deports", "deportes", "Local"));

        adapter = new CategoriasAdapter(this, categorias);

        Gv_Categorias.setAdapter(adapter);*/
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Categoria item = (Categoria) parent.getItemAtPosition(position);

        Intent intent = new Intent(this, PalabrasActivity.class);
        intent.putExtra("nombre_categoria", item.getNombre());
        startActivity(intent);
    }
}
