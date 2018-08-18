package carlos.weatherapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import carlos.weatherapp.util.Utility;

public class Principal implements Parcelable {
    private double temp;
    private double temp_min;
    private double temp_max;

    Principal(String temperatura) {
        int parseTemp = Integer.parseInt(temperatura.substring(0, 2));
        temp = Utility.converterKelvinCelsius(parseTemp);
    }

    private Principal(Parcel in) {
        temp = in.readDouble();
        temp_min = in.readDouble();
        temp_max = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(temp);
        dest.writeDouble(temp_min);
        dest.writeDouble(temp_max);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Principal> CREATOR = new Creator<Principal>() {
        @Override
        public Principal createFromParcel(Parcel in) {
            return new Principal(in);
        }

        @Override
        public Principal[] newArray(int size) {
            return new Principal[size];
        }
    };

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(double temp_min) {
        this.temp_min = temp_min;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(double temp_max) {
        this.temp_max = temp_max;
    }
}
