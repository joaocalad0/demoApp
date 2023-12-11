package com.example.onrequest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.webkit.WebView;
import android.widget.ImageView;

public class MapActivity extends AppCompatActivity {

    private  ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        ImageView imageView = findViewById(R.id.deliveryCar);

        float startPointX = 100f;
        float startPointY = 100f;
        float endPointX = 500f;
        float endPointY = 500f;

        // Crie uma animação de deslocamento
        Animation animation = new TranslateAnimation(startPointX, endPointX, startPointY, endPointY);
        animation.setDuration(5000); // Defina a duração da animação em milissegundos

        // Adicione um ouvinte de animação para executar ações após a conclusão da animação, se necessário
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // Ações ao iniciar a animação
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // Ações ao encerrar a animação
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // Ações ao repetir a animação
            }
        });

        // Inicie a animação
        imageView.startAnimation(animation);
    }

    }
