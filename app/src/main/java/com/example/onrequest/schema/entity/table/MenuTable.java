package com.example.onrequest.schema.entity.table;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity
public class MenuTable implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private long menuTableId;
    private String logoUrl;
    public MenuTable(long menuTableId, String logoUrl) {
        this.menuTableId = menuTableId;
        this.logoUrl = logoUrl;
    }

    @Ignore
    protected MenuTable(Parcel in) {
        menuTableId = in.readLong();
        logoUrl = in.readString();
    }

    public static final Creator<MenuTable> CREATOR = new Creator<MenuTable>() {

        @Override
        public MenuTable createFromParcel(Parcel in) {
            return new MenuTable(in);
        }

        @Override
        public MenuTable[] newArray(int size) {
            return new MenuTable[size];
        }
    };


    public long getMenuTableId() {
        return menuTableId;
    }

    public void setMenuTableId(long menuTableId) {
        this.menuTableId = menuTableId;
    }

    public String getLogoUrl(){
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl){
        this.logoUrl =logoUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeLong(this.menuTableId);
        parcel.writeString(this.logoUrl);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuTable menuTable = (MenuTable) o;
        return menuTableId == menuTable.menuTableId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuTableId);
    }
}
