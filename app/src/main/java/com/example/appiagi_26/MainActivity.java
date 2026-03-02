package com.example.appiagi_26;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize Buttons and set Listeners
        findViewById(R.id.btnAppListGridSpinner).setOnClickListener(v -> 
            startActivity(new Intent(MainActivity.this, App_ListGridSpinner.class)));

        findViewById(R.id.btnAppMenu).setOnClickListener(v -> 
            startActivity(new Intent(MainActivity.this, App_menu.class)));

        findViewById(R.id.btnRecycleList).setOnClickListener(v -> 
            startActivity(new Intent(MainActivity.this, RecycleListActivity.class)));

        findViewById(R.id.btnAct2).setOnClickListener(v -> 
            startActivity(new Intent(MainActivity.this, App_Act2.class)));

        findViewById(R.id.btnAct3).setOnClickListener(v -> 
            startActivity(new Intent(MainActivity.this, App_Act3.class)));

        findViewById(R.id.btnAct5).setOnClickListener(v -> 
            startActivity(new Intent(MainActivity.this, App_Act5.class)));

        findViewById(R.id.btnAct7).setOnClickListener(v -> 
            startActivity(new Intent(MainActivity.this, App_Act7.class)));

        findViewById(R.id.btnAct8).setOnClickListener(v -> 
            startActivity(new Intent(MainActivity.this, App_Act8.class)));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
