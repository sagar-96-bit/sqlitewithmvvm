package com.example.sqliteandmvvc2.View;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sqliteandmvvc2.employee;

import java.util.ArrayList;
import java.util.List;

public class MyDBAdapter {

    private static final String DBName = "UserDatabase.db";
    private static final int DBVersion = 1;
    private static final String TableName = "MYEmployee";
    private static final String Column1 = "Emp_Id";
    private static final String Column2 = "Emp_Name";
    private static final String Column3 = "Emp_Addr";
    private static final String Column4 = "Emp_Birth";
    private static final String Column5 = "Emp_Gender";


    private  static String createTable = "create table "+TableName+"("+Column1+" INTEGER PRIMARY KEY, "+Column2+" TEXT not null, "+Column3+" TEXT NOT NULL, "+Column4+" TEXT NOT NULL, "+Column5+" TEXT NOT NULL)";

    private Context context;
    private SQLiteDatabase sqLiteDatabase;
    private static MyDBAdapter myDBAdapter;

    private MyDBAdapter(Context context)
    {
          this.context=context;
          sqLiteDatabase=new MySqlOpenHelper(context,DBName,null,DBVersion).getWritableDatabase();

    }


    public boolean insertRecord(String name,String addr,String birth,String gender)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(Column2,name);
        contentValues.put(Column3,addr);
        contentValues.put(Column4,birth);
        contentValues.put(Column5,gender);

        long result=sqLiteDatabase.insert(TableName,null,contentValues);
        if(result>-1)
            return true;
        else
            return false;

    }

    public List<employee> getAllRecored() {
                    List<employee> list=new ArrayList<>();
        Cursor res= sqLiteDatabase.query(TableName, null, null,null,null,null,null);
            if(res.getCount()>0)
            {
                while (res.moveToNext())
                {
                      employee e=new employee(res.getInt(0),res.getString(1),res.getString(2),res.getString(3),res.getString(4));
                       list.add(e);
                }
            }
            return list;
    }

    public boolean remove(int id)
    {
          int res=sqLiteDatabase.delete(TableName,Column1+"="+id,null);
          if(res==0)
              return false;
          else
              return true;
    }

     public static MyDBAdapter getInstance(Context context){
              if (myDBAdapter==null)
              {
                  myDBAdapter=new MyDBAdapter(context);
              }
              return myDBAdapter;
    }

    private static class MySqlOpenHelper extends SQLiteOpenHelper {

        public MySqlOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }


        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
              sqLiteDatabase.execSQL(createTable);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}