package com.example.comunidad_programadores.DAO;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.example.comunidad_programadores.entidades.Progamadores;

public class ProgramadoresDAO {

//  Funcion que devuelve los programadores filtrados
    public static Progamadores filtrarProgramadores(String lenguaje){

        Progamadores p = new Progamadores();
        p.setLenguaje(lenguaje);

//      Recogemos los programadores de la BD.
        ObjectSet<Progamadores> existeProgramador = db.queryByExample(p);

        return p;
    }
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
//  Funcion que registra un programador
    public static boolean registrarProgramador(String programador, String contrasena, String genero, String lenguaje) {

//      Abrimos la conexion.
        ObjectContainer db = ConexionDb4o.getConexion();

        try {
//            Preguntamos si existe para dscartar
            if (existeProgramador(programador)) {
                System.out.println("existe");
                return false;
            }
//          Creamos el programador
            Progamadores p = new Progamadores();
            p.setUsuario(programador);
            p.setContrasenha(contrasena);
            p.setGenero(genero);
            p.setLenguaje(lenguaje);

//          Guardamos el programador
            db.store(p);
            db.commit();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
//  Funcion que comprueba si un usuario coincide con la contrasena introducida
    public static boolean usuarioValido(String usuario, String contrasena) {

        //      Abrimos la conexion.
        ObjectContainer db = ConexionDb4o.getConexion();

//      Creamos un usuario y le ponemos las variables mandadas desde el main.
        Progamadores p = new Progamadores();
        p.setUsuario(usuario);
        p.setContrasenha(contrasena);

//      Le preguntamos a la base de datos por ello.
        ObjectSet<Progamadores> esValidoP = db.queryByExample(p);

        return esValidoP.hasNext();

    }
}
