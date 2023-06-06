package com.example.a2004project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PlaySpellingActivity extends AppCompatActivity {
    private TextView targetWordTextView;
    private EditText wordEditText;
    private Button checkButton;

    private Map<String, String> targetWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_spelling);

        targetWordTextView = findViewById(R.id.target_word_text_view);
        wordEditText = findViewById(R.id.word_edit_text);
        checkButton = findViewById(R.id.check_button);

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkSpelling();
            }
        });

        targetWords = new HashMap<>();
        targetWords.put("merhaba", "mer-ha-ba");
        targetWords.put("masa", "ma-sa");
        targetWords.put("kitap", "ki-tap");
        targetWords.put("defter", "def-ter");
        targetWords.put("olmak", "ol-mak");
        targetWords.put("mercimek", "mer-ci-mek");
        targetWords.put("tiyatro", "ti-yat-ro");
        targetWords.put("fasulye", "fa-sul-ye");

        setRandomTargetWord();
    }

    private void setRandomTargetWord() {
        String[] keys = targetWords.keySet().toArray(new String[0]);
        String randomWord = keys[new Random().nextInt(keys.length)];
        String targetSpelling = targetWords.get(randomWord);
        targetWordTextView.setText("Target Word: " + randomWord);
    }

    private void checkSpelling() {
        String targetWord = targetWordTextView.getText().toString().replace("Target Word:", "").trim();
        String targetSpelling = targetWords.get(targetWord);
        String userSpelling = wordEditText.getText().toString().trim();

        String[] targetSyllables = targetSpelling.split(",");
        String[] userSyllables = userSpelling.split(",");

        if (compareArrays(targetSyllables, userSyllables)) {
            showMessage("Correct spelling!");
        } else {
            showMessage("Incorrect spelling. Try again.");
        }

        wordEditText.getText().clear();
        setRandomTargetWord();
    }


    private boolean compareArrays(String[] array1, String[] array2) {
        if (array1.length != array2.length) {
            return false;
        }

        for (int i = 0; i < array1.length; i++) {
            if (!array1[i].trim().equals(array2[i].trim())) {
                return false;
            }
        }

        return true;
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
