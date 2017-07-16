package app.ittraing.com.ittrainingdemoapp.SplashScreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import app.ittraing.com.ittrainingdemoapp.LoginActivity;
import app.ittraing.com.ittrainingdemoapp.R;


/**
 * Created by ranja_000 on 6/11/2017.
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            }
        }, 3000);
    }
}


