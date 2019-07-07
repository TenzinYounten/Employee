package com.example.employee.EmployeeDetails;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.employee.Model.Employee;
import com.example.employee.R;

import java.util.ArrayList;
import java.util.List;

class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.EmployeeHolder> implements Filterable {
    Context context;
    List<Employee> employees;
    List<Employee> filteredEmployee;
    List<Employee> tempEmployee;
    private ItemClickListener clickListener;
    public interface ItemClickListener {
        void onClick(View view, int position);
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(Long id);
    }

    public DetailsAdapter(List<Employee> finalEmployees, Context applicationContext) {
        this.context = applicationContext;
        this.employees = finalEmployees;
        this.tempEmployee = finalEmployees;

    }

    @NonNull
    @Override
    public EmployeeHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.employee_rows, viewGroup, false);
        EmployeeHolder employeeHolder = new EmployeeHolder(v);
        return employeeHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeHolder viewHolder, int i) {
        viewHolder.name.setText("" + employees.get(i).getName());
        viewHolder.role.setText("" + employees.get(i).getRole());
        viewHolder.place.setText("" + employees.get(i).getPlace());
        viewHolder.salary.setText("" + employees.get(i).getSalary());
        viewHolder.date.setText("" + employees.get(i).getDate());
        viewHolder.id = employees.get(i).getId();
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {
                    employees = tempEmployee;
                    filteredEmployee = employees;
                } else {
                    ArrayList<Employee> tempEmployee = new ArrayList<Employee>();
                    for (Employee employee : employees) {
                        if (employee.getName().toLowerCase().contains(charString) ) {

                            tempEmployee.add(employee);
                        }
                    }

                    filteredEmployee = tempEmployee;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredEmployee;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                employees = (ArrayList<Employee>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }



    public class EmployeeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, role, place, salary, date;
        Button button;
        String id;


        public EmployeeHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            place = (TextView) itemView.findViewById(R.id.place);
            role = (TextView) itemView.findViewById(R.id.role);
            salary = (TextView) itemView.findViewById(R.id.salary);
            date = (TextView) itemView.findViewById(R.id.date);
            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if (clickListener != null) clickListener.onClick(v, getAdapterPosition());
        }
    }
}
