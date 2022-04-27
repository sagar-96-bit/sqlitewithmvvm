package com.example.sqliteandmvvc2.Repository;

import androidx.lifecycle.MutableLiveData;

import com.example.sqliteandmvvc2.employee;

import java.util.List;

public interface ListRepository {
 public MutableLiveData<List<employee>> getAllRecord();
public void addRecord(String name,String addr,String birth,String gender);
public void removeRecord(int id);

}
