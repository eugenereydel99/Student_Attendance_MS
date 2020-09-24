package com.example.student_attendance_ms.network.model;

import android.os.Parcel;
import android.os.Parcelable;

public class AuthorizationResponse implements Parcelable{

    private String authToken;
    private Integer id;
    private String first_name;
    private String second_name;
    private String last_name;
    private String email;

    private AuthorizationResponse(Parcel in) {
        authToken = in.readString();
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        first_name = in.readString();
        second_name = in.readString();
        last_name = in.readString();
        email = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(authToken);
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(first_name);
        dest.writeString(second_name);
        dest.writeString(last_name);
        dest.writeString(email);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AuthorizationResponse> CREATOR = new Creator<AuthorizationResponse>() {
        @Override
        public AuthorizationResponse createFromParcel(Parcel in) {
            return new AuthorizationResponse(in);
        }

        @Override
        public AuthorizationResponse[] newArray(int size) {
            return new AuthorizationResponse[size];
        }
    };

    public String getAuthToken() {
        return authToken;
    }

    public Integer getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }
}
