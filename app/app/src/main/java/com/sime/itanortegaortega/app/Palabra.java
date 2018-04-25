package com.sime.itanortegaortega.app;

/**
 * Created by itanortegaortega on 25/04/18.
 */

public class Palabra {
    private int i;
    private String imagen;
    private String nombre;

    public Palabra(){
        
    }

    public Palabra(int i, String imagen, String nombre) {
        this.i = i;
        this.imagen = imagen;
        this.nombre = nombre;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
