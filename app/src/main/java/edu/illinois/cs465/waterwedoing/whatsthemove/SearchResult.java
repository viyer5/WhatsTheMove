package edu.illinois.cs465.waterwedoing.whatsthemove;

import android.os.Parcel;
import android.os.Parcelable;

public class SearchResult implements Parcelable {
    private String title;
    private String description;
    private int rating;
    private int time;
    private int cost;

    public SearchResult() {
    }

    public SearchResult(String title, String description, int rating, int time, int cost) {
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.time = time;
        this.cost = cost;
    }

    protected SearchResult(Parcel in) {
        title = in.readString();
        description = in.readString();
        rating = in.readInt();
        time = in.readInt();
        cost = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeInt(rating);
        dest.writeInt(time);
        dest.writeInt(cost);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SearchResult> CREATOR = new Creator<SearchResult>() {
        @Override
        public SearchResult createFromParcel(Parcel in) {
            return new SearchResult(in);
        }

        @Override
        public SearchResult[] newArray(int size) {
            return new SearchResult[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
