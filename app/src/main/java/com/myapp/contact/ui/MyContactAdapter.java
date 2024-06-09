package com.myapp.contact.ui;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.myapp.contact.R; // Import your R.java file
import java.util.List;
public class MyContactAdapter {
    private List<Contacto> contactos;
    private Context context;

    public MyContactAdapter(List<Contacto> contactos, Context context) {
        this.contactos = contactos;
        this.context = context;
    }
}
