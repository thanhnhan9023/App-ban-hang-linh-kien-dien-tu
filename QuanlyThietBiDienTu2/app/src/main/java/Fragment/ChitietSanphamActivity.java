package Fragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlythietbidientu2.Model.SanPham;
import com.example.quanlythietbidientu2.R;
import com.example.quanlythietbidientu2.Retrofit.ResultAPI;
import com.example.quanlythietbidientu2.Utils.Common;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ChitietSanphamActivity extends AppCompatActivity {

    ResultAPI mService;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    private TextView txtTenchitietsp,txtMotasp;
    private ImageView imageViewChitietsp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet_sanpham);
        anhxa();
        mService = Common.getAPI();


      getSanPhamTheoID(Common.curSanPhamID.getMaHH());

}

    private void getSanPhamTheoID(String idsanpham) {

        compositeDisposable.add(mService.getSanPhamID(idsanpham).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()
                ).subscribe(new Consumer<List<SanPham>>() {


            @Override
            public void accept(List<SanPham> sanPhams) throws Exception {
                displaySanPhamtheoid(sanPhams);
            }

            private void displaySanPhamtheoid(List<SanPham> sanPhams) {

                txtTenchitietsp.setText(sanPhams.get(0).getTenHH());
                txtMotasp.setText(sanPhams.get(0).getMoTa());
                Picasso.with(getApplicationContext()).load(sanPhams.get(0).getDuongdan()).into(imageViewChitietsp);
            }
        }));
    }

    public  void anhxa()
        {
                txtTenchitietsp=findViewById(R.id.txtTenChitietSp);
                txtMotasp=findViewById(R.id.txtMotasp);
                imageViewChitietsp=findViewById(R.id.ImageChitietSp);
        }


}