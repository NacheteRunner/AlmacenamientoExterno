package com.example.almacenamientoexterno;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

// Insertar
public class MainActivity extends AppCompatActivity {

    private TextView tvNombre;
    private TextView tvApellidos;
    private TextView tvEmail;
    private TextView tvEdad;
    private TextView tvTelefono;

    private EditText etNombre;
    private EditText etApellidos;
    private EditText etEmail;
    private EditText etEdad;
    private EditText etTelefono;

    private RadioButton radioBBDD;
    private RadioButton radioFILE;
    private RadioButton radioFREC;

    private Button btnInsertar;
    private Button btnMostrar;
    private Button btnLimpiar;

    private UsuariosBD bd;
    private UsuariosFILE uFILE;
    private Usuario usuario;

    private Switch sWitchRecicler;

    public static int id = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvNombre = findViewById(R.id.tvNombre);
        tvApellidos = findViewById(R.id.tvApellidos);
        tvEmail = findViewById(R.id.tvEmail);
        tvTelefono = findViewById(R.id.tvTelefono);
        tvEdad = findViewById(R.id.tvEdad);
        etNombre = findViewById(R.id.etNombre);
        etApellidos = findViewById(R.id.etApellidos);
        etEmail = findViewById(R.id.etEmail);
        etEdad = findViewById(R.id.etEdad);
        etTelefono = findViewById(R.id.etTelefono);
        btnInsertar = findViewById(R.id.btnInsertar);
        btnMostrar = findViewById(R.id.btnMostrar);
        btnLimpiar = findViewById(R.id.btnLimpiar);
        radioBBDD = findViewById(R.id.radioButtonBBDD);
        radioFILE = findViewById(R.id.radioButtonFILE);
        radioFREC = findViewById(R.id.radioButtonF_REC);
        sWitchRecicler = findViewById(R.id.switch1);
        radioBBDD.setChecked(true);
        Context context = getApplicationContext();
        SharedPreferences sharedPref = context.getSharedPreferences("ALMACENAMIENTO",MODE_PRIVATE);




        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioBBDD.isChecked()){
                    bd = new UsuariosBD(MainActivity.this);
                    insertarUsuarioBBDD();
                    Toast.makeText(MainActivity.this,"SE HA INSERTADO UN USUARIO EN BBDD",Toast.LENGTH_LONG).show();

                }
                if (radioFILE.isChecked()){
                    uFILE = new UsuariosFILE(MainActivity.this);
                    insertarUsuarioFILE();
                    Toast.makeText(MainActivity.this,"SE HA INSERTADO UN USUARIO EN FICHERO",Toast.LENGTH_LONG).show();
                }
                if (radioFREC.isChecked()){
                    Toast.makeText(MainActivity.this,"FICHERO DE RECURSOS DE SOLO LECTURA",Toast.LENGTH_LONG).show();
                }

            }
        });

        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPref.edit();
                Intent intent;
                if (sWitchRecicler.isChecked()){
                    intent = new Intent(MainActivity.this, UsuariosActivity.class);
                }else{
                    intent = new Intent(MainActivity.this, Usuarios2.class);
                }

                if (radioBBDD.isChecked()){
                    editor.putString("ALMACENAMIENTO", "BBDD");
                    editor.apply();
                    //intent.putExtra("ALMACENAMIENTO", "BBDD");
                }
                if (radioFILE.isChecked()){
                    editor.putString("ALMACENAMIENTO", "FICHERO");
                    editor.apply();
                    //intent.putExtra("ALMACENAMIENTO", "FICHERO");
                }
                if (radioFREC.isChecked()){
                    editor.putString("ALMACENAMIENTO", "FILERECURSOS");
                    editor.apply();
                    //intent.putExtra("ALMACENAMIENTO", "FILERECURSOS");
                }


                startActivity(intent);
            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiarCampos();
            }
        });


    }
    private void insertarUsuarioFILE(){
        usuario = recogerDatosUsuario();
        usuario.setId_usuario(id++);
        uFILE.insertarUsuario(usuario);

    }

    private void insertarUsuarioBBDD() {
        usuario = recogerDatosUsuario();
        bd.insertarUsuario(usuario);

    }

    private Usuario recogerDatosUsuario(){
        usuario = new Usuario();
        usuario.setNombre_usuario(etNombre.getText().toString());
        usuario.setApellidos_usuario(etApellidos.getText().toString());
        usuario.setEmail(etEmail.getText().toString());
        if(!etEdad.getText().toString().equals(""))
            usuario.setEdad(Integer.parseInt(etEdad.getText().toString()));
        if(!etTelefono.getText().toString().equals(""))
            usuario.setTelefono(Integer.parseInt(etTelefono.getText().toString()));
        return usuario;
    }

    private void limpiarCampos(){
        etNombre.setText("");
        etApellidos.setText("");
        etEmail.setText("");
        etTelefono.setText("");
        etEdad.setText("");
    }
}