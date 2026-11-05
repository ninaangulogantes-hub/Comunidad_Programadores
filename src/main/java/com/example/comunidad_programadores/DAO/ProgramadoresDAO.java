package com.example.comunidad_programadores.DAO;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.example.comunidad_programadores.entidades.Progamadores;

public class ProgramadoresDAO {


    //  Funcion que comprueba si existe .
    public static boolean existeProgramador(String usuario) {

//      Abrimos la conexion
        ObjectContainer db = ConexionDb4o.getConexion();

//      Creamos el usuario
        Progamadores p = new Progamadores();
        p.setUsuario(usuario);

//      Recogemos los programadores de la BD.
        ObjectSet<Progamadores> existeProgramador = db.queryByExample(p);
        boolean existe = false;

        if (existeProgramador.hasNext()) {
            existe = true;
        }
//        Cerrramos la conexion
        db.close();

//        Devolvemos el resultado
        return existe;

    }

    //  Metodo que registra un programador
    public static boolean registrarProgramador(String programador, String contrasena) {
        //      Abrimos la conexion.
        ObjectContainer db = ConexionDb4o.getConexion();
        try {

//          Creamos el programador
            Progamadores p = new Progamadores();

//          Comprobamos si existe y si es asi lo regsitramos.
            if (existeProgramador(programador)){
                p.setUsuario(programador);
                p.setContrasenha(contrasena);
                return false;
            }
//          Guardamos el programador
            db.store(programador);
            db.commit();

            return true;
//      Cuando termine cerramos la base de datos.
        } finally {
            db.close();
        }
    }

}
