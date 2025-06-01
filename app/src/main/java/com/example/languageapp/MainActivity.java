package com.example.languageapp;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize TextToSpeech
        textToSpeech = new TextToSpeech(this, status -> {
            if(status == TextToSpeech.SUCCESS) {
                textToSpeech.setLanguage(Locale.FRENCH);
            }
        });

        // Find buttons
        Button whiteBtn = findViewById(R.id.button);
        Button blackBtn = findViewById(R.id.button2);
        Button greenBtn = findViewById(R.id.button3);
        Button blueBtn = findViewById(R.id.button4);

        whiteBtn.setOnClickListener(v -> speak("blanc"));
        blackBtn.setOnClickListener(v -> speak("noir"));
        greenBtn.setOnClickListener(v -> speak("vert"));
        blueBtn.setOnClickListener(v -> speak("bleu"));

    }

    private void speak(String text) {
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    @Override
    protected void onDestroy() {
        if(textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }
}