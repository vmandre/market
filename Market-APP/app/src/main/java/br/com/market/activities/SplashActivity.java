package br.com.market.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import org.androidannotations.annotations.EActivity;

import java.util.Timer;
import java.util.TimerTask;

import br.com.market.R;

@EActivity(R.layout.activity_splash)
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_splash);


        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {

                finish();

                Intent intent = new Intent();
                intent.setClass(SplashActivity.this, LoginActivity_.class);
                startActivity(intent);
            }
        }, 1000);

    }

}
