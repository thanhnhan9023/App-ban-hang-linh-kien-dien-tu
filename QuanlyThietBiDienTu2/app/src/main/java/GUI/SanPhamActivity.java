package GUI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.quanlythietbidientu2.Adapter.SanPhamViewHolder;
import com.example.quanlythietbidientu2.Model.SanPham;
import com.example.quanlythietbidientu2.R;
import com.example.quanlythietbidientu2.Retrofit.ResultAPI;
import com.example.quanlythietbidientu2.Utils.Common;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SanPhamActivity extends AppCompatActivity {
    ResultAPI mService;
    RecyclerView recyclerViewSanPham;
    private ElegantNumberButton elegantNumberButton;

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_san_pham);


        mService = Common.getAPI();
//
        recyclerViewSanPham = findViewById(R.id.RecViewSp);
        recyclerViewSanPham.setLayoutManager( new GridLayoutManager( this,2, LinearLayout.VERTICAL,false));
        recyclerViewSanPham.setHasFixedSize(true);

   getSanPham(Common.currentLoaiProduct.getMaLoai());


    }

    private void getSanPham(String id) {

        compositeDisposable.add(mService.getSanPham(id).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()
                ).subscribe(new Consumer<List<SanPham>>() {


            @Override
            public void accept(List<SanPham> sanPhams) throws Exception {
             displaySanPham(sanPhams);
            }
        }));


    }

    private void displaySanPham(List<SanPham> sanPhams) {

//
          SanPhamViewHolder sanPhamViewHolder=new SanPhamViewHolder(this,sanPhams);
      recyclerViewSanPham.setAdapter(sanPhamViewHolder);

       //Toast.makeText(this,sanPhams.get(0).getLinkSP(), Toast.LENGTH_SHORT).show();

    }
}