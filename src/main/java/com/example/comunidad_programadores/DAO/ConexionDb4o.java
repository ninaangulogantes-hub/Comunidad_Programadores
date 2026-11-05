package com.example.comunidad_programadores.DAO;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class ConexionDb4o {static final String DB_FILE = "comunidadProgramadores.db4o";

    public static ObjectContainer getConexion(){
        return Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), DB_FILE);
    }
}