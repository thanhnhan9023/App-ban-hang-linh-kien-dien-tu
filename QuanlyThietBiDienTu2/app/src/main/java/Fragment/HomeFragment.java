package Fragment;

import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.quanlythietbidientu2.Adapter.CategoryViewHolder;
import com.example.quanlythietbidientu2.Adapter.HonrzontalAapter;
import com.example.quanlythietbidientu2.Adapter.SanPhamHomeAdapter;
import com.example.quanlythietbidientu2.Adapter.SildeAdapter;
import com.example.quanlythietbidientu2.Model.Banner;
import com.example.quanlythietbidientu2.Model.LoaiProduct;
import com.example.quanlythietbidientu2.Model.SanPham;
import com.example.quanlythietbidientu2.R;
import com.example.quanlythietbidientu2.Retrofit.ResultAPI;
import com.example.quanlythietbidientu2.Utils.Common;
import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;

import java.util.HashMap;
import java.util.List;
import java.util.PropertyPermission;
import java.util.Timer;
import java.util.TimerTask;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomeFragment extends Fragment {

    ResultAPI mService;

     List<Banner> bannerList;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
        Timer timer;
    RecyclerView recyclerViewLoaisp;
    RecyclerView recyclerViewSanPhamNoibat,recyclerViewSanPhammoinhat;
    SliderLayout siSliderLayout;
    ScrollView scrollView;
    int currentPage =0;
    final long delay=3000;
    final  long petro=3000;

    //baner silde

    ViewPager bannerSilde;

    //
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container ,false);
    //    scrollView=view.findViewById(R.id.scrollView);
        mService = Common.getAPI();
        recyclerViewLoaisp = view.findViewById(R.id.recLoaiSp);
      bannerSilde = view.findViewById(R.id.view_BanerPage);
        recyclerViewLoaisp.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayout.HORIZONTAL, false));
        recyclerViewLoaisp.setHasFixedSize(true);
      //  siSliderLayout=view.findViewById(R.id.silderBanner);
        recyclerViewSanPhamNoibat=view.findViewById(R.id.recSanPhamNoiBat);
        recyclerViewSanPhamNoibat.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayout.HORIZONTAL,false));
        recyclerViewSanPhamNoibat.setHasFixedSize(true);
        recyclerViewSanPhammoinhat =view.findViewById(R.id.recSanPhamMoinhat);
        recyclerViewSanPhammoinhat.setLayoutManager( new GridLayoutManager( getContext(),2,LinearLayout.VERTICAL,false));
        recyclerViewSanPhammoinhat.setHasFixedSize(true);
     if(  Common.getFirebaseAuth().getCurrentUser()!=null)
     {
         String s="a";
         Log.d("sd",Common.getmail());
     }


        getLoaiSp();// get danh muc header

        getBaner();// getbaner quảng cáo
        stratBaner();// tự động chạy quảng cáo

     //  getBanerImage();
         getSanphamnoibat();// get sản phẩm nổi bật center layout
        getsanphammoinhat();


//        ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int i, float v, int i1) {
//
//            }
//
//            @Override
//            public void onPageSelected(int i) {
//                currentPage = i;
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int i) {
//                if (i == ViewPager.SCROLL_STATE_IDLE) {
//                  papgeLopper();
//                }
//                currentPage = i;
//            }
//        };
//        bannerSilde.addOnPageChangeListener(onPageChangeListener);
//        bannerSilde.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//
//                stopBanner();
//                if(event.getAction()==MotionEvent.ACTION_UP)
//                {
//                    stratBaner();
//                }
//
//
//                return false;
//            }
//        });
        return  view;
    }

    private void getsanphammoinhat() {
        compositeDisposable.add(mService.getsanphammoi().
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()
                ).subscribe(new Consumer<List<SanPham>>() {


            @Override
            public void accept(List<SanPham> sanPhams) throws Exception {
                displayLoaisp(sanPhams);
            }

            private void displayLoaisp(List<SanPham> sanPhams) {

                SanPhamHomeAdapter sanPhamHomeAdapter = new SanPhamHomeAdapter( sanPhams,getContext());


                recyclerViewSanPhammoinhat.setAdapter(sanPhamHomeAdapter);

                //    Toast.makeText(getContext(),sanPhams.get(0).getLinkSP()+"", Toast.LENGTH_SHORT).show();
            }
        }));

    }

    private void getSanphamnoibat() {

        compositeDisposable.add(mService.getAllSanpham().
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()
                ).subscribe(new Consumer<List<SanPham>>() {


            @Override
            public void accept(List<SanPham> sanPhams) throws Exception {
                displayLoaisp(sanPhams);
            }

            private void displayLoaisp(List<SanPham> sanPhams) {

                HonrzontalAapter honrzontalAapter = new HonrzontalAapter(getContext(), sanPhams);


                recyclerViewSanPhamNoibat.setAdapter(honrzontalAapter);

            //    Toast.makeText(getContext(),sanPhams.get(0).getLinkSP()+"", Toast.LENGTH_SHORT).show();
            }
        }));

}



    private void getBaner() {

        compositeDisposable.add(mService.getBanner().
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()
                ).subscribe(new Consumer<List<Banner>>() {

            @Override
            public void accept(List<Banner> banners) throws Exception {
                displayImage(banners);
             //   bannerList =new List<Banner>();
                bannerList=banners;
             //   Toast.makeText(getContext(),banners.size()+"", Toast.LENGTH_SHORT).show();
            }

            private void displayImage(List<Banner> banners) {

                SildeAdapter sildeAdapter=new SildeAdapter(getContext(),banners);
                bannerSilde.setAdapter(sildeAdapter);
                bannerSilde.setClipToPadding(false);
                bannerSilde.setPageMargin(20);
                bannerSilde.setCurrentItem(currentPage);
               // stratBaner();
              //  bannerSilde.setAdapter(sildeAdapter);


            }
        }));

    }




  private  void stratBaner()
    {
        Handler handler=new Handler();
        Runnable runnable= new Runnable() {
            @Override
            public void run() {
                if(currentPage>=bannerList.size())
                {
                    currentPage=0;
                }
         bannerSilde.setCurrentItem(currentPage++, true);

        };

        };
        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }

        },3000,8000);
    }


    public  void stopBanner()
    {
        timer=new Timer();
        timer.cancel();
    }

    public  void papgeLopper()
        {
            if(currentPage==bannerList.size()-2)
            {
                currentPage=2;
                bannerSilde.setCurrentItem(currentPage,false);
            }
            if(currentPage==1)
            {
                currentPage=bannerList.size()-3;
                bannerSilde.setCurrentItem(currentPage,false);
            }
        }

    private void getLoaiSp() {


        compositeDisposable.add(mService.getLoaisp().
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()
                ).subscribe(new Consumer<List<LoaiProduct>>() {


            @Override
            public void accept(List<LoaiProduct> loaiProducts) throws Exception {
                displayLoaisp(loaiProducts);
            }

            private void displayLoaisp(List<LoaiProduct> loaiProducts) {

                CategoryViewHolder sanPhamViewHolder = new CategoryViewHolder(getActivity(), loaiProducts);
                recyclerViewLoaisp.setAdapter(sanPhamViewHolder);
            }
        }));
    }

    private void getBanerImage() {

        compositeDisposable.add(mService.getBanner().
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()
                ).subscribe(new Consumer<List<Banner>>() {

            @Override
            public void accept(List<Banner> banners) throws Exception {
                displayImage(banners);
            }

    }));
        }

    private void displayImage(List<Banner> banners) {

        HashMap<String, String> banermap = new HashMap<>();
        for (Banner item : banners) {
            banermap.put(item.getName(), item.getLink());
        }
        for (String name : banermap.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getContext());
            textSliderView.description(name).image(banermap.get(name)).
                    setScaleType(BaseSliderView.ScaleType.Fit);
            siSliderLayout.addSlider(textSliderView);
        }
    }


}