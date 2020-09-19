package com.example.quanlythietbidientu2.Utils;


import android.widget.FrameLayout;

import com.example.quanlythietbidientu2.Model.KhachHang;
import com.example.quanlythietbidientu2.Model.LoaiProduct;
import com.example.quanlythietbidientu2.Model.SanPham;
import com.example.quanlythietbidientu2.Retrofit.ResultAPI;
import com.example.quanlythietbidientu2.Retrofit.RetrofitClient;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import Database.Model.GioHang;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class Common {
  private static FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();

    public static FirebaseAuth getFirebaseAuth() {
        return firebaseAuth;
    }

    public static void setFirebaseAuth(FirebaseAuth firebaseAuth) {
        Common.firebaseAuth = firebaseAuth;
    }

    public  static String getmail()
  {
  return  firebaseAuth.getCurrentUser().getEmail();
  }
    public static Boolean Kiemtrataikhoan()
    {
        if(firebaseAuth.getCurrentUser()!=null)
        {
            return  true;
        }
        else
            return  false;
    }



    public  static LoaiProduct currentLoaiProduct=null;
    public  static  SanPham curSanPhamID=null;
    public  static  String username="";
    public static String email="";

    public  static ArrayList<GioHang> currentgiohang=null;
    private  static  final String baseurrl="http://172.17.27.77:3000/";


    public  static ResultAPI getAPI()
    {
        return RetrofitClient.getClient(baseurrl).create(ResultAPI.class);
    }
}
