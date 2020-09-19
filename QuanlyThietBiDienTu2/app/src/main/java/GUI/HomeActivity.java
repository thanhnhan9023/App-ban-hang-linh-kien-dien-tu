package GUI;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.quanlythietbidientu2.Adapter.CategoryViewHolder;
import com.example.quanlythietbidientu2.Model.Banner;
import com.example.quanlythietbidientu2.Model.DonHang;
import com.example.quanlythietbidientu2.Model.LoaiProduct;
import com.example.quanlythietbidientu2.R;
import com.example.quanlythietbidientu2.Retrofit.ResultAPI;
import com.example.quanlythietbidientu2.Utils.Common;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthCredential;
import com.nex3z.notificationbadge.NotificationBadge;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Database.Database.DBHelper;
import Fragment.AcountFragment;
import Fragment.HomeFragment;
import Fragment.RewardFragment;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private AppBarConfiguration mAppBarConfiguration;
    public String i = "";
    TextView txtname, txtEmail;
    SliderLayout siSliderLayout;
    ResultAPI mService;
    RecyclerView recyclerViewLoaisp;
    DrawerLayout drawer;
    //
    String s="";
    FirebaseAuth firebaseAuth;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    //item cart
     NotificationBadge badge;
   // TextView textCartItemCount;
    DBHelper dbHelper;
    int count=3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //     getSupportActionBar().setDisplayShowTitleEnabled(false);
        mService = Common.getAPI();
        dbHelper=new DBHelper(getApplicationContext());
      //  firebaseAuth=FirebaseAuth.getInstance();
        String s="";
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerview =navigationView.getHeaderView(0);
        txtEmail=headerview.findViewById(R.id.txtEmail);
        if(Common.getFirebaseAuth().getCurrentUser()!=null)
        {
            txtEmail.setText(Common.getmail());
        }


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
        //updateCartcount();
       setupBadge();

    }







    @Override
    protected void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {



            getMenuInflater().inflate(R.menu.home, menu);
        final MenuItem menuItem = menu.findItem(R.id.menu_Cart);
            View view=menuItem.getActionView();
            badge=view.findViewById(R.id.badge);
            setupBadge();
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onOptionsItemSelected(menuItem);
                }
            });





        return true;
    }

    private void setupBadge() {
        if (badge != null) {
            ArrayList<Database.Model.GioHang> list=new ArrayList<>();
            if(Common.Kiemtrataikhoan()) {
                list = dbHelper.getALLGioHang(Common.getmail());
            }

            else
              list = dbHelper.getALLGioHang("");
            Log.d("xemcount",list.size()+"");
            if (list.size()<=0) {

                if (badge.getVisibility() != View.GONE) {
                    badge.setVisibility(View.GONE);
                }
            } else {

              badge.setText(String.valueOf(list.size()));
                if (badge.getVisibility() != View.VISIBLE) {
                    badge.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private void updateCartcount() {
               if(badge==null) return;;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        ArrayList<Database.Model.GioHang> list=new ArrayList<>();
//                        list=dbHelper.getALLGioHang("");
                        if(Common.currentgiohang.size()==0)
                        {
                            badge.setVisibility(View.INVISIBLE);
                        }
                        else {
                            badge.setVisibility(View.VISIBLE);
                            badge.setText(String.valueOf(Common.currentgiohang.size()));
                            //    Log.d("cart",list.size()+"");
                        }
                    }
                });

    }

    public boolean onOptionsItemSelected(MenuItem item) {



        switch (item.getItemId())
        {
            case R.id.menu_Cart:
                Log.d("xemgiohang", "onOptionsItemSelected: ");

                        startActivity(new Intent(this,GioHang.class));
                break;
            case  R.id.menu_sreach:
                startActivity(new Intent(this,SerachActivity.class));
                break;
        }
        return true;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        {
            switch (item.getItemId()) {
                case R.id.nav_home:
                  loadfragment(new HomeFragment());
                    Log.d("home", "onNavigationItemSelected: ");
                    break;
                    case R.id.nav_My_Acoount:
                        startActivity(new Intent(HomeActivity.this,SignActivity.class));

                        break;
                case  R.id.nav_logout:
                    Common.getFirebaseAuth().signOut();
                    Toast.makeText(this, "Đăng Xuất", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(HomeActivity.this,HomeActivity.class));
                    finish();
                        break;
                    case  R.id.nav_my_cart:
                        startActivity(new Intent(HomeActivity.this,GioHang.class));
                        break;
                        case R.id.nav_donhang:
                          startActivity(new Intent(HomeActivity.this,GiaoHang.class));
                    break;
                case  R.id.nav_my_rewards:

                     loadfragment(new RewardFragment());

                    break;



                }
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
       // updateCartcount();
        setupBadge();
    }

    @Override
    protected void onResume() {
        super.onResume();
      //updateCartcount();
        setupBadge();
    }

    public void loadfragment(Fragment fragment)
    {
        FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }





}




