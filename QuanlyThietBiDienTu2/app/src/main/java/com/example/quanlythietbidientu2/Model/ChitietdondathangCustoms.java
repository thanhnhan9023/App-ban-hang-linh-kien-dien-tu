package com.example.quanlythietbidientu2.Model;

public class ChitietdondathangCustoms {


    private  String TenHH,Duongdan,MAHH,MaDH;
    float Giaban;
    int Soluong;
    double Thanhtien;

    public String getMAHH() {
        return MAHH;
    }

    public String getMaDH() {
        return MaDH;
    }

    public void setMAHH(String MAHH) {
        this.MAHH = MAHH;
    }

    public void setMaDH(String maDH) {
        MaDH = maDH;
    }

    public ChitietdondathangCustoms() {
    }

    public String getTenHH() {
        return TenHH;
    }

    public void setTenHH(String tenHH) {
        TenHH = tenHH;
    }

    public String getDuongdan() {
        return Duongdan;
    }

    public void setDuongdan(String duongdan) {
        Duongdan = duongdan;
    }

    public float getGiaban() {
        return Giaban;
    }

    public void setGiaban(float giaban) {
        Giaban = giaban;
    }

    public int getSoluong() {
        return Soluong;
    }

    public void setSoluong(int soluong) {
        Soluong = soluong;
    }

    public double getThanhtien() {
        return Thanhtien;
    }

    public void setThanhtien(double thanhtien) {
        Thanhtien = thanhtien;
    }
}
