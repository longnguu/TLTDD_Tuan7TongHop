package com.example.tltdd_tuan7.Class;

public class Items {
    String ten,mota,gia;
    int image;

    public Items(String ten, String mota, String gia,int image) {
        this.ten = ten;
        this.mota = mota;
        this.gia = gia;
        this.image=image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int     image) {
        this.image = image;
    }

    public String getTen() {
        return ten;
    }

    public String getMota() {
        return mota;
    }

    public String getGia() {
        return gia;
    }

    public void setTen(String ten) {

        this.ten = ten;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }
}
