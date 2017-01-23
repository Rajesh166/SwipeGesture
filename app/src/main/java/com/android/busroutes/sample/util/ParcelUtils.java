package com.android.busroutes.sample.util;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class ParcelUtils {

	private ParcelUtils(){}

	public static void writeString(Parcel dest, String value) {
        dest.writeInt(value == null ? 0 : 1);
        if (value != null) {
            dest.writeString(value);
        }
    }

    public static String readString(Parcel in) {
        boolean hasValue = in.readInt() == 1;
        return hasValue ? in.readString() : null;
    }
	
	public static void writeInteger(Parcel dest, Integer value) {
		dest.writeInt(value == null ? 0 : 1);
		if (value != null) {
			dest.writeInt(value);
		}
	}
	
	public static Integer readInteger(Parcel in){
		boolean hasValue = in.readInt() == 1;
		return hasValue ? in.readInt() : null;
	}
	
	public static void writeBoolean(Parcel dest, Boolean value) {
		dest.writeInt(value == null ? 0 : 1);
		if (value != null) {
			dest.writeInt(value ? 1 : 0);
		}
	}
	
	public static Boolean readBoolean(Parcel in){
		boolean hasValue = in.readInt() == 1;
		return hasValue ? (in.readInt() == 1 ? true : false) : null;
	}

    public static <T extends Parcelable> void writeTypedList(Parcel dest, List<T> value) {
        dest.writeInt(value == null ? -1 : value.size());
        if (value != null && !value.isEmpty()) {
            dest.writeTypedList(value);
        }
    }

    public static <T> void readTypedList(Parcel in, List<T> outVal, Parcelable.Creator<T> creator) {
        int size = in.readInt();
        if (size > 0) {
            in.readTypedList(outVal, creator);
        }
    }
}
