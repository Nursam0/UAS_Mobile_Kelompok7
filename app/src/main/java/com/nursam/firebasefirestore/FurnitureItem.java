package com.nursam.firebasefirestore;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class FurnitureItem implements Parcelable {
    private String nama;
    private String deskripsi;
    private Integer foto;


    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public void setFoto(Integer foto) {
        this.foto = foto;
    }


    public String getNama() {
        return nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public Integer getFoto() {
        return foto;
    }
    FurnitureItem(){

    }
    private FurnitureItem(Parcel in) {
        this.nama = in.readString();
        this.foto = in.readInt();
        this.deskripsi = in.readString();
    }
    public static final Creator<FurnitureItem> CREATOR = new Creator<FurnitureItem>() {
        @Override
        public FurnitureItem createFromParcel(Parcel in) {
            return new FurnitureItem(in);
        }

        @Override
        public FurnitureItem[] newArray(int size) {
            return new FurnitureItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(nama);
        dest.writeString(deskripsi);
        dest.writeInt(foto);
    }
}
