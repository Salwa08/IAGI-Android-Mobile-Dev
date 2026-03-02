package com.example.appiagi_26;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

public class GridActivity extends AppCompatActivity {

    ArrayList<String> countriesList;
    ArrayAdapter<String> adapter;
    GridView GridCountries;

    FloatingActionButton floatingActionAddButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_grid);

        // List of at least 40 countries as per requirements
        String[] countriesArray = {
                "Morocco", "Spain", "Portugal", "France", "Germany", "Canada", "USA", "Russia", "China", "Bulgaria",
                "Ukraine", "Italy", "Mexico", "Brazil", "Japan", "India", "Australia", "United Kingdom", "Belgium", "South Korea",
                "Egypt", "South Africa", "Nigeria", "Argentina", "Chile", "Colombia", "Peru", "Algeria", "Tunisia", "Turkey",
                "Saudi Arabia", "UAE", "Qatar", "Sweden", "Norway", "Finland", "Denmark", "Netherlands", "Switzerland", "Austria"
        };
        countriesList = new ArrayList<>(Arrays.asList(countriesArray));

        GridCountries = findViewById(R.id.GridCountries);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, countriesList);
        GridCountries.setAdapter(adapter);

        GridCountries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedCountry = countriesList.get(position);
                Toast.makeText(getApplicationContext(), selectedCountry, Toast.LENGTH_SHORT).show();
            }
        });

        // Long click for Edit and Delete
        GridCountries.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showEditDeleteDialog(position);
                return true;
            }
        });

        floatingActionAddButton = findViewById(R.id.floatingActionEditButton); // Reusing FAB ID for Add
        floatingActionAddButton.setOnClickListener(v -> showAddDialog());

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
            String name = input.getText().toString().trim();
            if (!name.isEmpty()) {
                countriesList.add(0, name);
                adapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    private void showEditDeleteDialog(int position) {
        String[] options = {"Edit", "Delete"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Options for " + countriesList.get(position));
        builder.setItems(options, (dialog, which) -> {
            if (which == 0) {
                showEditDialog(position);
            } else {
                showDeleteConfirmDialog(position);
            }
        });
        builder.show();
    }

    private void showEditDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Country");

        final EditText input = new EditText(this);
        input.setText(countriesList.get(position));
        builder.setView(input);

        builder.setPositiveButton("Update", (dialog, which) -> {
            String newName = input.getText().toString().trim();
            if (!newName.isEmpty()) {
                countriesList.set(position, newName);
                adapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    private void showDeleteConfirmDialog(int position) {
        new AlertDialog.Builder(this)
                .setTitle("Delete Country")
                .setMessage("Are you sure you want to delete " + countriesList.get(position) + "?")
                .setPositiveButton("Delete", (dialog, which) -> {
                    countriesList.remove(position);
                    adapter.notifyDataSetChanged();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}
