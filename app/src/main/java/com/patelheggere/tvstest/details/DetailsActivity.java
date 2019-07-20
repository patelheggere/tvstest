package com.patelheggere.tvstest.details;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.patelheggere.tvstest.R;
import com.patelheggere.tvstest.base.BaseActivity;
import com.patelheggere.tvstest.model.EmployeeModel;
import com.patelheggere.tvstest.utils.utilities;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import static com.patelheggere.tvstest.constants.StringConstants.DETAILS;
import static com.patelheggere.tvstest.utils.utilities.anima;

public class DetailsActivity extends BaseActivity {
    private static final String TAG = "DetailsActivity";
    private EmployeeModel mEmployeeModel;
    private Button mButtonCapture;
    private ImageView mImageViewCapture;
    private LinearLayout mLinearLayoutImageLayout;
    private TextView mTextViewTimeStamp;
    private TextView mTextViewName, mTextViewDesgn, mTextViewCity, mTextViewSalary, mTextViewDoj, mTextViewEmpID;
    int PERMISSION_ALL = 1;
    private ActionBar mActionBar;

    private static final int REQUEST_CAMERA = 1200;
    String[] PERMISSIONS = {
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };
    @Override
    protected int getContentView() {
        return R.layout.activity_details;
    }

    @Override
    protected void initView() {
        mButtonCapture = findViewById(R.id.buttonCapture);
        mLinearLayoutImageLayout = findViewById(R.id.imageLayout);
        mImageViewCapture = findViewById(R.id.CaptureImageView);
        mTextViewName = findViewById(R.id.textView_name_value);
        mTextViewDesgn = findViewById(R.id.textView_designation_value);
        mTextViewEmpID = findViewById(R.id.textView_id_value);
        mTextViewCity = findViewById(R.id.textview_place_value);
        mTextViewSalary = findViewById(R.id.textView_salary_value);
        mTextViewDoj = findViewById(R.id.textView_doj_value);
        mTextViewTimeStamp = findViewById(R.id.timeStamp);
    }

    @Override
    protected void initData() {
        mActionBar = getSupportActionBar();
        if(mActionBar!=null)
        {
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setTitle(getString(R.string.details));
        }
        mEmployeeModel = getIntent().getParcelableExtra(DETAILS);
        mTextViewName.setText(mEmployeeModel.getmName());
        mTextViewEmpID.setText(mEmployeeModel.getmEmpId());
        mTextViewDesgn.setText(mEmployeeModel.getmDesignation());
        mTextViewCity.setText(mEmployeeModel.getmPlace());
        mTextViewDoj.setText(mEmployeeModel.getmDOJ());
        mTextViewSalary.setText(mEmployeeModel.getmSalary());
;    }

    @Override
    protected void initListener() {
        mButtonCapture.setOnClickListener(new View.OnClickListener() {
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
                        if (!utilities.hasPermissions(DetailsActivity.this, PERMISSIONS)) {
                            ActivityCompat.requestPermissions(DetailsActivity.this, PERMISSIONS, PERMISSION_ALL);
                        }
                        else{
                            cameraIntent();
                        }                    }

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
    private void cameraIntent() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }
    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        if(thumbnail!=null)
        {
            mLinearLayoutImageLayout.setVisibility(View.VISIBLE);
            mImageViewCapture.setImageBitmap(thumbnail);
            long date = System.currentTimeMillis();

            SimpleDateFormat sdf = new SimpleDateFormat(" dd/MM/yyyy h:mm:s a");
            String dateString = sdf.format(date);
            mTextViewTimeStamp.setText(dateString);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
