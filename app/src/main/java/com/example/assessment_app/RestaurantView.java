package com.example.assessment_app;

import android.os.Parcel;
import android.os.Parcelable;

public class RestaurantView implements Parcelable {
    private int mImageResource;
    private String mRestaurantName;
    private String mRestaurantDesc;

    public RestaurantView(int imageResource, String restaurantName, String restaurantDesc) {
        mImageResource = imageResource;
        mRestaurantName = restaurantName;
        mRestaurantDesc = restaurantDesc;
    }

    protected RestaurantView(Parcel in) {
        mImageResource = in.readInt();
        mRestaurantName = in.readString();
        mRestaurantDesc = in.readString();
    }

    public static final Creator<RestaurantView> CREATOR = new Creator<RestaurantView>() {
        @Override
        public RestaurantView createFromParcel(Parcel in) {
            return new RestaurantView(in);
        }

        @Override
        public RestaurantView[] newArray(int size) {
            return new RestaurantView[size];
        }
    };

    public int getImageResource() {
        return mImageResource;
    }

    public String getRestaurantName() {
        return mRestaurantName;
    }

    public String getRestaurantDesc() {
        return mRestaurantDesc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mImageResource);
        parcel.writeString(mRestaurantName);
        parcel.writeString(mRestaurantDesc);
    }
}


