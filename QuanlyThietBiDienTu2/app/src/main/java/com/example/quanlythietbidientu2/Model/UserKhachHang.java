package com.example.quanlythietbidientu2.Model;

public class UserKhachHang {
    private  String Username,Password;
    private  String MaKH;

    public UserKhachHang() {
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String maKH) {
        MaKH = maKH;
    }
}
