package com.example.appiagi_26;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class ListActivity extends AppCompatActivity {
    ArrayList<String> countriesList;
    ArrayAdapter<String> adapter;
    ListView ListCountries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list);

        String[] countriesArray = {"Morocco", "Spain", "Portugal", "France", "Germany", "Canada", "USA", "Russia", "China", "Bulgaria", "Ukraine", "Italy", "Mexico", "Brazil", "Japan", "India", "Australia", "United Kingdom", "Belgium", "South Korea"};
        countriesList = new ArrayList<>(Arrays.asList(countriesArray));

        ListCountries = findViewById(R.id.ListCountries);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, countriesList);
        ListCountries.setAdapter(adapter);

        ListCountries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedCountry = countriesList.get(position);
                Toast.makeText(getApplicationContext(), selectedCountry, Toast.LENGTH_SHORT).show();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
