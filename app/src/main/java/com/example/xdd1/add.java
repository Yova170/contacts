package com.example.xdd1;

<<<<<<< HEAD
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
=======
import android.os.Bundle;
>>>>>>> aa1eea8cb327ab6ff8204329362bc104bc291ba0

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

<<<<<<< HEAD
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class add extends AppCompatActivity {

    // Definimos Variables
    private EditText nameEditText;
    private EditText phoneEditText;
    private Button saveButton;
    private int nextId;

=======
public class add extends AppCompatActivity {

>>>>>>> aa1eea8cb327ab6ff8204329362bc104bc291ba0
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);
<<<<<<< HEAD

=======
>>>>>>> aa1eea8cb327ab6ff8204329362bc104bc291ba0
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
<<<<<<< HEAD
        // Definir Variables
        nameEditText = findViewById(R.id.editTextText); // Nombre EditText
        phoneEditText = findViewById(R.id.editTextPhone); // Teléfono EditText
        saveButton = findViewById(R.id.button); // Botón de guardar

        // Obtener el próximo ID desde el intent
        nextId = getIntent().getIntExtra("next_id", 1);

        // Fucnion para el boton de guardar
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String phone = phoneEditText.getText().toString();
                String id = String.valueOf(nextId); // Utilizar el ID recibido

                // Copiar el archivo XML desde res/raw a la carpeta de archivos internos
                copyRawFileToInternalStorage();

                // Modificar el archivo XML en la carpeta de archivos internos
                modifyXmlFile(name, phone, id);

                Intent resultIntent = new Intent();
                resultIntent.putExtra("name", name);
                resultIntent.putExtra("phone", phone);
                resultIntent.putExtra("id", id);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    private void copyRawFileToInternalStorage() {
        InputStream inputStream = getResources().openRawResource(R.raw.contacts);
        File file = new File(getFilesDir(), "contacts.xml");

        try {
            OutputStream outputStream = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void modifyXmlFile(String name, String phone, String id) {
        try {
            // Obtener la ruta del archivo XML en la carpeta de archivos internos
            File file = new File(getFilesDir(), "contacts.xml");

            // Crear un objeto FileWriter para escribir en el archivo XML
            FileWriter fileWriter = new FileWriter(file, true);

            // Construir el texto XML para el nuevo contacto
            String newContactXml = "<contact>\n" +
                    "    <nombre>" + name + "</nombre>\n" +
                    "    <tel>" + phone + "</tel>\n" +
                    "    <id>" + id + "</id>\n" +
                    "</contact>\n";

            // Escribir la el texto XML en el archivo
            fileWriter.write(newContactXml);

            // Cerrar el FileWriter
            fileWriter.close();

            Log.d("ModifyXmlFile", "Nuevo contacto agregado al archivo XML en la carpeta de archivos internos.");
        } catch (IOException e) {
            Log.e("ModifyXmlFile", "Error al escribir en el archivo XML: " + e.getMessage());
            e.printStackTrace();
        }
    }

}

=======
    }
}
>>>>>>> aa1eea8cb327ab6ff8204329362bc104bc291ba0
