package com.example.sqliteandmvvc2.ViewModel;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.OnLifecycleEvent;

import com.example.sqliteandmvvc2.employee;

import java.util.List;

public interface CommonViewModel extends LifecycleEventObserver {

    LiveData<List<employee>> getAllData();
    public void insertData(String name,String addr,String birth,String gender);
    void removeRecord(int id);
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void refreshdata();

}
