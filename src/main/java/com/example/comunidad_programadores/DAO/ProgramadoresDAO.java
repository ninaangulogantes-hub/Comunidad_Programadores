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

//      Devolvemos el resultado de la pregunta a la db
        return existeProgramador.hasNext();

    }

    //  Metodo que registra un programador
    public static boolean registrarProgramador(String programador, String contrasena) {
        //      Abrimos la conexion.
        ObjectContainer db = ConexionDb4o.getConexion();
        boolean existe = false;
        try {
//            Preguntamos si existe para dscartar
            if (existeProgramador(programador)) {
                return false;
            }
//          Creamos el programador
            Progamadores p = new Progamadores();
            p.setUsuario(programador);
            p.setContrasenha(contrasena);

//          Guardamos el programador
            db.store(p);
            db.commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void usuarioValido(String usuario, String contrasena) {
        //      Abrimos la conexion.
        ObjectContainer db = ConexionDb4o.getConexion();

        if(existeProgramador(usuario)){
            Progamadores p =
            ObjectSet<Progamadores> esValido = db.queryByExample(p);
        }
    }
}
