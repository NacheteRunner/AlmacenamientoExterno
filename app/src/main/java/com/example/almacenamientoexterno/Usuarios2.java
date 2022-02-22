package com.example.almacenamientoexterno;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
// Actividad para probar la bd SQLite con botones anterior y siguiente y textView
public class Usuarios2 extends AppCompatActivity {

    private ArrayList<Usuario> usuarios;
    private UsuariosBD bd;
    private UsuariosFILE uFILE;
    private Button anterior;
    private Button siguiente;
    private int cont;
    private TextView tvIdUsu;
    private TextView tvNombreUsu;
    private TextView tvApellidosUsu;
    private TextView tvEmailUsu;
    private TextView tvEdadUsu;
    private TextView tvTelefonoUsu;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios2);
        anterior = findViewById(R.id.btnAnterior);
        siguiente = findViewById(R.id.btnSiguiente);
        tvIdUsu = findViewById(R.id.tvIdUsu);
        tvNombreUsu = findViewById(R.id.tvNombreUsu);
        tvApellidosUsu = findViewById(R.id.tvApellidosUsu);
        tvEmailUsu = findViewById(R.id.tvEmailUsu);
        tvEdadUsu = findViewById(R.id.tvEdadUsu);
        tvTelefonoUsu = findViewById(R.id.tvTelefonoUsu);
        usuarios = new ArrayList<>();
        // Voy a ver de donde tomo los datos si de bbdd o de fichero
        //Intent intent = getIntent();
        //String tipoAlmacen = intent.getStringExtra("ALMACENAMIENTO");
        // Utilizando sharedPreferences en vez del intent
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




        if (usuarios.size()>0) {
            cont = 0;

            tvIdUsu.setText(String.valueOf(usuarios.get(cont).getId_usuario()));
            tvNombreUsu.setText(usuarios.get(cont).getNombre_usuario());
            tvApellidosUsu.setText(usuarios.get(cont).getApellidos_usuario());
            tvEmailUsu.setText(usuarios.get(cont).getEmail());
            tvEdadUsu.setText(String.valueOf(usuarios.get(cont).getEdad()));
            tvTelefonoUsu.setText(String.valueOf(usuarios.get(cont).getTelefono()));
        }else
            Toast.makeText(this,"NO EXISTEN DATOS DE USUARIOS", Toast.LENGTH_LONG).show();

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cont<usuarios.size()-1){
                    cont++;
                    tvIdUsu.setText(String.valueOf(usuarios.get(cont).getId_usuario()));
                    tvNombreUsu.setText(usuarios.get(cont).getNombre_usuario());
                    tvApellidosUsu.setText(usuarios.get(cont).getApellidos_usuario());
                    tvEmailUsu.setText(usuarios.get(cont).getEmail());
                    tvEdadUsu.setText(String.valueOf(usuarios.get(cont).getEdad()));
                    tvTelefonoUsu.setText(String.valueOf(usuarios.get(cont).getTelefono()));
                }
            }
        });

        anterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cont>0){
                    cont--;
                    tvIdUsu.setText(String.valueOf(usuarios.get(cont).getId_usuario()));
                    tvNombreUsu.setText(usuarios.get(cont).getNombre_usuario());
                    tvApellidosUsu.setText(usuarios.get(cont).getApellidos_usuario());
                    tvEmailUsu.setText(usuarios.get(cont).getEmail());
                    tvEdadUsu.setText(String.valueOf(usuarios.get(cont).getEdad()));
                    tvTelefonoUsu.setText(String.valueOf(usuarios.get(cont).getTelefono()));
                }
            }
        });



    }




}