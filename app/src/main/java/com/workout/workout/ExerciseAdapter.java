package com.workout.workout;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;


public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.MyViewHolder> {
    private static final String TAG = "MyLog";
    private List<Exercise> exercisesList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView title, exInfo;
        private LinearLayout subItem;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.item_title);
            exInfo = (TextView) view.findViewById(R.id.exInfo);
            subItem = (LinearLayout) view.findViewById(R.id.sub_item);

        }
        private void bind(Exercise ex) {
            // Get the state
            boolean expanded = ex.isExpanded();
            // Set the visibility based on state
            subItem.setVisibility(expanded ? View.VISIBLE : View.GONE);



            title.setText(ex.getExName());
            exInfo.setText(ex.toString());
//            TextView[] setsTV = new TextView[ex.getSets().length];
//            Log.i(TAG, "title set " + ex.getExName());
//            int i = 0;
//            for (WorkoutSet set : ex.getSets()) {
//                setsTV[i] = new TextView(context);
//                if (set!= null) {
//                    Log.i(TAG, set.toString());
//                    setsTV[i].setText(set.getReps() + " Reps of " + set.getWeight() + " kgs");
//                }
//                else
//                    setsTV[i].setText("0 Reps of 0 kgs");
//                subItem.addView(setsTV[i]);
//                i++;
//            }
        }
    }
    public ExerciseAdapter(List<Exercise> exercisesList) {
        this.exercisesList = exercisesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exercise_item_recycle, parent, false);
        this.context = parent.getContext();
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Exercise ex = exercisesList.get(position);
        final int pos = position;
        // Set movie data
        holder.bind(ex);

        holder.itemView.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                // Get the current state of the item
                boolean expanded = ex.isExpanded();
                // Change the state
                ex.setExpanded(!expanded);
                // Notify the adapter that item has changed
                notifyItemChanged(pos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return exercisesList.size();
    }

}
