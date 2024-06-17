package com.example.xdd1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class contac extends AppCompatActivity {

    //Definimos variables a utilizar
    private EditText et1;
    private EditText et2;
    private EditText id;
    private Button btnSave;
    private Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contac);

        // Le damos a las variables los datos de los Views
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        id = findViewById(R.id.id);
        btnSave = findViewById(R.id.button3);
        btnDelete = findViewById(R.id.button4);

        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombre");
        String telefono = intent.getStringExtra("telefono");
        String id1 = intent.getStringExtra("id");

        et1.setText(nombre);
        et2.setText(telefono);
        id.setText(id1);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveContact();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteContact();
            }
        });
    }

    private void saveContact() {
        // Obtenemos los datos del contacto
        String updatedName = et1.getText().toString();
        String updatedPhone = et2.getText().toString();
        String contactId = id.getText().toString();

        //Creamos un intent para eviar los datos actualizados.
        Intent resultIntent = new Intent();

        //Agregamos los Datos Actualizados
        resultIntent.putExtra("updated_name", updatedName);
        resultIntent.putExtra("updated_phone", updatedPhone);
        resultIntent.putExtra("contact_id", contactId);

        //Enviamos el resultado devuelta al mainactivity
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    private void deleteContact() {

        //Agarramos el id del contacto.
        String contactId = id.getText().toString();

        //Creamos un intent para eviar el id del contacto a eliminar
        Intent resultIntent = new Intent();
        resultIntent.putExtra("delete_id", contactId);

        //Ponemos el resultado y agregamos el id a eliminar.
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
