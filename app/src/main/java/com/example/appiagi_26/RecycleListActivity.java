package com.example.appiagi_26;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

public class RecycleListActivity extends AppCompatActivity {

    private ArrayList<String> countriesList;
    private CountryAdapter adapter;
    private RecyclerView recyclerView;
    private FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recycle_list);

        String[] countriesArray = {"Morocco", "Spain", "Portugal", "France", "Germany", "Canada", "USA", "Russia", "China", "Bulgaria", "Ukraine", "Italy", "Mexico", "Brazil", "Japan", "India", "Australia", "United Kingdom", "Belgium", "South Korea"};
        countriesList = new ArrayList<>(Arrays.asList(countriesArray));

        recyclerView = findViewById(R.id.recyclerViewCountries);
        fabAdd = findViewById(R.id.fabAddCountry);

        adapter = new CountryAdapter(countriesList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        fabAdd.setOnClickListener(v -> showAddDialog());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void showAddDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add New Country");

        final EditText input = new EditText(this);
        builder.setView(input);

        builder.setPositiveButton("Add", (dialog, which) -> {
            String countryName = input.getText().toString().trim();
            if (!countryName.isEmpty()) {
                countriesList.add(0, countryName);
                adapter.notifyItemInserted(0);
                recyclerView.scrollToPosition(0);
            }
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }
}
