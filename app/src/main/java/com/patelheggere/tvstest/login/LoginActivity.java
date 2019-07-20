package com.patelheggere.tvstest.login;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.patelheggere.tvstest.R;
import com.patelheggere.tvstest.base.BaseActivity;
import com.patelheggere.tvstest.main.ListActivity;
import com.patelheggere.tvstest.utils.utilities;

import static com.patelheggere.tvstest.constants.StringConstants.PASSWORD;
import static com.patelheggere.tvstest.constants.StringConstants.USERNAME;
import static com.patelheggere.tvstest.utils.utilities.anima;

public class LoginActivity extends BaseActivity implements LoginView{
    private static final String TAG = "LoginActivity";
    private Button mButtonLogin;
    private ProgressBar mProgressBar;
    private LoginPresenter mLoginPresenter;
    private EditText mEditTextUsername, mEditTextPassword;
    private ActionBar mActionBar;
    private Animation animSlide;
    private LinearLayout mLinearLayoutContainer;

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        animSlide = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide);
        mLinearLayoutContainer = findViewById(R.id.linearLayout);
        mLinearLayoutContainer.startAnimation(animSlide);
        mButtonLogin = findViewById(R.id.buttonLogin);
        mEditTextPassword = findViewById(R.id.password);
        mEditTextUsername = findViewById(R.id.username);
        mProgressBar = findViewById(R.id.progressbar);
    }

    @Override
    protected void initData() {
        mActionBar = getSupportActionBar();
        if(mActionBar!=null)
        {
            mActionBar.setTitle(getString(R.string.login));
        }
        mLoginPresenter = new LoginPresenter(this, this);
    }

    @Override
    protected void initListener() {
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimatorSet scaleDown =  anima(v);
                scaleDown.start();
                scaleDown.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if(utilities.checkInternetStatus(LoginActivity.this))
                        {
                            if(mEditTextPassword.getText()!=null && mEditTextUsername.getText()!=null && !mEditTextUsername.getText().toString().trim().isEmpty() && !mEditTextPassword.getText().toString().trim().isEmpty())
                            {
                                mLoginPresenter.validateCredentials(mEditTextUsername.getText().toString().trim(), mEditTextPassword.getText().toString().trim() );
                            }
                            else {
                                Toast.makeText(LoginActivity.this, getString(R.string.username_cnt_empty), Toast.LENGTH_LONG).show();
                            }
                        }
                        else {
                            networkError();
                        }
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });

            }
        });
    }

    @Override
    public void networkError() {
        Toast.makeText(this, getString(R.string.network_fail_msg), Toast.LENGTH_LONG).show();
    }

    @Override
    public void invalidCredentials() {
        Toast.makeText(this, getString(R.string.invalid_credentails), Toast.LENGTH_LONG).show();
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void errorMessage() {
        Toast.makeText(this, getString(R.string.somethin_wrong), Toast.LENGTH_LONG).show();

    }

    @Override
    public void loginSuccess() {
        Intent intent = new Intent(this, ListActivity.class);
        intent.putExtra(USERNAME, mEditTextUsername.getText().toString().trim());
        intent.putExtra(PASSWORD, mEditTextPassword.getText().toString().trim());
        startActivity(intent);
        finish();
    }
}
