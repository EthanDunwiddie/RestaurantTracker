package com.example.assessment_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {
    private ArrayList<RestaurantView> mRestaurantList;
    private onItemClickListener mListener;

    public interface onItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        mListener = listener;
    }

    public static class RestaurantViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextViewName;
        public TextView mTextViewDesc;

        public RestaurantViewHolder(View itemView, final onItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextViewName = itemView.findViewById(R.id.textview_restaurant_name);
            mTextViewDesc = itemView.findViewById(R.id.textview_restaurant_desc);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAbsoluteAdapterPosition(); // getAdapterPosition is deprecated?
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public RestaurantAdapter(ArrayList<RestaurantView> restaurantList) {
        mRestaurantList = restaurantList;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_view, parent, false);
        RestaurantViewHolder rvh = new RestaurantViewHolder(v, mListener);
        return rvh;
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder holder, int position) {
        RestaurantView currentView = mRestaurantList.get(position);

        holder.mImageView.setImageResource(currentView.getImageResource());
        holder.mTextViewName.setText(currentView.getRestaurantName());
        holder.mTextViewDesc.setText(currentView.getRestaurantDesc());
    }

    @Override
    public int getItemCount() {
        return mRestaurantList.size();
    }
}
