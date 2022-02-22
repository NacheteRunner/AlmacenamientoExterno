package com.example.almacenamientoexterno;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class UsuariosFILE {

    private  Context context;

    private static String FICHERO = "usuarios.txt";
    private final static int ITEMS_ARRAY = 6;

    public UsuariosFILE(Context context) {
        this.context = context;
    }

    public  void insertarUsuario(Usuario usuario) {

        try {
            FileOutputStream f = context.openFileOutput(FICHERO, Context.MODE_APPEND);
            String linea = usuario.getId_usuario() + ";"
                    + usuario.getNombre_usuario() + ";"
                    + usuario.getApellidos_usuario() + ";"
                    + usuario.getEmail() + ";"
                    + usuario.getEdad() + ";"
                    + usuario.getTelefono() + "\n";
            f.write(linea.getBytes());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  ArrayList<Usuario> leerUsuarios(String tipoFichero) {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        FileInputStream fis = null;
        InputStreamReader isReader = null;
        String[] palabras = new String[ITEMS_ARRAY];
        try {
            if (tipoFichero.equals("INTERNO")){
                fis = context.openFileInput(FICHERO);
                isReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
            }else{ // Fichero de recursos
                InputStream is = context.getResources().openRawResource(R.raw.usuarios);
                isReader= new InputStreamReader(is, StandardCharsets.UTF_8);
            }

            BufferedReader br = new BufferedReader(isReader);
            String linea = br.readLine();
            while (linea != null) {
                palabras = linea.split(";");
                Usuario usuario = new Usuario();
                usuario.setId_usuario(Integer.valueOf(palabras[0]));
                usuario.setNombre_usuario(palabras[1]);
                usuario.setApellidos_usuario(palabras[2]);
                usuario.setEmail(palabras[3]);
                usuario.setEdad(Integer.valueOf(palabras[4]));
                usuario.setTelefono(Integer.valueOf(palabras[5]));
                usuarios.add(usuario);
                linea = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    return usuarios;
    }


}
