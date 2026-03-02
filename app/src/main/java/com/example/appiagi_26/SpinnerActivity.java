package com.example.appiagi_26;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

public class SpinnerActivity extends AppCompatActivity {

    Spinner spinnerCountries;
    ArrayList<String> countriesList;
    ArrayAdapter<String> adapter;
    FloatingActionButton floatingActionAddButton, floatingActionEditButton, floatingActionDeleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_spinner);

        String[] countriesArray = {
                "Morocco", "Spain", "Portugal", "France", "Germany", "Canada", "USA", "Russia", "China", "Bulgaria",
                "Ukraine", "Italy", "Mexico", "Brazil", "Japan", "India", "Australia", "United Kingdom", "Belgium", "South Korea",
                "Egypt", "South Africa", "Nigeria", "Argentina", "Chile", "Colombia", "Peru", "Algeria", "Tunisia", "Turkey",
                "Saudi Arabia", "UAE", "Qatar", "Sweden", "Norway", "Finland", "Denmark", "Netherlands", "Switzerland", "Austria"
        };
        countriesList = new ArrayList<>(Arrays.asList(countriesArray));

        spinnerCountries = findViewById(R.id.spinnerCountries);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, countriesList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCountries.setAdapter(adapter);

        spinnerCountries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCountry = parent.getItemAtPosition(position).toString();
                Toast.makeText(SpinnerActivity.this, "Selected country: " + selectedCountry, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

        floatingActionAddButton = findViewById(R.id.floatingActionAddButton);
        floatingActionEditButton = findViewById(R.id.floatingActionEditButton);
        floatingActionDeleteButton = findViewById(R.id.floatingActionDeleteButton);

        floatingActionAddButton.setOnClickListener(v -> showAddDialog());
        floatingActionEditButton.setOnClickListener(v -> showEditDialog());
        floatingActionDeleteButton.setOnClickListener(v -> showDeleteConfirmDialog());

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
                spinnerCountries.setSelection(0);
            }
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    private void showEditDialog() {
        int position = spinnerCountries.getSelectedItemPosition();
        if (position == AdapterView.INVALID_POSITION) return;

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

    private void showDeleteConfirmDialog() {
        int position = spinnerCountries.getSelectedItemPosition();
        if (position == AdapterView.INVALID_POSITION) return;

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
