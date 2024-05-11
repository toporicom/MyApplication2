package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.net.URI;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Animation animBtn, animTv;
    private Button startButton;
    private TextView textViewFirst;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        startButton = findViewById(R.id.btnStartActivity);
        textViewFirst = findViewById(R.id.tvText);
        textViewResult = findViewById(R.id.tvResultText);
        animTv = AnimationUtils.loadAnimation(this, R.anim.tv_animationin);
        animTv.setAnimationListener(animationFadeInListenerForTv);
        animBtn = AnimationUtils.loadAnimation(this, R.anim.tv_animationin);
        animBtn.setAnimationListener(animationFadeInListenerForBtn);
        startButton.setOnClickListener(this);
        textViewFirst.startAnimation(animTv);

    }

    Animation.AnimationListener animationFadeInListenerForTv = new Animation.AnimationListener() {
        @Override
        public void onAnimationEnd(Animation animation) {
            startButton.startAnimation(animBtn);
            textViewFirst.clearAnimation();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationStart(Animation animation) {
            textViewFirst.setVisibility(View.VISIBLE);
        }
    };
    Animation.AnimationListener animationFadeInListenerForBtn = new Animation.AnimationListener() {
        @Override
        public void onAnimationEnd(Animation animation) {
            startButton.clearAnimation();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationStart(Animation animation) {
            startButton.setVisibility(View.VISIBLE);
        }
    };

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnStartActivity) {
            Uri uriLocation = Uri.parse("geo:55.763435, 37.562386");
            Intent newIntent = new Intent(Intent.ACTION_VIEW);
            newIntent.setData(uriLocation);
            Intent intent = new Intent(this, CalculatorActivity.class);
            activityResultLauncher.launch(intent);
        }
    }

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getData() != null) {
                        textViewFirst.setVisibility(View.GONE);
                        startButton.setText("Ещё раз?   ");
                        double res = result.getData().getDoubleExtra("data", 0);
                        Log.d("simple_app_tag", "result" + res);
                        textViewResult.setEnabled(true);
                        textViewResult.setText("Ответ: " + res);
                    }
                }
            }
    );
}