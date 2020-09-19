package GUI;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.example.quanlythietbidientu2.Adapter.SanPhamHomeAdapter;
import com.example.quanlythietbidientu2.Model.LoaiProduct;
import com.example.quanlythietbidientu2.Model.SanPham;
import com.example.quanlythietbidientu2.R;
import com.example.quanlythietbidientu2.Retrofit.ResultAPI;
import com.example.quanlythietbidientu2.Utils.Common;
import com.google.firebase.auth.FirebaseAuth;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SerachActivity extends AppCompatActivity {

    ResultAPI mService;
    RecyclerView recyclerViewSerach;
    MaterialSearchBar searchBar;
    //
    List<SanPham> Datasource=new ArrayList<>();
    List<String> suggesList=new ArrayList<>();
    SanPhamHomeAdapter adapter,searchAdapter;

    FirebaseAuth firebaseAuth;
    CompositeDisposable compositeDisposable = new CompositeDisposable();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serach);
        mService= Common.getAPI();
        recyclerViewSerach=findViewById(R.id.RecSerach);
        recyclerViewSerach.setLayoutManager(new GridLayoutManager(this,2));
        searchBar=findViewById(R.id.serachBar);
        searchBar.setHint("Enter you text");
        loadAllSanPham();
        searchBar.setCardViewElevation(5);
    //   recyclerViewSerach.VISIBLE(this.in);
        searchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                    List<String> suggest =new ArrayList<>();
                    for (String serch:suggesList) {
                        if (serch.toLowerCase().contains(searchBar.getText().toLowerCase()))
                            suggest.add(serch);
                    }
                    searchBar.setLastSuggestions(suggest);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        searchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if(!enabled)
                    recyclerViewSerach.setAdapter(adapter);
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                        strartSerch(text);
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });
    }

    private void strartSerch(CharSequence text) {
        List<SanPham> result=new ArrayList<>();
        for (SanPham sp:Datasource)
        {
            if(sp.getTenHH().contains(text)) {
                Log.d("timkiem","vao");
                result.add(sp);
                searchAdapter = new SanPhamHomeAdapter(result, this);
                recyclerViewSerach.setAdapter(searchAdapter);

            }

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        compositeDisposable.clear();
    }

    private void loadAllSanPham() {


            compositeDisposable.add(mService.getAllSanpham().
                    subscribeOn(Schedulers.io()).
                    observeOn(AndroidSchedulers.mainThread()
                    ).subscribe(new Consumer<List<SanPham>>() {


                @Override
                public void accept(List<SanPham> sanPhams) throws Exception {
                    displaySanPham(sanPhams);
                    buildSuggestList(sanPhams);
                }

                private void buildSuggestList(List<SanPham> sanPhams) {

                    for (int i=0;i<5;i++)

                        suggesList.add(sanPhams.get(i).getTenHH());
                    searchBar.setLastSuggestions(suggesList);

                }

                private void displaySanPham(List<SanPham> sanPhams) {
                    Datasource=sanPhams;
                    adapter=new SanPhamHomeAdapter(sanPhams,getApplication());
                    recyclerViewSerach.setAdapter(adapter);

                }
            }));


        


    }
}