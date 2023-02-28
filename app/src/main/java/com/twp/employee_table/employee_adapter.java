package com.twp.employee_table;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class employee_adapter extends RecyclerView.Adapter<employee_adapter.ViewHolder> {


    private employee[] localDataSet;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView code;
        private final TextView desig;
        private final TextView salary;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            name = (TextView) view.findViewById(R.id.name);
            code = (TextView) view.findViewById(R.id.code);
            desig = (TextView) view.findViewById(R.id.desig);
            salary = (TextView) view.findViewById(R.id.salary);
        }

        public TextView getName() {
            return name;
        }

        public TextView getcode() {
            return code;
        }

        public TextView getDesig() {
            return desig;
        }

        public TextView getSalary() {
            return salary;
        }
    }

    /**
     * Initialize the dataset of the Adapter
     *
     * @param dataSet String[] containing the data to populate views to be used
     *                by RecyclerView
     */
    public employee_adapter(employee[] dataSet) {
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.listview, viewGroup, false);

        return new ViewHolder(view);
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getName().setText(localDataSet[position].name);
        viewHolder.getcode().setText(String.valueOf(localDataSet[position].code));
        viewHolder.getDesig().setText(localDataSet[position].desgination);
        viewHolder.getSalary().setText(localDataSet[position].salary);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.length;
    }


}
