package com.example.almacenamientoexterno;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class UsuariosBD extends SQLiteOpenHelper {

    public UsuariosBD(Context context){
        super(context, "bbdd_usuarios", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sSQL = " CREATE TABLE Usuarios ("+
                    "id_usuario INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "nombre_usuario TEXT,"+
                "apellidos_usuario TEXT,"+
                "email TEXT,"+
                "edad INTEGER,"+
                "telefono INTEGER)";
        Log.d("miAPP", "sSQL="+sSQL);
        db.execSQL(sSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertarUsuario(Usuario usuario){
        SQLiteDatabase db = getWritableDatabase();
        String sSQL = "INSERT INTO Usuarios VALUES ("+
                "null,'"+usuario.getNombre_usuario()+
                "','"+usuario.getApellidos_usuario()+
                "','"+usuario.getEmail()+
                "',"+usuario.getEdad()+
                ","+usuario.getTelefono()+");";
        Log.d("miAPP", "sSQL="+sSQL);
        db.execSQL(sSQL);
        db.close();
    }

    public ArrayList<Usuario> leerUsuarios(){
        ArrayList<Usuario> usuarios = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();  // Para leer de la base de datos
        String sSQL = "SELECT * FROM Usuarios;";
        // Voy a declarar un cursor para moverme por los resultados de la consulta
        Cursor cursor = db.rawQuery(sSQL,null);
        while (cursor.moveToNext()){
            Usuario usuario = new Usuario();
            usuario.setId_usuario(cursor.getInt(0));
            usuario.setNombre_usuario(cursor.getString(1));
            usuario.setApellidos_usuario(cursor.getString(2));
            usuario.setEmail(cursor.getString(3));
            usuario.setEdad(cursor.getInt(4));
            usuario.setTelefono(cursor.getInt(5));
            usuarios.add(usuario);
        }
        cursor.close();
        db.close();
        return usuarios;
    }
}
