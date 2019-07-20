package com.patelheggere.tvstest.splash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.patelheggere.tvstest.R;
import com.patelheggere.tvstest.base.BaseActivity;
import com.patelheggere.tvstest.constants.StringConstants;
import com.patelheggere.tvstest.login.LoginActivity;
import com.patelheggere.tvstest.main.ListActivity;

public class SplashActivity extends BaseActivity {
    private static final String TAG = "SplashActivity";
    private ImageView mImageViewLogo;
    private Animation animSlide;

    @Override
    protected int getContentView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        mImageViewLogo = findViewById(R.id.logo);
        animSlide = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide);
        mImageViewLogo.startAnimation(animSlide);
    }

    @Override
    protected void initData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }
        }, StringConstants.THREE_SECONDS);
    }

    @Override
    protected void initListener() {

    }
}
