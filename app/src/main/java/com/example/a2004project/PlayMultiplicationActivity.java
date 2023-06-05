package com.example.a2004project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class PlayMultiplicationActivity extends AppCompatActivity {
    private TextView questionTextView;
    private EditText answerEditText;
    private Button checkButton;
    private TextView resultTextView;

    private int firstNumber;
    private int secondNumber;
    private int correctAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_multiplication);

        questionTextView = findViewById(R.id.questionTextView);
        answerEditText = findViewById(R.id.answerEditText);
        checkButton = findViewById(R.id.checkButton);
        resultTextView = findViewById(R.id.resultTextView);

        generateQuestion();

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });
    }

    private void generateQuestion() {
        Random random = new Random();
        firstNumber = random.nextInt(10); // Generate a random number between 0 and 9
        secondNumber = random.nextInt(10);

        correctAnswer = firstNumber * secondNumber;

        questionTextView.setText(firstNumber + " * " + secondNumber + " = ?");
        answerEditText.setText("");
        resultTextView.setVisibility(View.INVISIBLE);
    }

    private void checkAnswer() {
        String answerString = answerEditText.getText().toString().trim();

        if (!answerString.isEmpty()) {
            int userAnswer = Integer.parseInt(answerString);

            if (userAnswer == correctAnswer) {
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show();
            }

            resultTextView.setVisibility(View.VISIBLE);
            resultTextView.setText("The correct answer is: " + correctAnswer);

            generateQuestion();
        } else {
            Toast.makeText(this, "Please enter an answer", Toast.LENGTH_SHORT).show();
        }
    }
}