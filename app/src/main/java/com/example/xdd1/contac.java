package com.example.xdd1;

import android.content.Context;
import android.content.Intent;
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

public class contac extends AppCompatActivity {
    private EditText et1;
    private EditText et2;
private Button btn_modificar , btn_eliminar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contac);

        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        btn_eliminar=findViewById(R.id.btn_eliminar);
        btn_modificar=findViewById(R.id.btn_modificar);
        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombre");
        String telefono = intent.getStringExtra("telefono");

        et1.setText(nombre);
        et2.setText(telefono);

        btn_modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre= et1.getText().toString();
                String telefono= et2.getText().toString();
                SharedPreferences preferences = getSharedPreferences("contactos_lista", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor= preferences.edit();
                editor.putString("nombre",nombre);
                editor.putString("telefono",telefono);
                et1.setText(nombre);
                et2.setText(telefono);
                editor.commit();

            }
        });
        btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(""); // de acuerdo al id del contancto se elimina

                // Obtener SharedPreferences
                SharedPreferences preferences = getSharedPreferences("contactos_lista", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                // Eliminar el contacto con el ID específico
                editor.remove(String.valueOf(id));
                editor.commit();
            }
        });
    }
}