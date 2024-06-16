package com.example.xdd1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
<<<<<<< HEAD
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, ListAdapter.OnItemClickListener {
    private static final int REQUEST_CODE_ADD_CONTACT = 1;
    private static final int REQUEST_CODE_EDIT_CONTACT = 2;
    private List<ListElements> elements; // Lista de elementos de contactos
    private ListAdapter listAdapter; // Adaptador para el RecyclerView
    private SearchView searchView; // Vista de búsqueda
=======
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, ListAdapter.OnItemClickListener {
    List<ListElements> elements;
    ListAdapter listAdapter;
>>>>>>> aa1eea8cb327ab6ff8204329362bc104bc291ba0

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

<<<<<<< HEAD
        // Habilita el borde a borde (EdgeToEdge) para la actividad
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main); // Establece el layout de la actividad
=======
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
>>>>>>> aa1eea8cb327ab6ff8204329362bc104bc291ba0
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

<<<<<<< HEAD
        // Configura la vista de búsqueda y el listener de texto de búsqueda
        searchView = findViewById(R.id.busqueda);
        searchView.setOnQueryTextListener(this);

        init(); // Inicializa la lista de contactos
    }

    // Método para iniciar la actividad para agregar un nuevo contacto
    public void anadir(View view) {
        Intent intent = new Intent(this, add.class);
        int nextId = getNextId();
        intent.putExtra("next_id", nextId);
        startActivityForResult(intent, REQUEST_CODE_ADD_CONTACT);
    }

    // Método para obtener el próximo ID disponible para un nuevo contacto
    private int getNextId() {
        int maxId = 0;
        for (ListElements element : elements) {
            int id = Integer.parseInt(element.getNum());
            if (id > maxId) {
                maxId = id;
            }
        }
        return maxId + 1;
    }

    // Método para iniciar una llamada telefónica
=======
        init();
    }

    public void anadir(View view) {
        Intent intent = new Intent(this, add.class);
        startActivity(intent);
    }

>>>>>>> aa1eea8cb327ab6ff8204329362bc104bc291ba0
    public void llamar(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }

<<<<<<< HEAD
    // Método para inicializar la lista de contactos
    public void init() {
        elements = XmlParser.parseContacts(this); // Parsea los contactos desde un archivo XML

        // Ordenar alfabéticamente por nombre
        Collections.sort(elements, new Comparator<ListElements>() {
            @Override
            public int compare(ListElements o1, ListElements o2) {
                return o1.getNombre().compareTo(o2.getNombre());
            }
        });

        // Configura el adaptador para el RecyclerView
=======
    public void init() {
        elements = new ArrayList<>();
        elements.add(new ListElements("Bryan", "65486053"));
        elements.add(new ListElements("Piruliws", "65486053"));
        elements.add(new ListElements("Bran", "65486053"));
        elements.add(new ListElements("Bradley", "65486053"));
        elements.add(new ListElements("Allan", "65486053"));
        elements.add(new ListElements("Pepe", "65486053"));
        elements.add(new ListElements("Joseph", "65486053"));
        elements.add(new ListElements("Bryan", "65486053"));
        elements.add(new ListElements("Piruliws", "65486053"));
        elements.add(new ListElements("Bran", "65486053"));
        elements.add(new ListElements("Bradley", "65486053"));
        elements.add(new ListElements("Allan", "65486053"));
        elements.add(new ListElements("Pepe", "65486053"));
        elements.add(new ListElements("Joseph", "65486053"));

>>>>>>> aa1eea8cb327ab6ff8204329362bc104bc291ba0
        listAdapter = new ListAdapter(elements, this, this);
        RecyclerView recyclerView = findViewById(R.id.add);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }

<<<<<<< HEAD
    // Método llamado cuando se hace clic en un elemento del RecyclerView
=======
>>>>>>> aa1eea8cb327ab6ff8204329362bc104bc291ba0
    @Override
    public void onItemClick(ListElements item) {
        Intent intent = new Intent(this, contac.class);
        intent.putExtra("nombre", item.getNombre());
        intent.putExtra("telefono", item.getTel());
<<<<<<< HEAD
        intent.putExtra("id", item.getNum());
        startActivityForResult(intent, REQUEST_CODE_EDIT_CONTACT);
    }

    // Método llamado cuando se envía el texto de búsqueda
=======
        startActivity(intent);
    }

>>>>>>> aa1eea8cb327ab6ff8204329362bc104bc291ba0
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

<<<<<<< HEAD
    // Método llamado cuando cambia el texto de búsqueda
    @Override
    public boolean onQueryTextChange(String newText) {
        filterContacts(newText); // Filtra los contactos según el texto de búsqueda
        return true;
    }

    // Método para filtrar la lista de contactos según el texto de búsqueda
    private void filterContacts(String query) {
        List<ListElements> filteredList = new ArrayList<>();
        for (ListElements element : elements) {
            if (element.getNombre().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(element);
            }
        }
        listAdapter.updateList(filteredList); // Actualiza el adaptador con la lista filtrada
    }

    // Método llamado cuando se completa una actividad iniciada para agregar o editar un contacto
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD_CONTACT && resultCode == RESULT_OK) {
            String name = data.getStringExtra("name");
            String phone = data.getStringExtra("phone");
            String id = data.getStringExtra("id");

            ListElements newElement = new ListElements(name, phone, id);
            elements.add(newElement);

            // Reordenar la lista después de agregar un nuevo contacto
            Collections.sort(elements, new Comparator<ListElements>() {
                @Override
                public int compare(ListElements o1, ListElements o2) {
                    return o1.getNombre().compareTo(o2.getNombre());
                }
            });

            listAdapter.notifyDataSetChanged(); // Notifica al adaptador que los datos han cambiado
        } else if (requestCode == REQUEST_CODE_EDIT_CONTACT && resultCode == RESULT_OK) {
            if (data.hasExtra("delete_id")) {
                String deleteId = data.getStringExtra("delete_id");
                removeContactById(deleteId); // Elimina el contacto según su ID
            } else {
                String updatedName = data.getStringExtra("updated_name");
                String updatedPhone = data.getStringExtra("updated_phone");
                String contactId = data.getStringExtra("contact_id");

                updateContact(contactId, updatedName, updatedPhone); // Actualiza el contacto con el nuevo nombre y teléfono
            }
            listAdapter.notifyDataSetChanged(); // Notifica al adaptador que los datos han cambiado
        }
    }

    // Método para actualizar un contacto con un nuevo nombre y teléfono
    private void updateContact(String contactId, String updatedName, String updatedPhone) {
        for (ListElements element : elements) {
            if (element.getNum().equals(contactId)) {
                element.setNombre(updatedName);
                element.setTel(updatedPhone);
                break;
            }
        }

        // Reordenar la lista después de actualizar un contacto
        Collections.sort(elements, new Comparator<ListElements>() {
            @Override
            public int compare(ListElements o1, ListElements o2) {
                return o1.getNombre().compareTo(o2.getNombre());
            }
        });
    }

    // Método para eliminar un contacto según su ID
    private void removeContactById(String contactId) {
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).getNum().equals(contactId)) {
                elements.remove(i);
                break;
            }
        }
    }
}

=======
    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
>>>>>>> aa1eea8cb327ab6ff8204329362bc104bc291ba0
