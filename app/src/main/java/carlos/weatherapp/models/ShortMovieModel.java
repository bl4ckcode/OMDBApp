package carlos.weatherapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ShortMovieModel implements Parcelable {
    private String title;
    private String year;
    @SerializedName("imdbID")
    private String imdbID;
    private String poster;

    public ShortMovieModel(String title, String year, String imdbID, String poster) {
        this.title = title;
        this.year = year;
        this.imdbID = imdbID;
        this.poster = poster;
    }

    private ShortMovieModel(Parcel in) {
        title = in.readString();
        year = in.readString();
        imdbID = in.readString();
        poster = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(year);
        dest.writeString(imdbID);
        dest.writeString(poster);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ShortMovieModel> CREATOR = new Creator<ShortMovieModel>() {
        @Override
        public ShortMovieModel createFromParcel(Parcel in) {
            return new ShortMovieModel(in);
        }

        @Override
        public ShortMovieModel[] newArray(int size) {
            return new ShortMovieModel[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
