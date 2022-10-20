package com.example.tltdd_tuan7.Adapter;

public class SlideModel {
    int banner;
    byte[] anh;
    String chu;

    public SlideModel(int banner, byte[] anh, String chu) {
        this.banner = banner;
        this.anh = anh;
        this.chu = chu;
    }

    public byte[] getAnh() {
        return anh;
    }

    public void setAnh(byte[] anh) {
        this.anh = anh;
    }

    public String getChu() {
        return chu;
    }

    public void setChu(String chu) {
        this.chu = chu;
    }

    public SlideModel(int banner) {
        this.banner = banner;
    }

    public int getBanner() {
        return banner;
    }

    public void setBanner(int banner) {
        this.banner = banner;
    }
}
