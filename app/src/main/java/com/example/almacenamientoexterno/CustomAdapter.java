package com.example.almacenamientoexterno;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private ArrayList<Usuario> usuarios;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private  TextView tv_nombreUsu;
        private TextView tv_apellidosUsu;
        private TextView tv_emailUsu;
        private TextView tv_edadUsu;
        private TextView tv_telefonoUsu;
        private TextView tv_idUsu;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            tv_idUsu = (TextView) view.findViewById(R.id.tvIdUsu);
            tv_nombreUsu = (TextView) view.findViewById(R.id.tvNombreUsu);
            tv_apellidosUsu = (TextView) view.findViewById(R.id.tvApellidosUsu);
            tv_emailUsu = (TextView) view.findViewById(R.id.tvEmailUsu);
            tv_edadUsu = (TextView) view.findViewById(R.id.tvEdadUsu);
            tv_telefonoUsu = (TextView) view.findViewById(R.id.tvTelefonoUsu);
        }


    }

    /**
     * Initialize the dataset of the Adapter.
     *

     * by RecyclerView.
     */
    public CustomAdapter(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_row_item, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.tv_idUsu.setText(String.valueOf(usuarios.get(i).getId_usuario()));
        viewHolder.tv_nombreUsu.setText(usuarios.get(i).getNombre_usuario());
        viewHolder.tv_apellidosUsu.setText(usuarios.get(i).getApellidos_usuario());
        viewHolder.tv_emailUsu.setText(usuarios.get(i).getEmail());
        viewHolder.tv_edadUsu.setText(String.valueOf(usuarios.get(i).getEdad()));
        viewHolder.tv_telefonoUsu.setText(String.valueOf(usuarios.get(i).getTelefono()));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return usuarios.size();
    }
}

