package com.patelheggere.tvstest.model;

import android.os.Parcel;
import android.os.Parcelable;

public class EmployeeModel implements Parcelable {
    private String mName;
    private String mSalary;
    private String mDOJ;
    private String mDesignation;
    private String mPlace;
    private String mEmpId;

    public EmployeeModel() {
    }

    public EmployeeModel(String mName, String mSalary, String mDOJ, String mDesignation, String mPlace, String mEmpId) {
        this.mName = mName;
        this.mSalary = mSalary;
        this.mDOJ = mDOJ;
        this.mDesignation = mDesignation;
        this.mPlace = mPlace;
        this.mEmpId = mEmpId;
    }

    protected EmployeeModel(Parcel in) {
        mName = in.readString();
        mSalary = in.readString();
        mDOJ = in.readString();
        mDesignation = in.readString();
        mPlace = in.readString();
        mEmpId = in.readString();
    }

    public static final Creator<EmployeeModel> CREATOR = new Creator<EmployeeModel>() {
        @Override
        public EmployeeModel createFromParcel(Parcel in) {
            return new EmployeeModel(in);
        }

        @Override
        public EmployeeModel[] newArray(int size) {
            return new EmployeeModel[size];
        }
    };

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmSalary() {
        return mSalary;
    }

    public void setmSalary(String mSalary) {
        this.mSalary = mSalary;
    }

    public String getmDOJ() {
        return mDOJ;
    }

    public void setmDOJ(String mDOJ) {
        this.mDOJ = mDOJ;
    }

    public String getmDesignation() {
        return mDesignation;
    }

    public void setmDesignation(String mDesignation) {
        this.mDesignation = mDesignation;
    }

    public String getmPlace() {
        return mPlace;
    }

    public void setmPlace(String mPlace) {
        this.mPlace = mPlace;
    }

    public String getmEmpId() {
        return mEmpId;
    }

    public void setmEmpId(String mEmpId) {
        this.mEmpId = mEmpId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeString(mSalary);
        dest.writeString(mDOJ);
        dest.writeString(mDesignation);
        dest.writeString(mPlace);
        dest.writeString(mEmpId);
    }
}
