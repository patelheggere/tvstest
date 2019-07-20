package com.patelheggere.tvstest.model;

import java.util.ArrayList;
import java.util.List;
public class TABLE_DATA
{
    private List<List<String>> data;

    public void setData(List<List<String>> data){
        this.data = data;
    }
    public List<List<String>> getData(){
        return this.data;
    }
}

