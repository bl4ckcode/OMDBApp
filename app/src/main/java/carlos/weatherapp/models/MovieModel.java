package carlos.weatherapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class MovieModel implements Parcelable {
    @SerializedName("imdbID")
    private String imdbID;
    private String title;
    private int year;
    private String released;
    private String runtime;
    private String genre;
    private String director;
    private String actors;
    private String plot;
    private String awards;
    private String poster;

    public MovieModel(String imdbID, String title, int year, String released, String runtime, String genre, String director, String actors, String plot, String awards, String poster) {
        this.imdbID = imdbID;
        this.title = title;
        this.year = year;
        this.released = released;
        this.runtime = runtime;
        this.genre = genre;
        this.director = director;
        this.actors = actors;
        this.plot = plot;
        this.awards = awards;
        this.poster = poster;
    }

    private MovieModel(Parcel in) {
        imdbID = in.readString();
        title = in.readString();
        year = in.readInt();
        released = in.readString();
        runtime = in.readString();
        genre = in.readString();
        director = in.readString();
        actors = in.readString();
        plot = in.readString();
        awards = in.readString();
        poster = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imdbID);
        dest.writeString(title);
        dest.writeInt(year);
        dest.writeString(released);
        dest.writeString(runtime);
        dest.writeString(genre);
        dest.writeString(director);
        dest.writeString(actors);
        dest.writeString(plot);
        dest.writeString(awards);
        dest.writeString(poster);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
