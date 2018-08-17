package carlos.weatherapp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Clima implements Parcelable {
    private String main;
    private String description;

    private Clima(Parcel in) {
        main = in.readString();
        description = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(main);
        dest.writeString(description);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Clima> CREATOR = new Creator<Clima>() {
        @Override
        public Clima createFromParcel(Parcel in) {
            return new Clima(in);
        }

        @Override
        public Clima[] newArray(int size) {
            return new Clima[size];
        }
    };

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
