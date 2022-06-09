package com.rpl.reseppedia.ui.onboarding;

import android.os.Parcel;
import android.os.Parcelable;

public class obEntity implements Parcelable {

    private String title;
    private Integer illustration;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getIllustration() {
        return illustration;
    }

    public void setIllustration(int illustration) {
        this.illustration = illustration;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(this.title);
        parcel.writeInt(this.illustration);
    }

    public obEntity() {
    }

    private obEntity(Parcel in) {

        this.title = in.readString();
        this.illustration = in.readInt();
    }

    public static final Parcelable.Creator<obEntity> CREATOR = new Parcelable.Creator<obEntity>() {
        @Override
        public obEntity createFromParcel(Parcel source) {
            return new obEntity(source);
        }

        @Override
        public obEntity[] newArray(int size) {
            return new obEntity[size];
        }
    };
}
