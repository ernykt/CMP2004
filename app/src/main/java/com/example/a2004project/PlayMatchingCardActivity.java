package com.example.a2004project;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.a2004project.R;

public class PlayMatchingCardActivity extends AppCompatActivity {

    private GridLayout gridLayout;
    private ImageView previousImageView;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching_game);

        gridLayout = findViewById(R.id.gridLayout);
        previousImageView = null;
    }

    public void onImageClick(View view) {
        ImageView currentImageView = (ImageView) view;

        // Check if the clicked image is already invisible
        if (currentImageView.getVisibility() == View.INVISIBLE) {
            return;
        }

        // Check if there is a previously clicked image
        if (previousImageView != null) {
            // Check if the clicked image has the same tag as the previous one
            if (currentImageView.getTag().equals(previousImageView.getTag())) {
                // Make both images invisible
                currentImageView.setVisibility(View.INVISIBLE);
                previousImageView.setVisibility(View.INVISIBLE);
                score += 10;

                // Check if all images are now invisible
                if (areAllImagesInvisible()) {
                    showCongratsToast(score);
                }

                // Reset the previously clicked image
                previousImageView = null;
            } else {
                // Reset the previously clicked image
                previousImageView.setSelected(false);
                previousImageView = currentImageView;
                previousImageView.setSelected(true);
            }
        } else {
            // Set the current image as the previously clicked image
            previousImageView = currentImageView;
            previousImageView.setSelected(true);
        }
    }

    private boolean areAllImagesInvisible() {
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            View child = gridLayout.getChildAt(i);
            if (child instanceof ImageView && child.getVisibility() != View.INVISIBLE) {
                return false;
            }
        }
        return true;
    }

    private void showCongratsToast(int score) {
        String message = "Congratulations! All images are invisible. Your score is " + score + ".";
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
