package com.example.quanlythietbidientu2.Retrofit;

import com.example.quanlythietbidientu2.Model.Banner;
import com.example.quanlythietbidientu2.Model.Chitietdondathang;
import com.example.quanlythietbidientu2.Model.ChitietdondathangCustoms;
import com.example.quanlythietbidientu2.Model.DonHang;
import com.example.quanlythietbidientu2.Model.KhachHang;
import com.example.quanlythietbidientu2.Model.LoaiProduct;
import com.example.quanlythietbidientu2.Model.SanPham;
import com.example.quanlythietbidientu2.Model.UserKhachHang;

import java.io.ObjectInput;
import java.util.Date;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.internal.operators.observable.ObservableElementAt;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ResultAPI {




    //get banner baner quang cao
    @GET("Banner")
   Observable<List<Banner>> getBanner();


    //    //getloaisp // lay ra loai hang
    @GET("loaiHang")
    Observable<List<LoaiProduct>> getLoaisp();

    // lay ra san pham theo loai hang
    @GET("sanphamTheoLoaisp")
    Observable<List<SanPham>> getSanPham(@Query("IDLoaiHang") String id);

    @GET("sanphamTheoID")
    Observable<List<SanPham>> getSanPhamID(@Query("IdHH") String idsanpham);


    @GET("getMakhtheouser")
    Observable<List<KhachHang>> getMakhtheouser(@Query("userkhachhang") String userkhachang); // lấy  mã kh


    @GET("getMadhtheomakh")
    Observable<List<DonHang>> getMakhtheouser2(@Query("makh") String userkhachang); // lấy mã đh

    @GET("getchitietdonhangtheouser") // lấy chitietdonhang
    Observable<List<ChitietdondathangCustoms>>  getcchitietdonhangtheouser(@Query("username") String userkhachang);


    @GET("getdonhangxacnhan")
    Observable<List<ChitietdondathangCustoms>>  getcchitietgiaohang(@Query("username") String userkhachang);




 //getAllSanPham
         @GET("sanphamALL")
        Observable<List<SanPham>> getAllSanpham();

    @GET("sanphammoinhat")
    Observable<List<SanPham>> getsanphammoi();




    @FormUrlEncoded
    @POST("themuser")
    Call<UserKhachHang> saveuserkhachhang(@Field("username") String username
            ,@Field("password")String  password, @Field("Makh")String makh);


    @FormUrlEncoded
    @POST("xoa1chitietdonhang")
    Call<ChitietdondathangCustoms> xoa1sanphamchitiet(@Field("madh") String madh
            ,@Field("mahh")String  mahh);


    @FormUrlEncoded
    @POST("them1donhang")
    Call<DonHang> them1donhang(
            @Field("madh") String madh,
            @Field("makh")String  makh,
            @Field("ngaydathang")Date ngaydathang,
            @Field("tongtien") long tongtien,
                @Field("ngaythanhtoan") Date ngaythanhtoan,
            @Field("Tinhtrang") String tinhtrang
    );

        @FormUrlEncoded
        @POST("them1chietdonhang")
        Call<Chitietdondathang> them1chitietdonhang(
                @Field("madh") String madh,
                @Field("mahh")String  mahh,
                @Field("soluong")int soluong,
                @Field("giaban") float giaban,
                @Field("thanhtien") double thanhtien

        );





 @FormUrlEncoded
    @POST("updatethongtinkhachhang")
    Call<KhachHang> savethongtinkhachang(@Field("makh")String makh
                , @Field("tenkh")String  tenkh,
                                         @Field("ngaysinh")Date ngaysinh,
                                         @Field("diachi")String diachi,
                                         @Field("sdt")String sdt,
                                         @Field("gioitinh") String gioitinh,
                                         @Field("maloaikh") String maloaikh
    );





}
