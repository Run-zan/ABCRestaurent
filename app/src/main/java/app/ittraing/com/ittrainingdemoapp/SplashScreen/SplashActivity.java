package app.ittraing.com.ittrainingdemoapp.SplashScreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import app.ittraing.com.ittrainingdemoapp.Helper.Constants;
import app.ittraing.com.ittrainingdemoapp.Helper.GlobalState;
import app.ittraing.com.ittrainingdemoapp.LoginActivity;
import app.ittraing.com.ittrainingdemoapp.MainActivity;
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

                GlobalState state = GlobalState.singleton;

                if (state.getPrefsIsLoggedIn() != null && state.getPrefsIsLoggedIn().equals(Constants.STATE_TRUE)) {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }

                else{
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }
            }
        }, 2000);
    }
}


