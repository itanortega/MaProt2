package com.sime.itanortegaortega.app;

import java.io.File;

/**
 * Created by itanortegaortega on 25/04/18.
 */

public class Utilidades {
    public static boolean existeArchivo(String ruta){
        File fichero = new File(ruta);
        if (fichero.exists())
            return true;
        else
            return false;
    }

}
