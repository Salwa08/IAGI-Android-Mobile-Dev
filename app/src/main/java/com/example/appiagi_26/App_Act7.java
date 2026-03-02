package com.example.appiagi_26;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class App_Act7 extends AppCompatActivity {

    Button b1;
    EditText txt2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_app_act7);
        b1 = findViewById(R.id.b1);
        txt2 = findViewById(R.id.txt2);

        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {



            EditText txt2 = findViewById(R.id.txt2);
            String s1 = txt2.getText().toString();
            Intent relation= new Intent(getApplicationContext(), App_Act8.class);
            relation.putExtra("c1", s1);
            startActivity(relation);
        }});



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}