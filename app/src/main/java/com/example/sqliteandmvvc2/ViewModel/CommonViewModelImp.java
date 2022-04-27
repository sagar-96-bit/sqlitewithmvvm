package com.example.sqliteandmvvc2.ViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sqliteandmvvc2.Repository.ListRepository;
import com.example.sqliteandmvvc2.Repository.ListRepositoryImpl;
import com.example.sqliteandmvvc2.employee;

import java.util.ArrayList;
import java.util.List;

public class CommonViewModelImp extends ViewModel implements CommonViewModel {

    private ListRepository listRepository;
    private MutableLiveData<List<employee>> listMutableLiveData;

    public CommonViewModelImp() {
        listRepository =ListRepositoryImpl.getRepositoryInstance();
        listMutableLiveData = new MutableLiveData<>();
        try {
            listMutableLiveData = listRepository.getAllRecord();
        } catch (Exception e){
        listMutableLiveData.setValue(new ArrayList<>());
    }

}
    @Override
    public LiveData<List<employee>> getAllData() {
        return listMutableLiveData;
    }

    @Override
    public void insertData(String name, String addr, String birth, String gender) {
               listRepository.addRecord(name,addr,birth,gender);
               listMutableLiveData.setValue(listRepository.getAllRecord().getValue());

     }

    @Override
    public void removeRecord(int id) {
             listRepository.removeRecord(id);
    }

    @Override
    public void refreshdata() {

    }
    public LiveData<List<employee>> getEmployeeList()
    {
        return listMutableLiveData;
    }

    @Override
    public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
               listMutableLiveData.setValue(listRepository.getAllRecord().getValue());
    }
}
