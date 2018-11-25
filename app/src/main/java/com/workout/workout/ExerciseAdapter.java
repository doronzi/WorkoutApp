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
            subItem = (LinearLayout) view.findViewById(R.id.sub_item);

        }
        private void bind(Exercise ex) {
            // Get the state
            boolean expanded = ex.isExpanded();
            // Set the visibility based on state
            subItem.setVisibility(expanded ? View.VISIBLE : View.GONE);

            if (ex.getSetCounter() > 0 &&  !ex.getSets().get(ex.getSetCounter() - 1).isInitilized()) {
                Log.i(TAG, "bind: set Initialize:"+ String.valueOf(ex.getSets().get(ex.getSetCounter() - 1).isInitilized())+
                        "\nsetCounter: " + String.valueOf(ex.getSetCounter()));
                TextView tempTV = new TextView (context);
                tempTV.setText(ex.getSets().get(ex.getSetCounter() - 1).toString());
                Log.i(TAG, "bind: " + ex.getSets().get(ex.getSetCounter() - 1).toString());
                ex.getSets().get(ex.getSetCounter() - 1).setInitilized(true);
                subItem.addView(tempTV);
                subItem.invalidate();
                Log.i(TAG, "bind: " + String.valueOf(subItem.getChildCount()));
            }

            title.setText(ex.getExName());
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
