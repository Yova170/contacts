package com.example.xdd1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.xdd1.ConexionApi.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class add extends AppCompatActivity {

    // Definimos Variables
    private EditText nameEditText;
    private EditText phoneEditText;
    private String urlApi="https://92c5-190-219-102-217.ngrok-free.app";
    private Button saveButton;
    private int nextId;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Definir Variables
        nameEditText = findViewById(R.id.editTextText); // Nombre EditText
        phoneEditText = findViewById(R.id.editTextPhone); // Teléfono EditText
        saveButton = findViewById(R.id.button); // Botón de guardar

        // Obtener el próximo ID desde el intent
        nextId = getIntent().getIntExtra("next_id", 1);

        // Configurar Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urlApi+"/CrearContacto") // Cambia esto por la URL base de tu API
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        // Función para el botón de guardar
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String phone = phoneEditText.getText().toString();
                String id = String.valueOf(nextId);

                ListElements contacto = new ListElements(name, phone, id);
                sendDataToApi(contacto);
            }
        });
    }

    private void sendDataToApi(ListElements contacto) {
        Call<ListElements> call = apiService.crearContacto(contacto);
        call.enqueue(new Callback<ListElements>() {
            @Override
            public void onResponse(Call<ListElements> call, Response<ListElements> response) {
                if (response.isSuccessful()) {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("name", contacto.getNombre());
                    resultIntent.putExtra("phone", contacto.getTel());
                    //resultIntent.putExtra("id", contacto.getId());
                    setResult(RESULT_OK, resultIntent);
                    finish();
                } else {
                    // Manejar error en la respuesta
                    Log.e("SendDataToApi", "Error en la respuesta del servidor: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ListElements> call, Throwable t) {
                // Manejar error en la solicitud
                Log.e("SendDataToApi", "Error al enviar los datos: " + t.getMessage());
            }
        });
    }
}
