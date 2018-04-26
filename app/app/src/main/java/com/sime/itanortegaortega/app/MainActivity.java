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

import com.bumptech.glide.util.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private final static String DOMAIN = "http://181.62.161.249:41062/www/api/";
    private static String LOCAL = "";

    private CategoriasAdapter adapter;
    private GridView Gv_Categorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LOCAL = getApplicationContext().getFilesDir().getAbsolutePath() + "/";

        Gv_Categorias =  (GridView)findViewById(R.id.Gv_Categorias);
        Gv_Categorias.setOnItemClickListener(this);

        if(existeArchivoVersion()){
            if(versionesDiferentes()){
                descargarTodo();
            }
        }else{
            descargarTodo();
        }
    }

    private void descargarTodo() {

    }

    private boolean versionesDiferentes() {
        final String urlWeb = DOMAIN + "version.json";
        final String urlLocal = LOCAL + "version.json";
        boolean res = true;
        ExecutorService queue = Executors.newSingleThreadExecutor();

        Runnable thread = new Runnable() {
            URL urlW = null;
            URL urlL = null;
            boolean iguales = true;

            @Override
            public void run() {
                try {
                    urlW = new URL(urlWeb);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                CAFData web;
                CAFData local;

                if(urlW != null && urlL !=null){
                    web = CAFData.dataWithContentsOfURL(urlW);
                    local = CAFData.dataWithContentsOfURL(urlL);

                    try {
                        JSONObject webJson = new JSONObject(web.toText());
                        JSONObject localJson = new JSONObject(local.toText());
                        if(webJson.toString().equals(localJson.toString())){
                            iguales = false;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        queue.execute(thread);
        res = iguales;
        return res;
    }

    private boolean existeArchivoVersion() {
        String urlLocal = LOCAL + "version.json";
        File fichero = new File(urlLocal);

        if (fichero.exists())
            return true;
        else
            return false;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Categoria item = (Categoria) parent.getItemAtPosition(position);

        Intent intent = new Intent(this, PalabrasActivity.class);
        intent.putExtra("nombre_categoria", item.getNombre());
        startActivity(intent);
    }
}
