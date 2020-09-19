package Database.Model;

public class GioHang {

    private  int idgiohang;
    private  String tensp,idsp,linksp,username;
    private  float giasp;
    private  float thanhtien;
    private  int soluongsp;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public GioHang() {
    }

    public GioHang(int idgiohang, String tensp, String idsp, String linksp, float giasp, float thanhtien, int soluongsp,String username) {
        this.idgiohang = idgiohang;
        this.tensp = tensp;
        this.idsp = idsp;
        this.linksp = linksp;
        this.giasp = giasp;
        this.thanhtien = thanhtien;
        this.soluongsp = soluongsp;
        this.username=username;
    }

    public String getLinksp() {
        return linksp;
    }

    public void setLinksp(String linksp) {
        this.linksp = linksp;
    }

    public int getIdgiohang() {
        return idgiohang;
    }

    public void setIdgiohang(int idgiohang) {
        this.idgiohang = idgiohang;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getIdsp() {
        return idsp;
    }

    public void setIdsp(String idsp) {
        this.idsp = idsp;
    }

    public float getGiasp() {
        return giasp;
    }

    public void setGiasp(float giasp) {
        this.giasp = giasp;
    }

    public float getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(float thanhtien) {
        this.thanhtien = thanhtien;
    }

    public int getSoluongsp() {
        return soluongsp;
    }

    public void setSoluongsp(int soluongsp) {
        this.soluongsp = soluongsp;
    }
}
