package com.example.onrequest;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class StartPoint extends AppCompatActivity {

    private ImageView restImageView;
    private ImageView endPointImageView;
    private ImageView movingImageView;
    ProgressBar progressBar;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_point);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        progressBar = findViewById(R.id.progress_bar);
        textView = findViewById(R.id.text_view);

        restImageView = findViewById(R.id.rest);
        endPointImageView = findViewById(R.id.end_point);
        movingImageView = findViewById(R.id.moving_image);

        progressBar.setMax(100);
        progressBar.setScaleY(3f);

        progressAnimation();
        moveAnimation();
    }

    public void progressAnimation() {
        DeliveryAnimation deliveryAnimation = new DeliveryAnimation(this, progressBar, textView, 0f, 100f);
        deliveryAnimation.setDuration(8000);
        progressBar.setAnimation(deliveryAnimation);
    }

    public void moveAnimation() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;

        float startX = 0; // Começa da esquerda
        float endX = screenWidth - movingImageView.getWidth(); // Termina na extremidade direita

        ObjectAnimator animator = ObjectAnimator.ofFloat(movingImageView, "translationX", startX, endX);
        animator.setDuration(8000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(ObjectAnimator.INFINITE);
        animator.start();
    }
}
