package com.example.quanlythietbidientu2.Model;

public class Banner {


    private String ID_banner,name,link;


    public Banner( )
    {

    }

    public Banner(String ID_banner, String name, String link) {
        this.ID_banner = ID_banner;
        this.name = name;
        this.link = link;
    }

    public String getID_banner() {
        return ID_banner;
    }

    public void setID_banner(String ID_banner) {
        this.ID_banner = ID_banner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
