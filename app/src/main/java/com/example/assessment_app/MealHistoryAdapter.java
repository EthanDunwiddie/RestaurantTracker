package com.example.assessment_app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.assessment_app.UserContract.*;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MealHistoryAdapter extends RecyclerView.Adapter<MealHistoryAdapter.MealHistoryViewHolder>{
    private Context mContext;
    private Cursor mCursor;

    public MealHistoryAdapter(Context context, Cursor cursor){
        mContext = context;
        mCursor = cursor;
    }

    public class MealHistoryViewHolder extends RecyclerView.ViewHolder{
        public TextView mMeal;
        public TextView mFlavour;
        public TextView mPrice;
        public TextView mAverage;

        public MealHistoryViewHolder(@NonNull View itemView) {
            super(itemView);

            mMeal = itemView.findViewById(R.id.textview_meal);
            mFlavour = itemView.findViewById(R.id.meal_flavour_score);
            mPrice = itemView.findViewById(R.id.meal_price_score);
            mAverage = itemView.findViewById(R.id.meal_average_score);
        }
    }

    @NonNull
    @Override
    public MealHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.meal_view, parent, false);
        return new MealHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealHistoryViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position)){
            return;
        }

        @SuppressLint("Range") String meal = mCursor.getString(mCursor.getColumnIndex(MealEntry.COLUMN_MEAL));
        @SuppressLint("Range") String flavour = mCursor.getString(mCursor.getColumnIndex(MealEntry.COLUMN_FLAVOUR));
        @SuppressLint("Range") String price = mCursor.getString(mCursor.getColumnIndex(MealEntry.COLUMN_PRICE));
        @SuppressLint("Range") String average = mCursor.getString(mCursor.getColumnIndex(MealEntry.COLUMN_AVERAGE));

        holder.mMeal.setText(meal);
        holder.mFlavour.setText(flavour);
        holder.mPrice.setText(price);
        holder.mAverage.setText(average);

    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        if(mCursor != null){
            mCursor.close();
        }

        mCursor = newCursor;

        if(newCursor != null){
            notifyDataSetChanged();
        }
    }

}
