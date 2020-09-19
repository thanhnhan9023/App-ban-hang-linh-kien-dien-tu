package GUI;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlythietbidientu2.Adapter.DatHangAdapter;
import com.example.quanlythietbidientu2.Model.Chitietdondathang;
import com.example.quanlythietbidientu2.Model.DonHang;
import com.example.quanlythietbidientu2.Model.KhachHang;
import com.example.quanlythietbidientu2.R;
import com.example.quanlythietbidientu2.Retrofit.ResultAPI;
import com.example.quanlythietbidientu2.Utils.Common;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Database.Database.DBHelper;
import Database.Model.GioHang;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DathangActivity extends AppCompatActivity {


    RecyclerView recDathang;
    DBHelper dbHelper;
    TextView txtTongtiensp,txtTongtiendat;
    Button btnDathang;
    ResultAPI mResultAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    String makh,madh;
    long tongtien;
    boolean co=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dathang);
        dbHelper=new DBHelper(getApplicationContext());
        txtTongtiensp=findViewById(R.id.txtTongtiensp);
        txtTongtiendat=findViewById(R.id.txtTongTienThanhToandat);
        btnDathang=findViewById(R.id.btndathang);
        mResultAPI=Common.getAPI();

        ArrayList<GioHang> lst=new ArrayList<>();
        if(Common.getFirebaseAuth().getCurrentUser()!=null)
        {
            lst=dbHelper.getALLGioHang(Common.getmail());
            tongtien=0;
            for (int i=0;i<lst.size();i++) {
                tongtien+=lst.get(i).getThanhtien();
            }
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

            txtTongtiensp.setText(decimalFormat.format(tongtien)+" VNĐ");
            txtTongtiendat.setText(decimalFormat.format(tongtien)+" VNĐ");
            
        }

        recDathang=findViewById(R.id.Recdathang);
        recDathang.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayout.VERTICAL, false));
        recDathang.setHasFixedSize(true);
        DatHangAdapter datHangAdapter=new DatHangAdapter(getApplicationContext(),lst);
        recDathang.setAdapter(datHangAdapter);
        getMakh();


        ArrayList<GioHang> finalLst = lst;

        btnDathang.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {



                String day = LocalDateTime.now().getDayOfMonth() + "-" + LocalDateTime.now().getMonth() + "-" + LocalDateTime.now().getYear();

                Date date = new Date();
                try {
                    date = new SimpleDateFormat("dd/MM/yyyy").parse(day);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                mResultAPI.them1donhang("",makh , date,
                        tongtien, null, "false").enqueue(new Callback<DonHang>() {

                    @Override
                    public void onResponse(Call<DonHang> call, Response<DonHang> response) {


                        compositeDisposable.add(mResultAPI.getMakhtheouser2(makh).
                                subscribeOn(Schedulers.io()).
                                observeOn(AndroidSchedulers.mainThread()
                                ).subscribe(new Consumer<List<DonHang>>() {


                            @Override
                            public void accept(List<DonHang> dskh) throws Exception {
                                Toast.makeText(DathangActivity.this, ""+dskh.get(0).getMaDH(), Toast.LENGTH_SHORT).show();
                                for (int i = 0; i< finalLst.size(); i++) {
                                    mResultAPI.them1chitietdonhang(dskh.get(0).getMaDH(), finalLst.get(i).getIdsp(), finalLst.get(i).getSoluongsp(),
                                            finalLst.get(i).getGiasp(), finalLst.get(i).getThanhtien()).enqueue(new Callback<Chitietdondathang>() {
                                        @Override
                                        public void onResponse(Call<Chitietdondathang> call, Response<Chitietdondathang> response) {
                                            dbHelper.xoa1giohang(Common.getmail());
                                            startActivity(new Intent(DathangActivity.this,HomeActivity.class));
                                        }

                                        @Override
                                        public void onFailure(Call<Chitietdondathang> call, Throwable t) {

                                        }
                                    });
                                }



                            }
                        }));



                    }

                    @Override
                    public void onFailure(Call<DonHang> call, Throwable t) {

                    }
                });


            }

        });




    }

    private void getMadh(String makh) {
       {
            compositeDisposable.add(mResultAPI.getMakhtheouser2(makh).
                    subscribeOn(Schedulers.io()).
                    observeOn(AndroidSchedulers.mainThread()
                    ).subscribe(new Consumer<List<DonHang>>() {


                @Override
                public void accept(List<DonHang> dskh) throws Exception {

                   madh=dskh.get(0).getMaDH();


                }
            }));

        }
    }

    private void getMakh() {
        if (Common.getFirebaseAuth().getCurrentUser() != null) {
            compositeDisposable.add(mResultAPI.getMakhtheouser(Common.getmail()).
                    subscribeOn(Schedulers.io()).
                    observeOn(AndroidSchedulers.mainThread()
                    ).subscribe(new Consumer<List<KhachHang>>() {


                @Override
                public void accept(List<KhachHang> dskh) throws Exception {
                    makh = dskh.get(0).getMaKH();



                }
            }));

        } else
            return;

    }
}