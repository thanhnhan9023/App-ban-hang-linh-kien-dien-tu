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
import com.example.quanlythietbidientu2.Model.Chitietdondathang;
import com.example.quanlythietbidientu2.Model.ChitietdondathangCustoms;
import com.example.quanlythietbidientu2.Model.LoaiProduct;
import com.example.quanlythietbidientu2.R;
import com.example.quanlythietbidientu2.Retrofit.ResultAPI;
import com.example.quanlythietbidientu2.Utils.Common;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class XacnhandonhangFragment extends Fragment {

        private RecyclerView recchoxacnhan;
        private ResultAPI resultAPI;
        private ScrollView scrollView;
    CompositeDisposable compositeDisposable;
    public XacnhandonhangFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        compositeDisposable=new CompositeDisposable();
        resultAPI= Common.getAPI();
        View view= inflater.inflate(R.layout.fragment_xacnhandonhang, container, false);
        //scrollView=view.findViewById(R.id)
     recchoxacnhan=view.findViewById(R.id.RecXacnhandon);
        recchoxacnhan.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false));
        recchoxacnhan.setHasFixedSize(true);

       if(Common.getFirebaseAuth().getCurrentUser()!=null) {
           getChietdonhang();
       }
       else
           Toast.makeText(getContext(), ""+"Chưa đăng nhập", Toast.LENGTH_SHORT).show();
       return  view;
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
//                    ChoxacnhanAdapter choxacnhanAdapter = new ChoxacnhanAdapter(getContext(, chitietdondathangs);
//                    recchoxacnhan.setAdapter(choxacnhanAdapter);
//                    Toast.makeText(getContext(), "" + chitietdondathangs.size(), Toast.LENGTH_SHORT).show();

                }
            }));


    }
}