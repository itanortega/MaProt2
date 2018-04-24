package com.sime.itanortegaortega.app;

import android.app.Activity;
import android.graphics.drawable.Drawable;

/**
 * Created by itanortegaortega on 23/04/18.
 */

public class Categoria {
    private int id;
    private String nombre;
    private String imagen;
    private String estado;
    private Activity act = new Activity();

    public Categoria(){

    }

    public Categoria(String nombre, String imagen, String estado) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Drawable getImagen() {
        /*Drawable img = null;
        switch (nombre) {
            case "animales": img = act.getResources().getDrawable(R.drawable.animales); break;
            case "familia": img = act.getResources().getDrawable(R.drawable.animales); break;
            case "ropa": img = act.getResources().getDrawable(R.drawable.animales); break;
            case "profesiones": img = act.getResources().getDrawable(R.drawable.animales); break;
            case "la oficina": img = act.getResources().getDrawable(R.drawable.animales); break;
            case "cuerpo": img = act.getResources().getDrawable(R.drawable.animales); break;
            case "ciudad": img = act.getResources().getDrawable(R.drawable.animales); break;
            case "alimentos": img = act.getResources().getDrawable(R.drawable.animales); break;
            case "colores": img = act.getResources().getDrawable(R.drawable.animales); break;
            case "deportes": img = act.getResources().getDrawable(R.drawable.animales); break;
            case "default": img = act.getResources().getDrawable(R.drawable.animales); break;
        }
        return img;*/
        return act.getResources().getDrawable(R.drawable.animales);
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
