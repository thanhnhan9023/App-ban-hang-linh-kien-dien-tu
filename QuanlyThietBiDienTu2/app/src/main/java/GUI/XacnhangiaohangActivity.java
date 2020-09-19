package GUI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.quanlythietbidientu2.Adapter.ChoxacnhanAdapter;
import com.example.quanlythietbidientu2.Model.ChitietdondathangCustoms;
import com.example.quanlythietbidientu2.R;
import com.example.quanlythietbidientu2.Retrofit.ResultAPI;
import com.example.quanlythietbidientu2.Utils.Common;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class XacnhangiaohangActivity extends AppCompatActivity {


    private RecyclerView recchoxacnhan;
    private ResultAPI resultAPI;
    private ScrollView scrollView;
    CompositeDisposable compositeDisposable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xacnhangiaohang);


        compositeDisposable=new CompositeDisposable();
        resultAPI= Common.getAPI();

        //scrollView=view.findViewById(R.id)
        recchoxacnhan=findViewById(R.id.RecXacnhandon);
        recchoxacnhan.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayout.VERTICAL, false));
        recchoxacnhan.setHasFixedSize(true);
        if(Common.getFirebaseAuth().getCurrentUser()!=null) {
            getChietdonhang();
        }
        else
            Toast.makeText(getApplicationContext(), ""+"Chưa đăng nhập", Toast.LENGTH_SHORT).show();

    }
    private void getChietdonhang() {

        compositeDisposable.add(resultAPI.getcchitietdonhangtheouser(Common.getmail()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()
                ).subscribe(new Consumer<List<ChitietdondathangCustoms>>() {


            @Override
            public void accept(List<ChitietdondathangCustoms> chitietdondathangs) throws Exception {
                displayLoaisp(chitietdondathangs);
            }

            private void displayLoaisp(List<ChitietdondathangCustoms> chitietdondathangs) {
                ChoxacnhanAdapter choxacnhanAdapter = new ChoxacnhanAdapter(XacnhangiaohangActivity.this ,chitietdondathangs);
                recchoxacnhan.setAdapter(choxacnhanAdapter);
                Toast.makeText(getApplicationContext(), "" + chitietdondathangs.size(), Toast.LENGTH_SHORT).show();

            }
        }));


    }
}