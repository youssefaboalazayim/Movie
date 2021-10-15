package com.example.movieapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    //Attributes
    private String title;
    private String poster_path;
    private String release_date;
    private int id;
    private float vote_average;
    private String overview;
    private int runtime;

    // Constructor
    public Movie(String title, String poster_path, String release_date, int id, float vote_average, String overview
    , int runtime) {
        this.title = title;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.id = id;
        this.vote_average = vote_average;
        this.overview = overview;
        this.runtime = runtime;
    }

    protected Movie(Parcel in) {
        title = in.readString();
        poster_path = in.readString();
        release_date = in.readString();
        id = in.readInt();
        vote_average = in.readFloat();
        overview = in.readString();
        runtime = in.readInt();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    // Getter Methods
    public String getTitle() {
        return title;
    }
    public String getPoster_path() {
        return poster_path;
    }
    public String getRelease_date() {
        return release_date;
    }
    public int getId() {
        return id;
    }
    public float getVote_average() {
        return vote_average;
    }
    public String getOverview() {
        return overview;
    }
    public int getRuntime() {
        return runtime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(poster_path);
        dest.writeString(release_date);
        dest.writeInt(id);
        dest.writeFloat(vote_average);
        dest.writeString(overview);
    }
}
