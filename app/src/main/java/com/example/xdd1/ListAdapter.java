package com.example.xdd1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
<<<<<<< HEAD

    //Definimos una lista de elementos
    private List<ListElements> mData;

    // Inflater para inflar la vista de cada elemento
    private LayoutInflater mInflater;

    // Listener para manejar los eventos de clic en los elementos
    private OnItemClickListener listener;
    private Context mContext;  // Contexto de la aplicación

    // Interfaz para el listener de clic en los elementos del RecyclerView
=======
    private List<ListElements> mData;
    private LayoutInflater mInflater;
    private OnItemClickListener listener;
    private Context mContext;

>>>>>>> aa1eea8cb327ab6ff8204329362bc104bc291ba0
    public interface OnItemClickListener {
        void onItemClick(ListElements item);
    }

<<<<<<< HEAD

    public ListAdapter(List<ListElements> itemList, Context context, OnItemClickListener listener) {
        this.mInflater = LayoutInflater.from(context);  // Inicialización del LayoutInflater
        this.mData = itemList;  // Asignación de la lista de datos recibida
        this.listener = listener;  // Asignación del listener recibido
        this.mContext = context;  // Asignación del contexto recibido
    }

    // Retorna la cantidad de elementos en la lista
=======
    public ListAdapter(List<ListElements> itemList, Context context, OnItemClickListener listener) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = itemList;
        this.listener = listener;
        this.mContext = context;
    }

>>>>>>> aa1eea8cb327ab6ff8204329362bc104bc291ba0
    @Override
    public int getItemCount() {
        return mData.size();
    }

<<<<<<< HEAD
    // Crea y retorna un nuevo ViewHolder que contiene la vista de un elemento de la lista
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.contactos, parent, false);  // Infla la vista del elemento desde contactos.xml
        return new ViewHolder(view);  // Retorna un nuevo ViewHolder con la vista inflada
    }

    // Vincula los datos de un elemento específico con la vista correspondiente en el ViewHolder
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));  // Llama al método bindData() del ViewHolder para vincular los datos
    }

    // Clase ViewHolder que representa la vista de un elemento en el RecyclerView
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, tel, id;  // Elementos de la vista: nombre, teléfono y ID
        ImageView llamarIcon;  // Icono de llamada telefónica

        ViewHolder(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nom);  // Busca y asigna el TextView de nombre
            tel = itemView.findViewById(R.id.te);  // Busca y asigna el TextView de teléfono
            id = itemView.findViewById(R.id.id);  // Busca y asigna el TextView de ID
            llamarIcon = itemView.findViewById(R.id.xd);  // Busca y asigna el ImageView del icono de llamada

            // Configura el OnClickListener para el elemento del RecyclerView
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(mData.get(getAdapterPosition()));  // Llama al método onItemClick del listener
                }
            });

            // Configura el OnClickListener para el icono de llamada
            llamarIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String phoneNumber = mData.get(getAdapterPosition()).getTel();  // Obtiene el número de teléfono del elemento actual
                    llamar(phoneNumber);  // Llama al método llamar() para iniciar la llamada telefónica
=======
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.contactos, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.binData(mData.get(position));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, tel;
        ImageView llamarIcon;

        ViewHolder(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nom);
            tel = itemView.findViewById(R.id.te);
            llamarIcon = itemView.findViewById(R.id.xd);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(mData.get(getAdapterPosition()));
                }
            });

            // Configurar el OnClickListener para el icono de la llamada
            llamarIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String phoneNumber = mData.get(getAdapterPosition()).getTel();
                    llamar(phoneNumber);
>>>>>>> aa1eea8cb327ab6ff8204329362bc104bc291ba0
                }
            });
        }

<<<<<<< HEAD
        // Método para vincular los datos de un elemento con los elementos de la vista
        void bindData(final ListElements item) {
            nombre.setText(item.getNombre());  // Establece el nombre del contacto en el TextView correspondiente
            tel.setText(item.getTel());  // Establece el teléfono del contacto en el TextView correspondiente
            id.setText(item.getNum());  // Establece el ID del contacto en el TextView correspondiente
        }
    }

    // Método para iniciar una llamada telefónica utilizando el número de teléfono proporcionado
    @SuppressLint("QueryPermissionsNeeded")
    private void llamar(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);  // Intent implícito para llamar
        intent.setData(Uri.parse("tel:" + phoneNumber));  // Establece el número de teléfono en el URI del intent

        // Verifica si hay una actividad que pueda manejar la acción de llamar
        if (intent.resolveActivity(mContext.getPackageManager()) != null) {
            mContext.startActivity(intent);  // Inicia la actividad de llamada telefónica
        }
    }

    // Método para actualizar la lista de datos y notificar al RecyclerView
    public void updateList(List<ListElements> newList) {
        mData = newList;  // Actualiza la lista de datos con la nueva lista proporcionada
        notifyDataSetChanged();  // Notifica al RecyclerView que los datos han cambiado
    }

=======
        void binData(final ListElements item) {
            nombre.setText(item.getNombre());
            tel.setText(item.getTel());
        }
    }


    @SuppressLint("QueryPermissionsNeeded")
    private void llamar(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));

        if (intent.resolveActivity(mContext.getPackageManager()) != null) {
            mContext.startActivity(intent);
        }
    }
>>>>>>> aa1eea8cb327ab6ff8204329362bc104bc291ba0
}
