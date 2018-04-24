package com.sime.itanortegaortega.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PalabrasActivity extends AppCompatActivity {

    TextView Txt_Categoria;
    String nombre_categoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palabras);
        nombre_categoria = getIntent().getStringExtra("nombre_categoria").toString();
        Txt_Categoria = (TextView) findViewById(R.id.Txt_Categoria);
        Txt_Categoria.setText(nombre_categoria);
    }
}
