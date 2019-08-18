
package ca.judacribz.week4weekend_retrofit_rxjava.model.weather;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coord implements Parcelable
{

    @SerializedName("lat")
    @Expose
    private Float lat;
    @SerializedName("lon")
    @Expose
    private Float lon;
    public final static Creator<Coord> CREATOR = new Creator<Coord>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Coord createFromParcel(Parcel in) {
            return new Coord(in);
        }

        public Coord[] newArray(int size) {
            return (new Coord[size]);
        }

    }
    ;

    protected Coord(Parcel in) {
        this.lat = ((Float) in.readValue((Float.class.getClassLoader())));
        this.lon = ((Float) in.readValue((Float.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Coord() {
    }

    /**
     * 
     * @param lon
     * @param lat
     */
    public Coord(Float lat, Float lon) {
        super();
        this.lat = lat;
        this.lon = lon;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLon() {
        return lon;
    }

    public void setLon(Float lon) {
        this.lon = lon;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(lat);
        dest.writeValue(lon);
    }

    public int describeContents() {
        return  0;
    }

}
