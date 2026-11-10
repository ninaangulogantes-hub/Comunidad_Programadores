package com.example.comunidad_programadores.DAO;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class ConexionDb4o {
    private static final String DB_FILE = "comunidadProgramadores.db4o";
    private static ObjectContainer db = null;

    // Obtiene la instancia única de la base de datos
    public static synchronized ObjectContainer getConexion() {
        if (db == null || db.ext().isClosed()) {
            db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), DB_FILE);
        }
        return db;
    }

//  Metodo que cierra la sesion
    public static synchronized void cerrarConexion() {
        if (db != null && !db.ext().isClosed()) {
            db.close();
            db = null;
            System.out.println("Base de datos db4o cerrada correctamente.");
        }
    }
}
