package com.example.assessment_app;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MealsFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private MealHistoryAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_meals, container, false);

        DatabaseAccess dbAccess = DatabaseAccess.getInstance(this.getContext());
        dbAccess.open();

        // Recycler view does not populate?

        mRecyclerView = view.findViewById(R.id.mealrecyclerview);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mAdapter = new MealHistoryAdapter(this.getActivity(), dbAccess.getAllItems());
        mRecyclerView.setAdapter(mAdapter);

        dbAccess.close();


        return inflater.inflate(R.layout.fragment_meals, container, false);
    }

}
