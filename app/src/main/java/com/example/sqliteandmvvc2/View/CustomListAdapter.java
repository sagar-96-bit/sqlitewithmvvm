package com.example.sqliteandmvvc2.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.sqliteandmvvc2.R;
import com.example.sqliteandmvvc2.employee;

import java.util.List;

public class CustomListAdapter extends RecyclerView.Adapter<CustomListAdapter.ViewHolder> {
    private Context context;
    private List<employee> employeeList;
    MyDBAdapter myDBAdapter;

    public CustomListAdapter(Context context, List<employee> employeeList) {
        this.context = context;
        this.employeeList = employeeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.customlist,parent,false);

         ViewHolder holder=new ViewHolder(view);
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                          int pos=holder.getAdapterPosition();
                          employee e=employeeList.get(pos);
                          int id=e.getId();
                          employeeList.remove(pos);
                          myDBAdapter=MyDBAdapter.getInstance(context.getApplicationContext());
                          boolean res=myDBAdapter.remove(id);
                          if (res)
                              notifyDataSetChanged();
                          else
                              Toast.makeText(context.getApplicationContext(), "not deleted", Toast.LENGTH_SHORT).show();

                return false;
            }
        });




         return holder;
     }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                  employee emp=employeeList.get(position);
                 holder.name.setText(emp.getEmpName());
                 holder.addr.setText(emp.getAddr());
                 holder.birthdate.setText(emp.getBirhtDate());
                 holder.gender.setText(emp.getGender());



    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView addr;
        private TextView birthdate;
       private TextView gender;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.cust_name);
            addr=itemView.findViewById(R.id.cust_addr);
            birthdate=itemView.findViewById(R.id.cust_birth);
            gender=itemView.findViewById(R.id.cust_gender);

        }
    }
}
