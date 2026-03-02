package com.example.appiagi_26;

import android.content.Intent;
import android.os.Bundle;

import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class App_Act12 extends AppCompatActivity {

    ImageView imgChat, imgChien, imgPinguin;
    String[] animalDescriptions = {
            "The cat is a small, carnivorous mammal. Cats are often valued by humans for companionship.",
            "Dogs are domesticated mammals, known as man's best friend. Loyal, playful, and intelligent.",
            "Penguins are flightless birds that live in the southern hemisphere. They are excellent swimmers."
    };

    String[] animalTypes = {"Chat", "Chien", "Pinguin"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_app_act12);

        imgChat = findViewById(R.id.imgChat);
        imgChien = findViewById(R.id.imgChien);
        imgPinguin = findViewById(R.id.imgPinguin);

        imgChat.setImageResource(R.drawable.chat);
        imgChien.setImageResource(R.drawable.chien);
        imgPinguin.setImageResource(R.drawable.pinguin);

        ImageView[] imgs = {imgChat, imgChien, imgPinguin};

        for (int i = 0; i < imgs.length; i++) {
            final int index = i;

            imgs[i].setOnClickListener(v -> {
                
                v.animate()
                        .scaleX(0.9f)
                        .scaleY(0.9f)
                        .setDuration(100)
                        .withEndAction(() -> {
                            // Animate back to normal
                            v.animate()
                                    .scaleX(1f)
                                    .scaleY(1f)
                                    .setDuration(100);
                        });

                Intent intent = new Intent(this, App_Act12_new.class);
                intent.putExtra("desc", animalDescriptions[index]);
                intent.putExtra("animal", animalTypes[index]);
                startActivity(intent);
            });
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
