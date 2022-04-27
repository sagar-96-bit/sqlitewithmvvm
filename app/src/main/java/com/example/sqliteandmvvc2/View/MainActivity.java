package com.example.sqliteandmvvc2.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sqliteandmvvc2.R;
import com.example.sqliteandmvvc2.ViewModel.CommonViewModel;
import com.example.sqliteandmvvc2.ViewModel.CommonViewModelImp;
import com.example.sqliteandmvvc2.employee;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    MyDBAdapter myDBAdapter;
   EditText name,Addr,birthdate;
   Button btnadd,btnModify;
  TextView textShowRecord;
  RadioGroup radioGroup;
  RadioButton radioButton;
   List<employee> list=new ArrayList<>();
   CustomListAdapter customListAdapter;
   RecyclerView recyclerView;
   CommonViewModelImp commonViewModelimp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         myDBAdapter=MyDBAdapter.getInstance(this);

        btnadd=findViewById(R.id.add);
        btnadd.setOnClickListener(this);
        commonViewModelimp=new ViewModelProvider(this).get(CommonViewModelImp.class);
        getLifecycle().addObserver(commonViewModelimp);
        //initializeRecyclerList();
        commonViewModelimp.getAllData().observe(this, new Observer<List<employee>>() {
            @Override
            public void onChanged(List<employee> employees) {

                setNewList(employees);
            }
        });


//        list=myDBAdapter.getAllRecored();
//        if(list.size()==0)
//        {
//            Toast.makeText(this, "emty list", Toast.LENGTH_SHORT).show();
//        }
//
//        else
//        {
//            initializeRecyclerList();
//        }



    }

    private void initializeRecyclerList() {

        recyclerView=findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
         customListAdapter=new CustomListAdapter(this,new ArrayList<>());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(customListAdapter);
    }

    private void setNewList(List<employee> employees) {
        recyclerView=findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
          customListAdapter=new CustomListAdapter(this,employees);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(customListAdapter);
    }

    @Override
    public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.add:
                          addRecord();

            }
    }



    private void addRecord() {
        name=findViewById(R.id.textname);
        Addr=findViewById(R.id.textaddr);
        birthdate=findViewById(R.id.textBirthDate);
        radioGroup=findViewById(R.id.radioGroup);
      int selected=radioGroup.getCheckedRadioButtonId();
         radioButton=findViewById(selected);
         String gender=radioButton.getText().toString();
           //myDBAdapter.insertRecord(name.getText().toString(),Addr.getText().toString(),birthdate.getText().toString(),gender);
        commonViewModelimp.insertData(name.getText().toString(),Addr.getText().toString(),birthdate.getText().toString(),gender);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        commonViewModelimp.getAllData().removeObservers(this);
        getLifecycle().removeObserver(commonViewModelimp);
    }
    //    private void getAllRecord() {
//        list=new ArrayList<>();
//        list=myDBAdapter.getAllRecored();
//        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
//        customListAdapter=new CustomListAdapter(this,list);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setAdapter(customListAdapter);
//    }


}