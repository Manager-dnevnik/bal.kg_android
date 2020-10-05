package com.alanaandnazar.qrscanner.teacher.children;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.alanaandnazar.qrscanner.R;
import com.alanaandnazar.qrscanner.model.Student;

import java.util.List;


public class ChildrenAdapter extends RecyclerView.Adapter<ChildrenAdapter.OrderViewHolder> {
    private List<Student> orders;
    private OnOrderListener listener;


    public ChildrenAdapter(OnOrderListener listener) {
        this.listener = listener;
    }

    public void removeItem(int position) {
        orders.remove(position);
        notifyItemRemoved(position);
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        final OrderViewHolder holder = new OrderViewHolder(itemView);
        holder.itemView.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                listener.onOrderClick(orders.get(pos), pos);
            }
        });


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Student student = orders.get(position);
        holder.tv_title.setText(student.getFio());
        holder.spinner.setSelection(student.getMark_position());

        holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                String mark = holder.spinner.getSelectedItem().toString();
                student.setMark(mark);
                student.setMark_position(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

    }

    @Override
    public int getItemCount() {
        return orders == null ? 0 : orders.size();
    }

    public void updateItems(List<Student> list) {
        orders = list;
        notifyDataSetChanged();
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title;
        Context context;
        Spinner spinner;

        public OrderViewHolder(View view) {
            super(view);
            context = view.getContext();
            tv_title = view.findViewById(R.id.tv_title);
            spinner = itemView.findViewById(R.id.spinner);

            spinner.setVisibility(View.VISIBLE);
            String[] marks = {"", "5", "4", "3", "2", "н"};

            final ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(context, R.layout.spiener_item, marks);
            dataAdapter.setDropDownViewResource(R.layout.spiener_dropdown2);
            spinner.setAdapter(dataAdapter);

        }
    }

    public interface OnOrderListener {
        void onOrderClick(Student student, int position);
    }
}