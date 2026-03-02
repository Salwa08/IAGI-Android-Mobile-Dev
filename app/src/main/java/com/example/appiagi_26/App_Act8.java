package com.example.appiagi_26;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class App_Act8 extends AppCompatActivity {

    Button b9;
    TextView t3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_app_act8);

        b9 = findViewById(R.id.b9);





        Intent intent = getIntent();
        String s1= intent.getStringExtra("c1");
        TextView t3 = findViewById(R.id.t3);
        t3.setText("Bonjour " + s1);

        b9.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {




                Intent relation= new Intent(getApplicationContext(), App_Act7.class);
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