package com.example.sqliteandmvvc2.Repository;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.sqliteandmvvc2.View.MyDBAdapter;
import com.example.sqliteandmvvc2.employee;

import java.util.ArrayList;
import java.util.List;

public class ListRepositoryImpl implements ListRepository {
        private Context context;
        private MutableLiveData<List<employee>> mutableListOfEmployee;
        private MyDBAdapter myDBAdapter;
        private static ListRepository listRepository=null;
        private List<employee> list;

   private ListRepositoryImpl()
   {
       myDBAdapter=MyDBAdapter.getInstance(context);
       mutableListOfEmployee=new MutableLiveData<>();
       mutableListOfEmployee.setValue(new ArrayList<>());
   }

    public static ListRepository getRepositoryInstance()
    {
        if(listRepository==null)
        {
            listRepository=new ListRepositoryImpl();
        }
        return listRepository;
    }
    @Override
    public MutableLiveData<List<employee>> getAllRecord() {

        list=myDBAdapter.getAllRecored();
        mutableListOfEmployee.setValue(list);
        return mutableListOfEmployee;
    }

    @Override
    public void addRecord(String name, String addr, String birth, String gender) {
                boolean res=myDBAdapter.insertRecord(name,addr,birth,gender);

                if(res)
                {
                    list=myDBAdapter.getAllRecored();
                    mutableListOfEmployee.setValue(list);
                }
    }

    @Override
    public void removeRecord(int id) {
               boolean res=myDBAdapter.remove(id);
               if (res)
               {
                   list=myDBAdapter.getAllRecored();
                   mutableListOfEmployee.setValue(list);
               }
               else {
                   //throw new ItemNotFount("somethig wrong");
                   Toast.makeText(context, "somethig wrong", Toast.LENGTH_SHORT).show();
               }
    }


}
