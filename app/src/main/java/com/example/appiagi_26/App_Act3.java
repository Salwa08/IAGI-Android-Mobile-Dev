package com.example.appiagi_26;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class App_Act3 extends AppCompatActivity {

    Button b2, b3;
    TextView t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_act3);


        b2 = findViewById(R.id.b2);

        b3 = findViewById(R.id.b3);
        t2 = findViewById(R.id.t2);

        // Bouton afficher
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t2.setText("Bonsoir");
            }
        });

        // Bouton supprimer
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t2.setText("");
            }
        });
    }
}
