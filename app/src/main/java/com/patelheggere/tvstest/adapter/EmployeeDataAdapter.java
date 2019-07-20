package com.patelheggere.tvstest.adapter;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.patelheggere.tvstest.R;
import com.patelheggere.tvstest.model.EmployeeModel;

import java.util.List;

import static com.patelheggere.tvstest.utils.utilities.anima;

public class EmployeeDataAdapter extends RecyclerView.Adapter<EmployeeDataAdapter.EmpViewHolder> {

    private static final String TAG = "EmployeeDataAdapter";
    private Context mContext;
    private List<EmployeeModel> mEmployeeModelList;
    private SelectEmpployee mListener;

    public EmployeeDataAdapter(Context mContext, List<EmployeeModel> mEmployeeModelList) {
        this.mEmployeeModelList = mEmployeeModelList;
        this.mContext = mContext;
        this.mListener = (SelectEmpployee)mContext;
    }

    @NonNull
    @Override
    public EmpViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.employee_item_row, viewGroup, false);
        return new EmpViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final EmpViewHolder empViewHolder, int i) {
        EmployeeModel model = mEmployeeModelList.get(i);
        if(model!=null)
        {
            if(model.getmName()!=null)
                empViewHolder.mTextViewName.setText(model.getmName());

            if(model.getmEmpId()!=null)
                empViewHolder.mTextViewEmpID.setText(model.getmEmpId());

            if(model.getmDesignation()!=null)
                empViewHolder.mTextViewDesgn.setText(model.getmDesignation());

            if(model.getmDOJ()!=null)
                empViewHolder.mTextViewDoj.setText(model.getmDOJ());

            if(model.getmPlace()!=null)
                empViewHolder.mTextViewCity.setText(model.getmPlace());

            if(model.getmSalary()!=null)
                empViewHolder.mTextViewSalary.setText(model.getmSalary());
        }
        empViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
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
                        mListener.selectedPositionData(mEmployeeModelList.get(empViewHolder.getAdapterPosition()));
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
    public int getItemCount() {
        return mEmployeeModelList.size();
    }

    public class EmpViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextViewName, mTextViewDesgn, mTextViewCity, mTextViewSalary, mTextViewDoj, mTextViewEmpID;
        private ImageView mImageViewPhoto;

        public EmpViewHolder(@NonNull View mView) {
            super(mView);
            mTextViewName = mView.findViewById(R.id.textView_name_value);
            mTextViewDesgn = mView.findViewById(R.id.textView_designation_value);
            mTextViewEmpID = mView.findViewById(R.id.textView_id_value);
            mTextViewCity = mView.findViewById(R.id.textview_place_value);
            mTextViewSalary = mView.findViewById(R.id.textView_salary_value);
            mTextViewDoj = mView.findViewById(R.id.textView_doj_value);
        }
    }
    public interface SelectEmpployee{
        void selectedPositionData(EmployeeModel employeeModel);
    }
}
