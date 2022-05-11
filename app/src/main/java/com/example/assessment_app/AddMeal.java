package com.example.assessment_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class AddMeal extends AppCompatActivity {


    TextView mDate, rateFlavour, ratePrice;
    Spinner selectItem;
    DatePickerDialog.OnDateSetListener setListener;

    JSONArray jsonArray = new JSONArray();
    RaitingsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);

        /* Brings up issues with fetching the correct restaurant information
        Toolbar toolbar = findViewById(R.id.details_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        */

        mDate = findViewById(R.id.date);
        selectItem = findViewById(R.id.item_select);
        rateFlavour = findViewById(R.id.rate_flavour);
        ratePrice = findViewById(R.id.rate_price);

        // Date
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        mDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AddMeal.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        , setListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String date = day+"/"+month+"/"+year;
                mDate.setText(date);
            }
        };

 /*       // Add meal
        selectItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseAccess dbAccess = DatabaseAccess.getInstance(getApplicationContext());
                dbAccess.open();

                //Cant get array of items
                String[] meals = dbAccess.getMeals();
                ArrayAdapter adapter = new ArrayAdapter(AddMeal.this, android.R.layout.simple_spinner_item, meals);
                adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
                selectItem.setAdapter(adapter);


                dbAccess.close();

            }
        });
*/

        adapter = new RaitingsAdapter(AddMeal.this, jsonArray);

        // Rate flavour
        rateFlavour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Bring up ratings menu
                Dialog dialog = new Dialog(AddMeal.this);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setContentView(R.layout.dialog_add_rating);
                dialog.show();

                RatingBar ratingBar = dialog.findViewById(R.id.rating_bar);
                TextView tvRating = dialog.findViewById(R.id.tv_rating);
                Button btnSubmit = dialog.findViewById(R.id.btn_submit);

                ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                        // Set selected rating on text view
                        tvRating.setText(String.format("(%s)", v));
                        rateFlavour.setText(String.format("(%s)", v));
                    }
                });

                btnSubmit.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        String sRating = String.valueOf(ratingBar.getRating());
                        try {
                            jsonArray.put(new JSONObject().put("rating", sRating));
                            dialog.dismiss();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

            }
        });

        // Rate price
        ratePrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Bring up ratings menu
                Dialog dialog = new Dialog(AddMeal.this);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setContentView(R.layout.dialog_add_rating);
                dialog.show();

                RatingBar ratingBar = dialog.findViewById(R.id.rating_bar);
                TextView tvRating = dialog.findViewById(R.id.tv_rating);
                Button btnSubmit = dialog.findViewById(R.id.btn_submit);

                ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                        // Set selected rating on text view
                        tvRating.setText(String.format("(%s)", v));
                        ratePrice.setText(String.format("(%s)", v));
                    }
                });

                btnSubmit.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        String sRating = String.valueOf(ratingBar.getRating());
                        try {
                            jsonArray.put(new JSONObject().put("rating", sRating));
                            dialog.dismiss();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

            }
        });

    }
}