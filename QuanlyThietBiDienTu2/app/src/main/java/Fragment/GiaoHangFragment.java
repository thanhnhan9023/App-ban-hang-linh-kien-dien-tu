package Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.quanlythietbidientu2.Adapter.ChoxacnhanAdapter;
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

public class GiaoHangFragment extends Fragment {



    private RecyclerView recgiaohang;
    private ResultAPI resultAPI;
    private ScrollView scrollView;
    CompositeDisposable compositeDisposable;
    public GiaoHangFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_giao_hang, container, false);
        compositeDisposable=new CompositeDisposable();
        resultAPI= Common.getAPI();
        recgiaohang=view.findViewById(R.id.recgiaohang);
        recgiaohang.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false));
        recgiaohang.setHasFixedSize(true);

        if(Common.getFirebaseAuth().getCurrentUser()!=null) {
            getChietdonhangdangiao();
        }
        else
            Toast.makeText(getContext(), ""+"Chưa đăng nhập", Toast.LENGTH_SHORT).show();

        return  view;
        

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
                GiaohangAdapter giaohangAdapter= new GiaohangAdapter(getContext(), chitietdondathangs);
                recgiaohang.setAdapter(giaohangAdapter);
                Toast.makeText(getContext(), "" + chitietdondathangs.size(), Toast.LENGTH_SHORT).show();

            }
        }));
    }
}