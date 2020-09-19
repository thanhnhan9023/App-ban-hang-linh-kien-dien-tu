package GUI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.quanlythietbidientu2.Adapter.GiaohangAdapter;
import com.example.quanlythietbidientu2.Model.ChitietdondathangCustoms;
import com.example.quanlythietbidientu2.R;
import com.example.quanlythietbidientu2.Retrofit.ResultAPI;
import com.example.quanlythietbidientu2.Utils.Common;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class GiaohangActivity extends AppCompatActivity {


    private RecyclerView recgiaohang;
    private ResultAPI resultAPI;
    private ScrollView scrollView;
    CompositeDisposable compositeDisposable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giaohang);

        compositeDisposable=new CompositeDisposable();
        resultAPI= Common.getAPI();
        recgiaohang=findViewById(R.id.recgiaohang);
        recgiaohang.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayout.VERTICAL, false));
        recgiaohang.setHasFixedSize(true);

        if(Common.getFirebaseAuth().getCurrentUser()!=null) {
            getChietdonhangdangiao();
        }
        else
            Toast.makeText(getApplicationContext(), ""+"Chưa đăng nhập", Toast.LENGTH_SHORT).show();

    }
    private void getChietdonhangdangiao() {
        compositeDisposable.add(resultAPI.getcchitietgiaohang(Common.getmail()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()
                ).subscribe(new Consumer<List<ChitietdondathangCustoms>>() {


            @Override
            public void accept(List<ChitietdondathangCustoms> chitietdondathangs) throws Exception {
                displayLoaisp(chitietdondathangs);
            }

            private void displayLoaisp(List<ChitietdondathangCustoms> chitietdondathangs) {
                GiaohangAdapter giaohangAdapter= new GiaohangAdapter(getApplicationContext(), chitietdondathangs);
                recgiaohang.setAdapter(giaohangAdapter);
                Toast.makeText(getApplicationContext(), "" + chitietdondathangs.size(), Toast.LENGTH_SHORT).show();

            }
        }));
    }
}