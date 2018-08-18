package carlos.weatherapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Carlos on 16/08/2018.
 */

public class Cidade implements Parcelable {
    private int id;
    private String name;
    private ArrayList<Clima> weather;
    private Principal main;

    public Cidade(int id, String name, String clima, String temperatura) {
        this.id = id;
        this.name = name;

        weather = new ArrayList<>();
        weather.add(new Clima(clima));
        main = new Principal(temperatura);
    }

    private Cidade(Parcel in) {
        id = in.readInt();
        name = in.readString();
        weather = in.createTypedArrayList(Clima.CREATOR);
        main = in.readParcelable(Principal.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeTypedList(weather);
        dest.writeParcelable(main, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Cidade> CREATOR = new Creator<Cidade>() {
        @Override
        public Cidade createFromParcel(Parcel in) {
            return new Cidade(in);
        }

        @Override
        public Cidade[] newArray(int size) {
            return new Cidade[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Clima> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<Clima> weather) {
        this.weather = weather;
    }

    public Principal getMain() {
        return main;
    }

    public void setMain(Principal main) {
        this.main = main;
    }
}
