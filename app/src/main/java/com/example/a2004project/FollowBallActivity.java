package com.example.a2004project;
import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FollowBallActivity extends AppCompatActivity {

    private ImageView ballImageView;
    private ValueAnimator valueAnimator;
    private TextView scoreTextView;
    private int scoreCounter = 0;
    private float resetY = 0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow_ball);

        ballImageView = findViewById(R.id.ballImageView);
        scoreTextView = findViewById(R.id.score_text_view);

        // Start the ball movement animation
        startBallAnimation();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // Check if the touch event occurred within the bounds of the ballImageView
            Rect rect = new Rect();
            ballImageView.getGlobalVisibleRect(rect);

            if (rect.contains((int) touchX, (int) touchY)) {
                // User touched the ball, increment the score counter
                scoreCounter++;
                updateScoreCounter();
                scoreTextView.setText("Score: " + scoreCounter);

                // Example: Show a toast message with the updated score
                Toast.makeText(this, "Successful touch!", Toast.LENGTH_SHORT).show();
            }
        }

        return super.onTouchEvent(event);
    }

    private void startBallAnimation() {
        int screenWidth = getResources().getDisplayMetrics().widthPixels;

        float ballHeight = ballImageView.getHeight();
        resetY += ballHeight * 1f;

        valueAnimator = ValueAnimator.ofFloat(0f, 1f);
        valueAnimator.setDuration(5000); // Set the duration of the animation (in milliseconds)
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setRepeatCount(0); // Do not repeat the animation

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float progress = (float) animation.getAnimatedValue();

                // Calculate the X position based on the progress and screen width
                float newX = (screenWidth - ballImageView.getWidth()) * progress;

                ballImageView.setTranslationX(newX);
            }
        });

        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                // Animation start callback
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                // Animation end callback
                ballImageView.setTranslationY(resetY);
                startBallAnimation(); // Start the animation again
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                // Animation cancellation callback
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                // Animation repeat callback, you can implement any logic here
                // Example: Change the direction of the ball movement
            }
        });

        valueAnimator.start();
    }

    private void updateScoreCounter() {
        // Update the UI to display the current score
        // Example: You can set a TextView with the score value
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        valueAnimator.cancel();
    }
}
