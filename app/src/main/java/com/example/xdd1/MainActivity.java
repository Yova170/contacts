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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, ListAdapter.OnItemClickListener {
    private static final int REQUEST_CODE_ADD_CONTACT = 1;
    private static final int REQUEST_CODE_EDIT_CONTACT = 2;
    private List<ListElements> elements;
    private List<ListElements> filteredElements;
    private ListAdapter listAdapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        searchView = findViewById(R.id.busqueda);
        searchView.setOnQueryTextListener(this);

        init();
    }

    public void anadir(View view) {
        Intent intent = new Intent(this, add.class);
        int nextId = getNextId();
        intent.putExtra("next_id", nextId);
        startActivityForResult(intent, REQUEST_CODE_ADD_CONTACT);
    }

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

    public void llamar(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }

    public void init() {
        elements = XmlParser.parseContacts(this);
        filteredElements = new ArrayList<>(elements);

        // Ordenar alfabéticamente por nombre
        Collections.sort(filteredElements, new Comparator<ListElements>() {
            @Override
            public int compare(ListElements o1, ListElements o2) {
                return o1.getNombre().compareToIgnoreCase(o2.getNombre());
            }
        });

        listAdapter = new ListAdapter(filteredElements, this, this);
        RecyclerView recyclerView = findViewById(R.id.add);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }

    @Override
    public void onItemClick(ListElements item) {
        Intent intent = new Intent(this, contac.class);
        intent.putExtra("nombre", item.getNombre());
        intent.putExtra("telefono", item.getTel());
        intent.putExtra("id", item.getNum());
        startActivityForResult(intent, REQUEST_CODE_EDIT_CONTACT);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        filterContacts(newText);
        return true;
    }

    private void filterContacts(String query) {
        filteredElements.clear();
        for (ListElements element : elements) {
            if (element.getNombre().toLowerCase().contains(query.toLowerCase())) {
                filteredElements.add(element);
            }
        }
        listAdapter.updateList(filteredElements);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD_CONTACT && resultCode == RESULT_OK) {
            String name = data.getStringExtra("name");
            String phone = data.getStringExtra("phone");
            String id = data.getStringExtra("id");

            ListElements newElement = new ListElements(name, phone, id);
            elements.add(newElement);
            XmlParser.saveContacts(this, elements);
            Collections.sort(elements, new Comparator<ListElements>() {
                @Override
                public int compare(ListElements o1, ListElements o2) {
                    return o1.getNombre().compareToIgnoreCase(o2.getNombre());
                }
            });
            filterContacts(searchView.getQuery().toString());
        } else if (requestCode == REQUEST_CODE_EDIT_CONTACT && resultCode == RESULT_OK) {
            if (data.hasExtra("delete_id")) {
                String deleteId = data.getStringExtra("delete_id");
                removeContactById(deleteId);
            } else {
                String updatedName = data.getStringExtra("updated_name");
                String updatedPhone = data.getStringExtra("updated_phone");
                String contactId = data.getStringExtra("contact_id");

                updateContact(contactId, updatedName, updatedPhone);
            }
            XmlParser.saveContacts(this, elements);
            Collections.sort(elements, new Comparator<ListElements>() {
                @Override
                public int compare(ListElements o1, ListElements o2) {
                    return o1.getNombre().compareToIgnoreCase(o2.getNombre());
                }
            });
            filterContacts(searchView.getQuery().toString());
        }
    }

    private void updateContact(String contactId, String updatedName, String updatedPhone) {
        for (ListElements element : elements) {
            if (element.getNum().equals(contactId)) {
                element.setNombre(updatedName);
                element.setTel(updatedPhone);
                break;
            }
        }
    }

    private void removeContactById(String contactId) {
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).getNum().equals(contactId)) {
                elements.remove(i);
                break;
            }
        }
    }
}
