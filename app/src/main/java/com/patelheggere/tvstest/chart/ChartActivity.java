package com.patelheggere.tvstest.chart;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.patelheggere.tvstest.R;
import com.patelheggere.tvstest.base.BaseActivity;
import com.patelheggere.tvstest.constants.StringConstants;
import com.patelheggere.tvstest.model.EmployeeModel;

import java.util.ArrayList;
import java.util.List;

import static com.patelheggere.tvstest.constants.StringConstants.CHART_TYPE;

public class ChartActivity extends BaseActivity {
    private static final String TAG = "ChartActivity";
    private BarChart mBarChart;
    private PieChart mPieChart;
    private List<EmployeeModel> mEmployeeModelList;
    private boolean isBarChart;
    private ActionBar mActionBar;

    @Override
    protected int getContentView() {
        return R.layout.activity_chart;
    }

    @Override
    protected void initView() {
        mBarChart = findViewById(R.id.barchart);
        mPieChart = findViewById(R.id.pieChart);
    }

    @Override
    protected void initData() {
        mActionBar = getSupportActionBar();

        mEmployeeModelList = getIntent().getParcelableArrayListExtra(StringConstants.DETAILS);
        isBarChart = getIntent().getBooleanExtra(CHART_TYPE, true);
        if(isBarChart)
        {
            if(mActionBar!=null)
            {
                mActionBar.setTitle(getString(R.string.bar_chart));
            }
           setBarChart();
        }
        else {
            if(mActionBar!=null)
            {
                mActionBar.setTitle(getString(R.string.pie_chart));
            }
            setPieChart();
        }
        if(mActionBar!=null)
            mActionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void setBarChart()
    {
        ArrayList NoOfEmp = new ArrayList();

        ArrayList place = new ArrayList();

        for(int i = 0; i< mEmployeeModelList.size(); i++) {
            float sal = Float.parseFloat(mEmployeeModelList.get(i).getmSalary().replace("$","").replace(",",""))/10000f;
            NoOfEmp.add(new BarEntry(sal, i));
            place.add(mEmployeeModelList.get(i).getmPlace());
        }
        BarDataSet bardataset = new BarDataSet(NoOfEmp, StringConstants.CHART_LABEL);
        mBarChart.animateY(1000);
        BarData data = new BarData(bardataset);
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        mBarChart.setData(data);
        mPieChart.setVisibility(View.GONE);
        mBarChart.setVisibility(View.VISIBLE);

    }

    private void setPieChart() {
        mBarChart.setVisibility(View.GONE);
        mPieChart.setVisibility(View.VISIBLE);
        mPieChart.setCenterText(StringConstants.CHART_CENTER_TEXT);

        ArrayList NoOfEmp = new ArrayList();

        ArrayList place = new ArrayList();

        for(int i = 0; i< mEmployeeModelList.size(); i++) {
            float sal = Float.parseFloat(mEmployeeModelList.get(i).getmSalary().replace("$","").replace(",",""));
            NoOfEmp.add(new PieEntry(sal, i));
            place.add(mEmployeeModelList.get(i).getmPlace());
        }
        PieDataSet dataSet = new PieDataSet(NoOfEmp, "Salaries of 10 Employees");
        PieData data = new PieData(dataSet);
        mPieChart.setData(data);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        mPieChart.animateXY(1000, 1000);
    }

    @Override
    protected void initListener() {

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
