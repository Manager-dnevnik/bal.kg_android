package com.alanaandnazar.qrscanner.parent.shedule.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.alanaandnazar.qrscanner.R;
import com.alanaandnazar.qrscanner.model.Children;
import com.alanaandnazar.qrscanner.model.Subject;
import com.alanaandnazar.qrscanner.parent.detailSubject.SubjectActivity;

import java.util.List;


public class RVSubjectSheduleAdapter extends RecyclerView.Adapter<RVSubjectSheduleAdapter.PersonViewHolder> {

    Context context;
    Subject vse;

    public class PersonViewHolder extends RecyclerView.ViewHolder {

        TextView nameSubject, timeStart;

        PersonViewHolder(View itemView) {
            super(itemView);
            nameSubject = itemView.findViewById(R.id.tv_name_subject);
            timeStart = itemView.findViewById(R.id.tv_time_start);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent(context, SubjectActivity.class);
//                    vse = listVse.get(getAdapterPosition());
//                    intent.putExtra("id", id);
//                    intent.putExtra("subject_id", vse.getId());
//                    intent.putExtra("subject", vse.getName_subject());
//                    Activity activity = (Activity) context;
//                    activity.startActivity(intent);
//                    activity.overridePendingTransition(0, 0);
                }
            });

        }

    }

    public void removeAt(int position) {
        listVse.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, listVse.size());
    }

    List<Subject> listVse;
    int id;

    public RVSubjectSheduleAdapter(Context context, List<Subject> listVse, int id) {
        this.listVse = listVse;
        this.context = context;
        this.id = id;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_subject_shedule, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final PersonViewHolder personViewHolder, int i) {
        vse = new Subject();
        vse = listVse.get(i);

        personViewHolder.nameSubject.setText(vse.getName_subject());
        personViewHolder.timeStart.setText(vse.getTime_start());
    }

    @Override
    public int getItemCount() {
        return listVse.size();
    }

}