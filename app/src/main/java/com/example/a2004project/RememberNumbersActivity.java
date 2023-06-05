package com.example.a2004project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a2004project.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RememberNumbersActivity extends AppCompatActivity {

    private TextView numberText;
    private EditText numberInput;
    private Button startButton;
    private Button checkButton;
    private TextView resultText;

    private List<Integer> numbers;
    private int currentIndex;
    private boolean isGameStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remember_numbers);

        numberText = findViewById(R.id.numberText);
        numberInput = findViewById(R.id.numberInput);
        startButton = findViewById(R.id.startButton);
        checkButton = findViewById(R.id.checkButton);
        resultText = findViewById(R.id.resultText);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame();
            }
        });

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkInput();
            }
        });

        // Disable check button initially
        checkButton.setEnabled(false);

        isGameStarted = false;
        numbers = new ArrayList<>();
    }

    private void startGame() {
        numbers.clear();
        generateRandomNumbers(3); // Generate 5 random numbers

        currentIndex = 0;
        isGameStarted = true;
        startButton.setEnabled(false);
        checkButton.setEnabled(false);
        resultText.setVisibility(View.INVISIBLE);

        displayNumbers();
    }

    private void displayNumbers() {
        if (currentIndex < numbers.size()) {
            int number = numbers.get(currentIndex);
            currentIndex++;

            numberText.setText(String.valueOf(number));
            numberText.setVisibility(View.VISIBLE);

            // Delay the display of next number
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    numberText.setText("");
                    numberText.setVisibility(View.INVISIBLE);
                    displayNumbers();
                }
            }, 1000); // Display each number for 1 second
        } else {
            isGameStarted = false;
            startButton.setEnabled(true);
            checkButton.setEnabled(true);
            numberInput.setText("");
            resultText.setVisibility(View.VISIBLE);
            resultText.setText("Remember the numbers and enter them in the correct order.");
        }
    }

    private void checkInput() {
        String input = numberInput.getText().toString().trim();

        // Validate the input
        if (input.isEmpty()) {
            Toast.makeText(this, "Please enter the numbers in the correct order.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if the number of input numbers matches the generated numbers
        if (input.length() != numbers.size()) {
            Toast.makeText(this, "Incorrect number of input numbers.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Compare each input number with the corresponding generated number
        for (int i = 0; i < input.length(); i++) {
            int inputNumber;
            try {
                inputNumber = Integer.parseInt(String.valueOf(input.charAt(i)));
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid input format.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (inputNumber != numbers.get(i)) {
                Toast.makeText(this, "Incorrect input. Try again.", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        // All numbers are correct
        Toast.makeText(this, "Congratulations! You entered the numbers correctly.", Toast.LENGTH_SHORT).show();
    }


    private void generateRandomNumbers(int count) {
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            int number = random.nextInt(10); // Generate numbers between 0 and 9
            numbers.add(number);
        }
    }
}
