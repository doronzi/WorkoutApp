package com.workout.workout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SetsAdapter extends ArrayAdapter<WorkoutSet> {
    private ArrayList<WorkoutSet> setsList;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtReps;
        TextView txtWeight;
        TextView txtRest;
        TextView txtRepTime;
    }

    public SetsAdapter(ArrayList<WorkoutSet> data, Context context) {
        super(context, R.layout.set_list_item, data);
        this.setsList = data;
        this.mContext=context;

    }



    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        WorkoutSet workoutSet = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            Log.d("mylog", "getView: "+ String.valueOf(getContext()));
            Log.d("mylog", "getView: "+ String.valueOf(mContext));
            convertView = inflater.inflate(R.layout.set_list_item, parent, false);
            viewHolder.txtReps = (TextView) convertView.findViewById(R.id.repsTxt);
            viewHolder.txtWeight = (TextView) convertView.findViewById(R.id.weightTxt);
            viewHolder.txtRest = (TextView) convertView.findViewById(R.id.restTime);
            viewHolder.txtRepTime = (TextView) convertView.findViewById(R.id.repTime);

//            result=convertView;
//
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

//        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
//        result.startAnimation(animation);
//        lastPosition = position;

        viewHolder.txtReps.setText("Reps: "+workoutSet.getReps());
        viewHolder.txtWeight.setText("Weight: "+String.valueOf(workoutSet.getWeight()));
        viewHolder.txtRest.setText("Rest Time: "+String.valueOf(workoutSet.getRest()));
        viewHolder.txtRepTime.setText("Average Rep Time: " + String.valueOf(workoutSet.getSetTime()));

        // Return the completed view to render on screen
        return convertView;
    }

}