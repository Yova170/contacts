package com.example.xdd1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONException;
import org.json.JSONObject;

public class add extends AppCompatActivity {
 private  int count =0;
 private EditText input_nombre,input_telefono;
 private Button btn_añadir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            input_telefono=findViewById(R.id.input_telefono_add);
            input_nombre=findViewById(R.id.input_nombre_add);
           btn_añadir=findViewById(R.id.btn_añadir);


            return insets;

        });
    }

    public void onClickGuardar() {
        String nombre = input_nombre.getText().toString();
        String telefono = input_telefono.getText().toString();
        int id = count = count+1;

        // Crear un objeto JSON para el contacto
        JSONObject contacto = new JSONObject();
        try {
            contacto.put("id", id);
            contacto.put("nombre", nombre);
            contacto.put("telefono", telefono);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Obtener SharedPreferences
        SharedPreferences preferences = getSharedPreferences("contactos_lista", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        // Guardar el contacto como una cadena JSON
        editor.putString(String.valueOf(id), contacto.toString());
        editor.commit();

        input_nombre.setText("");
        input_telefono.setText("");
    }

}