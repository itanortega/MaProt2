package com.sime.itanortegaortega.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PalabrasActivity extends AppCompatActivity {
    private final static String DOMAIN = "http://181.62.161.249:41062/www/api/";

    String nombre_categoria;
    RecyclerView Rv_Palabras;
    LinearLayoutManager linearLayoutManager;
    private PalabrasAdapter palabrasAdapter;
    private ExecutorService queue = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palabras);
        nombre_categoria = getIntent().getStringExtra("nombre_categoria").toString();

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);

        Rv_Palabras = (RecyclerView) findViewById(R.id.Rv_Palabras);
        Rv_Palabras.setLayoutManager(linearLayoutManager);

        cargarPalabras();
    }

    public void cargarPalabras(){
        Runnable thread = new Runnable() {
            @Override
            public void run() {
                String strUrl = DOMAIN + nombre_categoria + ".json";

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

                        JSONArray palJson = root.getJSONArray("data");

                        final JSONArray palabrasJson = palJson;

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ArrayList<Palabra> palabras = new ArrayList<>();
                                Palabra p;
                                JSONObject palabraJson;

                                for(int i=0; i<palabrasJson.length(); i++){
                                    try {
                                        palabraJson = palabrasJson.getJSONObject(i);
                                        p = new Palabra(i, "", palabraJson.getString("nombre").toString());
                                        palabras.add(p);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                palabrasAdapter = new PalabrasAdapter(palabras, getApplicationContext());
                                Rv_Palabras.setAdapter(palabrasAdapter);
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
}
