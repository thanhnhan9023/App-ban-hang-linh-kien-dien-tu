package com.example.quanlythietbidientu2.Model;

import java.util.Date;

public class DonHang {

    private  String MaDH,MaKH,Tinhtrang;
    private Date Ngaydathang,Ngaythanhtoan;
    private  long Tongtien;


    public DonHang() {
    }

    public String getMaDH() {
        return MaDH;
    }

    public void setMaDH(String maDH) {
        MaDH = maDH;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String maKH) {
        MaKH = maKH;
    }

    public String getTinhtrang() {
        return Tinhtrang;
    }

    public void setTinhtrang(String tinhtrang) {
        Tinhtrang = tinhtrang;
    }

    public Date getNgaydathang() {
        return Ngaydathang;
    }

    public void setNgaydathang(Date ngaydathang) {
        Ngaydathang = ngaydathang;
    }

    public Date getNgaythanhtoan() {
        return Ngaythanhtoan;
    }

    public void setNgaythanhtoan(Date ngaythanhtoan) {
        Ngaythanhtoan = ngaythanhtoan;
    }

    public long getTongtien() {
        return Tongtien;
    }

    public void setTongtien(long tongtien) {
        Tongtien = tongtien;
    }
}
