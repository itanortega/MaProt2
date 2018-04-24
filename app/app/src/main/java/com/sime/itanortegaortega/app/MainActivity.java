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
                        JSONArray catJson = root.getJSONArray("Categorias");

                        final JSONArray categoriasJson = catJson;

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ArrayList<Categoria> categorias = new ArrayList<>();
                                Categoria c;
                                JSONObject categoriaJson;

                                for(int i=0; i<categoriasJson.length(); i++){
                                    try {
                                        categoriaJson = categoriasJson.getJSONObject(i);
                                        Log.d("debugapp", categoriaJson.getString("nombre"));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                    c = new Categoria(1, "itan", "", "");
                                    categorias.add(c);
                                }

                                adapter = new CategoriasAdapter(MainActivity.this, categorias);
                                Gv_Categorias.setAdapter(adapter);
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        queue.execute(thread);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Categoria item = (Categoria) parent.getItemAtPosition(position);

        Intent intent = new Intent(this, PalabrasActivity.class);
        intent.putExtra("nombre_categoria", item.getNombre());
        startActivity(intent);
    }
}
