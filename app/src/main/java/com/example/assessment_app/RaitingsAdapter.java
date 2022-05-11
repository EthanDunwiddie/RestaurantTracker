package com.example.assessment_app;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RaitingsAdapter extends RecyclerView.Adapter<RaitingsAdapter.ViewHolder> {
    Activity activity;
    JSONArray jsonArray;

    public RaitingsAdapter(Activity activity, JSONArray jsonArray){
        this.activity = activity;
        this.jsonArray = jsonArray;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog_add_rating, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            JSONObject object = jsonArray.getJSONObject(position);
            String sRating = object.getString("rating");
            holder.tvRating.setText(sRating);
            holder.ratingBar.setRating(Float.parseFloat(sRating));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return jsonArray.length();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        // Initialize variable
        TextView tvRating;
        RatingBar ratingBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Assign variable
            tvRating = itemView.findViewById(R.id.tv_rating);
            ratingBar = itemView.findViewById(R.id.rating_bar);
        }
    }
}
