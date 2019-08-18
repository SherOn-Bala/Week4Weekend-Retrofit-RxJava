
package ca.judacribz.week4weekend_retrofit_rxjava.model.weather;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rain implements Parcelable
{

    @SerializedName("1h")
    @Expose
    private Float _1h;
    public final static Creator<Rain> CREATOR = new Creator<Rain>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Rain createFromParcel(Parcel in) {
            return new Rain(in);
        }

        public Rain[] newArray(int size) {
            return (new Rain[size]);
        }

    }
    ;

    protected Rain(Parcel in) {
        this._1h = ((Float) in.readValue((Float.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Rain() {
    }

    /**
     * 
     * @param _1h
     */
    public Rain(Float _1h) {
        super();
        this._1h = _1h;
    }

    public Float get1h() {
        return _1h;
    }

    public void set1h(Float _1h) {
        this._1h = _1h;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(_1h);
    }

    public int describeContents() {
        return  0;
    }

}
