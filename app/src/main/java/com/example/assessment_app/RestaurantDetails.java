package com.example.assessment_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RestaurantDetails extends AppCompatActivity {
    public ImageView restaurantImage;
    public TextView restaurantName;
    public TextView restaurantDesc;
    private RecyclerView mRecyclerView;
    private MealHistoryAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);

        Toolbar toolbar = findViewById(R.id.details_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        RestaurantView restaurantView = intent.getParcelableExtra("Restaurant");

        int headerImage = restaurantView.getImageResource();
        String headerTitle = restaurantView.getRestaurantName();
        String shortDesc = restaurantView.getRestaurantDesc();

        restaurantImage = findViewById(R.id.image_header);
        restaurantImage.setImageResource(headerImage);

        restaurantName = findViewById(R.id.name_header);
        restaurantName.setText(headerTitle);

        restaurantDesc = findViewById(R.id.details_descrption);
        restaurantDesc.setText(shortDesc);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_meal_btn, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_meal:
                Intent intent = new Intent(getApplicationContext(), AddMeal.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}