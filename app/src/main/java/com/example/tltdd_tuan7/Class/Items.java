package com.example.tltdd_tuan7.Class;

public class Items {
    String ten,mota;
    int img;

    public Items(String ten, String mota, int img) {
        this.ten = ten;
        this.mota = mota;
        this.img = img;
    }
    public String getTen() {
        return ten;
    }

    public String getMota() {
        return mota;
    }

    public int getImg() {
        return img;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
