package com.example.appiagi_26;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class App_Act12_new extends AppCompatActivity {

    VideoView videoResult;
    TextView txtDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_app_act12_new);

        videoResult = findViewById(R.id.videoResult);
        txtDetails = findViewById(R.id.txtDetails);

        Intent intent = getIntent();
        String detailText = intent.getStringExtra("desc");
        String animal = intent.getStringExtra("animal");

        if (detailText != null) {
            txtDetails.setText(detailText);
        }

        if (animal != null) {
            int videoRes = 0;
            switch (animal) {
                case "Chat":
                    videoRes = R.raw.chat;
                    break;
                case "Chien":
                    videoRes = R.raw.chien;
                    break;
                case "Pinguin":
                    videoRes = R.raw.pinguin;
                    break;
            }

            if (videoRes != 0) {
                Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + videoRes);
                videoResult.setVideoURI(uri);

                MediaController mediaController = new MediaController(this);
                videoResult.setMediaController(mediaController);
                mediaController.setAnchorView(videoResult);

                videoResult.start();
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
