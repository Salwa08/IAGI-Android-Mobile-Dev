package com.example.appiagi_26;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class App_Act_13 extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    int[] sounds = {
            R.raw.a1,
            R.raw.a2,
            R.raw.a3,
            R.raw.a4,
            R.raw.a5,
            R.raw.a6,
            R.raw.a7,
            R.raw.a8,
            R.raw.a9
    };

    int[] imageIds = {
            R.id.i1,
            R.id.i2,
            R.id.i3,
            R.id.i4,
            R.id.i5,
            R.id.i6,
            R.id.i7,
            R.id.i8,
            R.id.i9
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_act13);

        for (int i = 0; i < imageIds.length; i++) {

            final int index = i;

            ImageView imageView = findViewById(imageIds[i]);

            imageView.setOnClickListener(v -> {

                if (mediaPlayer != null) {
                    mediaPlayer.release();
                }

                mediaPlayer = MediaPlayer.create(
                        App_Act_13.this,
                        sounds[index]
                );

                mediaPlayer.start();
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }
}

