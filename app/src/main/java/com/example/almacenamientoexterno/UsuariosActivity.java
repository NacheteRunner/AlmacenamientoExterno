package com.example.almacenamientoexterno;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.ArrayList;
// Actividad para probar la BBDD SQLite con un Recicler
public class UsuariosActivity extends AppCompatActivity {

    private RecyclerView miRecicler;
    private UsuariosFILE uFILE;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    private UsuariosBD bd;
    private ArrayList<Usuario> usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);

        //bd = new UsuariosBD(this);
        usuarios = new ArrayList<>();
        //usuarios = bd.leerUsuarios();
        // Voy a ver de donde tomo los datos si de bbdd o de fichero
        //Intent intent = getIntent();
        //String tipoAlmacen = intent.getStringExtra("ALMACENAMIENTO");

        // Utilizando sharedPreferences en vez del Intent
        Context context = getApplicationContext();
        SharedPreferences sharedPref = context.getSharedPreferences("ALMACENAMIENTO",MODE_PRIVATE);
        String tipoAlmacen = sharedPref.getString("ALMACENAMIENTO","FILERECURSOS");

        if (tipoAlmacen.equals("BBDD")){
            bd = new UsuariosBD(this);
            usuarios = bd.leerUsuarios();
        }
        if (tipoAlmacen.equals("FICHERO")){
            uFILE = new UsuariosFILE(this);
            usuarios = uFILE.leerUsuarios("INTERNO");
        }
        if (tipoAlmacen.equals("FILERECURSOS")){
            uFILE = new UsuariosFILE(this);
            usuarios = uFILE.leerUsuarios("RECURSOS");
        }
        // Obtener el Recycler
        miRecicler = (RecyclerView) findViewById(R.id.miRecicler);
        miRecicler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(this);
        miRecicler.setLayoutManager(lManager);

        // Crear un nuevo adaptador
        adapter = new CustomAdapter(usuarios);
        miRecicler.setAdapter(adapter);
    }
}