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
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, ListAdapter.OnItemClickListener {
    List<ListElements> elements;
    ListAdapter listAdapter;

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

        init();
    }

    public void anadir(View view) {
        Intent intent = new Intent(this, add.class);
        startActivity(intent);
    }

    public void llamar(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }

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

        listAdapter = new ListAdapter(elements, this, this);
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
        startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
