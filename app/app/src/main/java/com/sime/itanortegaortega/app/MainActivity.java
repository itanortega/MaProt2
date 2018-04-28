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

        Log.d("debugapp", "actividad main");
        /*LOCAL = getApplicationContext().getFilesDir().getAbsolutePath() + "/";

        Gv_Categorias =  (GridView)findViewById(R.id.Gv_Categorias);
        Gv_Categorias.setOnItemClickListener(this);

        if(existeArchivoVersion()){
            Log.d("debugapp", "existe el archivo");
            if(versionesDiferentes()){
                Log.d("debugapp", "versiones diferentes del archivo");
                //descargarTodo();
            }
        }else{
            Log.d("debugapp", "se debe descargar todo");
            descargarTodo();
        }
        
        cargarCategorias();*/
    }

    private void cargarCategorias() {
    }

    private void descargarTodo() {
        ExecutorService queue = Executors.newSingleThreadExecutor();

        Runnable thread = new Runnable() {
            URL urlV = null;
            URL urlP = null;
            boolean iguales = true;

            @Override
            public void run() {
                try {
                    urlV = new URL(DOMAIN + "version.json");
                    urlP = new URL(DOMAIN + "words.json");

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                CAFData versionData;
                CAFData wordsData;

                if(urlV != null && urlP !=null){
                    versionData = CAFData.dataWithContentsOfURL(urlV);
                    wordsData = CAFData.dataWithContentsOfURL(urlP);

                    try {
                        JSONObject jsonVersion = new JSONObject(versionData.toText());
                        JSONObject jsonWordsData = new JSONObject(wordsData.toText());

                        versionData.writeToFile(LOCAL + "version.json", true);
                        wordsData.writeToFile(LOCAL + "words.json", true);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        queue.execute(thread);
        Log.d("debugapp", "descargar todo");
    }

    private boolean versionesDiferentes() {
        final String urlWeb = DOMAIN + "version.json";
        final String urlLocal = LOCAL + "version.json";
        final boolean[] res = {true};
        ExecutorService queue = Executors.newSingleThreadExecutor();

        Runnable thread = new Runnable() {
            URL urlW = null;
            URL urlL = null;
            boolean iguales = true;

            @Override
            public void run() {
                try {
                    urlW = new URL(urlWeb);
                    urlL = new URL(urlLocal);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                Log.d("debugapp", String.valueOf(urlW));
                Log.d("debugapp", String.valueOf(urlL));
                CAFData web;
                CAFData local;

                if(urlW != null && urlL !=null){
                    web = CAFData.dataWithContentsOfURL(urlW);
                    local = CAFData.dataWithContentsOfURL(urlL);

                    try {
                        JSONObject webJson = new JSONObject(web.toText());
                        JSONObject localJson = new JSONObject(local.toText());
                        Log.d("debugapp", webJson.toString());
                        Log.d("debugapp", localJson.toString());
                        if(webJson.toString().equals(localJson.toString())){
                            iguales = false;
                            Log.d("debugapp", String.valueOf(iguales));
                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                res[0] = iguales;
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        queue.execute(thread);
        return res[0];
    }

    private boolean existeArchivoVersion() {
        final String urlLocal = LOCAL + "version.json";
        final boolean[] res = {false};
        ExecutorService queue = Executors.newSingleThreadExecutor();

        Runnable thread = new Runnable() {
            URL urlW = null;
            URL urlL = null;
            boolean existe = false;

            @Override
            public void run() {
                try {
                    urlL = new URL(urlLocal);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                if (urlL != null) {
                    existe = true;
                }

                res[0] = existe;
            }
        };

        queue.execute(thread);
        return res[0];
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Categoria item = (Categoria) parent.getItemAtPosition(position);

        Intent intent = new Intent(this, PalabrasActivity.class);
        intent.putExtra("nombre_categoria", item.getNombre());
        startActivity(intent);
    }
}
