package com.example.comunidad_programadores.DAO;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.example.comunidad_programadores.entidades.Progamadores;

import java.util.ArrayList;
import java.util.List;

public class ProgramadoresDAO {

    //  Funcion que devuelve los programadores filtrados
    public static ArrayList<Progamadores> filtrarProgramadores(String lenguaje) {

//      Abrimos la conexion
        ObjectContainer db = ConexionDb4o.getConexion();

//      Creamos el arraylist para guardar los programadores
        ArrayList<Progamadores> programadores = new ArrayList<>();

//      Le asignamos el lenguaje a un objeto creado.
        Progamadores p = new Progamadores();
        p.setLenguaje(lenguaje);

//      Si el lenguaje es todos guardamos todos los programadores si no filtramos por lenguaje.
        if (lenguaje.equalsIgnoreCase("todos")) {
//            ObjectSet<Progamadores> esProgramador = db.query(Progamadores.class);
//            while (esProgramador.hasNext()) {
//                programadores.add(esProgramador.next());
//            }
            programadores = new ArrayList<>(db.query(Progamadores.class));
        } else {
            // Creamos el objeto modelo solo cuando vamos a filtrar
            Progamadores filtro = new Progamadores();
            filtro.setLenguaje(lenguaje);

            ObjectSet<Progamadores> resultados = db.queryByExample(filtro);

            while (resultados.hasNext()) {
                programadores.add(resultados.next());
            }
        }
//      Devolvemos el arraylist
        return programadores;
    }

    //  Funcion que comprueba si existe.
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

    //  Funcion que registra un programador.
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

    //  Funcion que comprueba si un usuario coincide con la contrasena introducida.
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

    public static Progamadores mostrarUsuario(String usuario) {

//      Abrimos la conexion.
        ObjectContainer db = ConexionDb4o.getConexion();

//      Creamos el programador y le asignamos el usuario
        Progamadores p = new Progamadores();
        p.setUsuario(usuario);

//      Buscamos al user y lo devolvemos
        ObjectSet<Progamadores> mostrarUsuario = db.queryByExample(p);
        Progamadores user = null;

        if (mostrarUsuario.hasNext()) {
            user = mostrarUsuario.next();
        }
        return user;
    }

    public static void actualizarProgramador(Progamadores p) {
        //      Abrimos la conexion.
        ObjectContainer db = ConexionDb4o.getConexion();

        db.store(p);
        db.commit();
    }
}
