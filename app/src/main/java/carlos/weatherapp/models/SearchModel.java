package carlos.weatherapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchModel implements Parcelable {
    private List<ShortMovieModel> search;
    @SerializedName("totalResults")
    private int totalResults;
    private Boolean response;

    public SearchModel(List<ShortMovieModel> search, int totalResults, Boolean response) {
        this.search = search;
        this.totalResults = totalResults;
        this.response = response;
    }

    private SearchModel(Parcel in) {
        search = in.createTypedArrayList(ShortMovieModel.CREATOR);
        totalResults = in.readInt();
        byte tmpResponse = in.readByte();
        response = tmpResponse == 0 ? null : tmpResponse == 1;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(search);
        dest.writeInt(totalResults);
        dest.writeByte((byte) (response == null ? 0 : response ? 1 : 2));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SearchModel> CREATOR = new Creator<SearchModel>() {
        @Override
        public SearchModel createFromParcel(Parcel in) {
            return new SearchModel(in);
        }

        @Override
        public SearchModel[] newArray(int size) {
            return new SearchModel[size];
        }
    };

    public List<ShortMovieModel> getSearch() {
        return search;
    }

    public void setSearch(List<ShortMovieModel> search) {
        this.search = search;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public Boolean getResponse() {
        return response;
    }

    public void setResponse(Boolean response) {
        this.response = response;
    }
}
