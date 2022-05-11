package com.example.assessment_app;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class Home extends Fragment {
    private SQLiteDatabase mDatabase;
    // private UserAdapter mAdapter;

    private ArrayList<RestaurantView> restaurantList;
    private RecyclerView mRecyclerView;
    private RestaurantAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    Functions fun = new Functions();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_home, container, false);

        restaurantList = new ArrayList<>();
        restaurantList.add(new RestaurantView(R.drawable.bills_header, "Bill’s Restaurant", "Bill’s York restaurant is open for breakfast, lunch, dinner and everything in between."));
        restaurantList.add(new RestaurantView(R.drawable.yuzu_header, "YUZU Street Food", "Taking inspiration from Japanase, Thai, Korean & Chinese cuisine to create incredible dishes with big flavour."));
        restaurantList.add(new RestaurantView(R.drawable.cosyclub_header, "Cosy Club", "Step inside to a generous bar area, sitting just in front of our ground floor restaurant, secluded and comfortable."));
        restaurantList.add(new RestaurantView(R.drawable.prezzo_header, "Prezzo Italian", "Dinner for one? Sofa date for two? Or a family feast? Are you ready for some delicious Italian Classics!"));
        restaurantList.add(new RestaurantView(R.drawable.castlegate_header_header, "31 Castlegate", "Our restaurant offers fine dining and contemporary modern European cuisine"));
        restaurantList.add(new RestaurantView(R.drawable.paradiso_header, "Il Paradiso Del Cibo", "Authentic cuisine with only the freshest ingredients, and the warmest of welcomes – just like any Italian would expect."));
        restaurantList.add(new RestaurantView(R.drawable.tapas_header, "Ambiente Tapas", "Discover our range of events at Ambiente, created to showcase the best of Spain’s food and drink offering."));
        restaurantList.add(new RestaurantView(R.drawable.byron_header, "Byron", "Disruptive, indulgent and fun, THAT’s what Byron is all about."));
        restaurantList.add(new RestaurantView(R.drawable.burgsys_header, "Burgsy's", "We are a small family-run restaurant serving gourmet burgers in York."));
        restaurantList.add(new RestaurantView(R.drawable.rustique_header, "Rustique", "Experience exquisite French dining in an upbeat Bistro atmosphere in the centre of York"));

        mRecyclerView = view.findViewById(R.id.restaurantrecyclerview);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mLayoutManager = new LinearLayoutManager(this.getContext()); // or getActivity()
        mAdapter = new RestaurantAdapter(restaurantList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new RestaurantAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), RestaurantDetails.class);
                intent.putExtra("Restaurant", restaurantList.get(position));

                startActivity(intent);
            }
        });

        return view;
    }
}
