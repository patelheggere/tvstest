package com.patelheggere.tvstest.main;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.patelheggere.tvstest.R;
import com.patelheggere.tvstest.adapter.EmployeeDataAdapter;
import com.patelheggere.tvstest.base.BaseActivity;
import com.patelheggere.tvstest.chart.ChartActivity;
import com.patelheggere.tvstest.details.DetailsActivity;
import com.patelheggere.tvstest.map.MapActivity;
import com.patelheggere.tvstest.model.EmployeeModel;
import com.patelheggere.tvstest.utils.utilities;

import java.util.ArrayList;
import java.util.List;

import static com.patelheggere.tvstest.constants.StringConstants.CHART_TYPE;
import static com.patelheggere.tvstest.constants.StringConstants.DETAILS;
import static com.patelheggere.tvstest.constants.StringConstants.PASSWORD;
import static com.patelheggere.tvstest.constants.StringConstants.USERNAME;
import static com.patelheggere.tvstest.utils.utilities.anima;

public class ListActivity extends BaseActivity implements ListingView, EmployeeDataAdapter.SelectEmpployee{
    private static final String TAG = "ListActivity";
    private ListPresenter mMainPresenter;
    private String mUserName;
    private String mPassword;
    private ActionBar mActionBar;
    private RecyclerView mRecyclerViewDetails;
    private EmployeeDataAdapter mEmployeeDataAdapter;
    private ProgressBar mProgressBar;
    private SearchView searchView;
    private MenuItem searchViewItem;
    private ArrayList<EmployeeModel> masterList, mListTenEmpData;
    private Button mButtonBarChart, mButtonPieChart, mButtonMap;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mMainPresenter = new ListPresenter(this, this);
        mActionBar = getSupportActionBar();
        if(mActionBar!=null)
        {
            mActionBar.setTitle(getString(R.string.app_name));
        }
        mRecyclerViewDetails = findViewById(R.id.recyclerData);
        mProgressBar = findViewById(R.id.progressbar);
        mButtonBarChart = findViewById(R.id.buttonBarChart);
        mButtonPieChart = findViewById(R.id.buttonPieChart);
        mButtonMap = findViewById(R.id.buttonMap);
    }

    @Override
    protected void initData() {
        mUserName = getIntent().getStringExtra(USERNAME);
        mPassword = getIntent().getStringExtra(PASSWORD);
        if(utilities.checkInternetStatus(this)) {
            mMainPresenter.getEmployeeDetails(mUserName, mPassword);
        }
        else {
            networkErrorMessage();
        }
    }

    @Override
    protected void initListener() {
        mButtonBarChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               AnimatorSet animatorSet =  anima(v);
                animatorSet.start();
                animatorSet.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        Intent intent = new Intent(ListActivity.this, ChartActivity.class);
                        intent.putExtra(DETAILS, mListTenEmpData);
                        intent.putExtra(CHART_TYPE, true);
                        startActivity(intent);
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

        mButtonPieChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimatorSet animatorSet =  anima(v);
                animatorSet.start();
                animatorSet.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        Intent intent = new Intent(ListActivity.this, ChartActivity.class);
                        intent.putExtra(DETAILS, mListTenEmpData);
                        intent.putExtra(CHART_TYPE, false);
                        startActivity(intent);
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

        mButtonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimatorSet animatorSet =  anima(v);
                animatorSet.start();
                animatorSet.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        Intent intent = new Intent(ListActivity.this, MapActivity.class);
                        intent.putExtra(DETAILS, mListTenEmpData);
                        startActivity(intent);
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
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void networkErrorMessage() {
        Toast.makeText(this, getString(R.string.network_fail_msg), Toast.LENGTH_LONG).show();
    }

    @Override
    public void APIFailureMessage() {
        Toast.makeText(this, getString(R.string.api_fail_msg), Toast.LENGTH_LONG).show();
    }

    @Override
    public void updateEmployeeDetails(List<EmployeeModel> mEmployeeModelList) {
        mEmployeeDataAdapter = new EmployeeDataAdapter(this, mEmployeeModelList);
        mRecyclerViewDetails.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerViewDetails.setAdapter(mEmployeeDataAdapter);
    }
    @Override
    public void getOriginalData(List<EmployeeModel> mEmployeeModelList)
    {
        masterList = new ArrayList<>(mEmployeeModelList);
        mListTenEmpData = new ArrayList<>();
        for (int i = 0; i<9; i++)
        {
            mListTenEmpData.add(masterList.get(i));
        }
    }

    @Override
    public void selectedPositionData(EmployeeModel employeeModel) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(DETAILS, employeeModel);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.alert_menu, menu);
        searchViewItem = menu.findItem(R.id.action_search);
        searchViewItem.setVisible(true);
        searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);
        searchView.setQueryHint(getString(R.string.search_hint));
        searchView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!searchView.isFocused()){
                    searchViewItem.collapseActionView();
                    searchView.onActionViewCollapsed();
                }
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query.trim().length()==0){
                    updateEmployeeDetails(masterList);
                }
                else {
                    updateFilter(query.trim());
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.trim().length()==0){
                    updateEmployeeDetails(masterList);
                }
                else {
                    updateFilter(newText.trim());
                }
                return false;
            }
        });

        searchViewItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                updateEmployeeDetails(masterList);
                return true;
            }
        });

        return true;
    }

    private void updateFilter(String query) {
        if(masterList!=null && masterList.size()>0){
            List<EmployeeModel> filteredList = new ArrayList<EmployeeModel>();

            for(EmployeeModel employeeData : masterList)
                if(employeeData.getmName().toLowerCase().contains(query.toLowerCase())  || employeeData.getmDesignation().toLowerCase().contains(query.toLowerCase()) ){
                    filteredList.add(employeeData);
                }

            if(filteredList!=null && filteredList.size()>0){
                mRecyclerViewDetails.setVisibility(View.VISIBLE);
                updateEmployeeDetails(filteredList);
            }else{
                Toast.makeText(ListActivity.this,getString(R.string.no_data),Toast.LENGTH_LONG).show();
            }
        }
    }

}
