package GUI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.quanlythietbidientu2.Adapter.GioHangAdapter;
import com.example.quanlythietbidientu2.Adapter.SanPhamHomeAdapter;
import com.example.quanlythietbidientu2.Model.SanPham;
import com.example.quanlythietbidientu2.R;
import com.example.quanlythietbidientu2.Retrofit.ResultAPI;
import com.example.quanlythietbidientu2.Utils.Common;
import com.google.firebase.auth.FirebaseAuth;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import Database.Database.DBHelper;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class GioHang extends AppCompatActivity {

    CompositeDisposable compositeDisposable;
    DBHelper dbHelper;
    ResultAPI mService;
    ElegantNumberButton elegantNumberButton;
    TextView txtTongthanhtien,txtgiohangrong;
    Button btnxoagiohang,btnMuahang;
    GioHangAdapter sanPhamHomeAdapter;
 float thanhtien=0;
    private RecyclerView recyclerViewgh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);


        dbHelper = new DBHelper(getApplicationContext());
        compositeDisposable = new CompositeDisposable();
        mService = Common.getAPI();
        btnMuahang=findViewById(R.id.btnMuahang);
        btnxoagiohang = findViewById(R.id.btnXoaGioHang);
//        elegantNumberButton=findViewById(R.id.txtCountGioHang);
//            elegantNumberButton.setEnabled(false);
        recyclerViewgh = findViewById(R.id.recgiohang);
        txtTongthanhtien = findViewById(R.id.txtTongTien);
        txtgiohangrong = findViewById(R.id.txtGiohangRong);
        recyclerViewgh.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayout.VERTICAL, false));
        recyclerViewgh.setHasFixedSize(true);
        dbHelper.createTable();
        ArrayList<Database.Model.GioHang> gioHangs = new ArrayList<>();

        if (Common.getFirebaseAuth().getCurrentUser()!=null) {
          //  Toast.makeText(this, ""+Common.getmail(), Toast.LENGTH_SHORT).show();
            gioHangs = dbHelper.getALLGioHang(Common.getmail());
           // Log.d("vaolan1","vvv");
            Common.currentgiohang=gioHangs;
            //gioHangs = dbHelper.getALLGioHang(firebaseAuth.getCurrentUser().getEmail());
           // gioHangs = dbHelper.getALLGioHang("");

            Common.currentgiohang=gioHangs;
        }
        else
        {
            Log.d("kiemtraemail","vao roi");
            gioHangs = dbHelper.getALLGioHang("");
            Common.currentgiohang=gioHangs;
            //gioHangs = dbHelper.getALLGioHang("");
        }

//        Log.d("sql",dbHelper.getALLGioHang().get(0).getIdsp());
         sanPhamHomeAdapter = new GioHangAdapter(this,gioHangs);
        recyclerViewgh.setAdapter(sanPhamHomeAdapter);
//        ArrayList<Database.Model.GioHang> gioHangs=new ArrayList<>();
//        gioHangs=dbHelper.getALLGioHang();
        for (int i=0;i<gioHangs.size();i++)
        {
                thanhtien=thanhtien+gioHangs.get(i).getThanhtien();

        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        long thanhtientotal=(long)thanhtien;
        txtTongthanhtien.setText(decimalFormat.format(thanhtientotal)+" đ");
     //   long thanhtientotal=(long)thanhtien;
       // txtTongthanhtien.setText(thanhtientotal+" VNĐ");


     //   getsanphammoinhat();
        btnxoagiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("kiem tra email",Common.getmail());
                dbHelper.xoa1giohang(Common.getmail());

                sanPhamHomeAdapter.notifyDataSetChanged();
                txtgiohangrong.setVisibility(View.VISIBLE);
                startActivity(new Intent(getApplicationContext(),GioHang.class));
                finish();
                txtgiohangrong.setVisibility(View.VISIBLE);

               // txtgiohangrong.setVisibility(View.VISIBLE);

            }
        });
        btnMuahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GioHang.this,DathangActivity.class));

            }
        });
    }

//    private void getsanphammoinhat() {
//        compositeDisposable.add(mService.getsanphammoi().
//                subscribeOn(Schedulers.io()).
//                observeOn(AndroidSchedulers.mainThread()
//                ).subscribe(new Consumer<List<SanPham>>() {
//
//
//            @Override
//            public void accept(List<SanPham> sanPhams) throws Exception {
//                displayLoaisp(sanPhams);
//            }
//
//            private void displayLoaisp(List<SanPham> sanPhams) {
//
//               GioHangAdapter sanPhamHomeAdapter = new GioHangAdapter(getApplicationContext(),Common.currentgiohang);
//
//
//                recyclerViewgh.setAdapter(sanPhamHomeAdapter);
//
//                //    Toast.makeText(getContext(),sanPhams.get(0).getLinkSP()+"", Toast.LENGTH_SHORT).show();
//            }
//        }));

   // }
}