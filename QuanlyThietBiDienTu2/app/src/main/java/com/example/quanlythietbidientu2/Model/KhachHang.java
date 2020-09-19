package com.example.quanlythietbidientu2.Model;

import java.util.Date;

public class KhachHang {
    private   String MaKH,TenKH,DiaChi,SDT,GioiTinh,Maloaikh;
    private Date Ngaysinh;

    public KhachHang() {
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String maKH) {
        MaKH = maKH;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String tenKH) {
        TenKH = tenKH;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        GioiTinh = gioiTinh;
    }

    public String getMaloaikh() {
        return Maloaikh;
    }

    public void setMaloaikh(String maloaikh) {
        Maloaikh = maloaikh;
    }

    public Date getNgaysinh() {
        return Ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        Ngaysinh = ngaysinh;
    }
}
