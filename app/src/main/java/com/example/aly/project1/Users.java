package com.example.aly.project1;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by aly on 11/18/16.
 */

public class Users implements Parcelable{

    String email, uname, pass,profilepic;
    protected Users() {

    }

    protected Users(Parcel in) {
        email = in.readString();
        uname = in.readString();
        pass = in.readString();
        profilepic = in.readString();
    }

    public static final Creator<Users> CREATOR = new Creator<Users>() {
        @Override
        public Users createFromParcel(Parcel in) {
            return new Users(in);
        }

        @Override
        public Users[] newArray(int size) {
            return new Users[size];
        }
    };

    public void setEmail(String email)
    {
        this.email=email;

    }
    public String getEmail()
    {
        return this.email;

    }
    public void setUname(String uname)
    {
        this.uname=uname;

    }
    public String getUname()
    {
        return this.uname;

    }
    public void setPass(String pass)
    {
        this.pass=pass;

    }
    public String getPass()
    {
        return this.pass;

    }
    public void setProfilepic(String profilepic)
    {
        this.profilepic=profilepic;

    }
    public String getProfilepic()
    {
        return this.profilepic;

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(email);
        parcel.writeString(uname);
        parcel.writeString(pass);
        parcel.writeString(profilepic);
    }
}

