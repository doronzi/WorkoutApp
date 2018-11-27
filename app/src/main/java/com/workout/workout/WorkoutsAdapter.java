package com.workout.workout;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class WorkoutsAdapter extends RecyclerView.Adapter<WorkoutsAdapter.MyViewHolder> {

    private List<Workout> workoutsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, date;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            date = (TextView) view.findViewById(R.id.date);


        }

    }


    public WorkoutsAdapter(List<Workout> workoutsList) {
        this.workoutsList = workoutsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.workout_item_recycle, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Workout workout = workoutsList.get(position);
        holder.title.setText(workout.getWoName());
        holder.date.setText(workout.getDate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context , ExistingWorkout.class);
                intent.putExtra("workout", workout);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return workoutsList.size();
    }


}
