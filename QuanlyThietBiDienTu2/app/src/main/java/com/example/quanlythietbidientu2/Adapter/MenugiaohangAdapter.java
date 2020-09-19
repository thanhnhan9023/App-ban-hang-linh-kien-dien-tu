package com.example.quanlythietbidientu2.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import Fragment.XacnhandonhangFragment;
import Fragment.GiaoHangFragment;
import GUI.GiaohangActivity;
import GUI.XacnhangiaohangActivity;


import com.example.quanlythietbidientu2.R;

import java.util.ArrayList;

public class MenugiaohangAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {



    Activity activity;
    ArrayList<String> arrayList;

    public MenugiaohangAdapter(Activity activity,ArrayList arrayList)
    {
        this.activity=activity;
        this.arrayList=arrayList;
    }
    public class VH extends RecyclerView.ViewHolder  implements  View.OnClickListener

    {
        //        ItemClickListnear itemClickListnear;
//        public void setItemClickListnear(ItemClickListnear itemClickListnear) {
//            this.itemClickListnear = itemClickListnear;
//        }
        View.OnClickListener onClickListener;
        //    ItemClickListnear itemClickListnear;


            TextView txtMenu;

        public VH(@NonNull View itemView) {
            super(itemView);
                txtMenu=itemView.findViewById(R.id.txtmenugiaohang);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onClickListener.onClick(v);
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(activity).inflate(R.layout.menugiaohang_layout,null);

        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            final int t=i;
       VH vh = (VH)viewHolder;
       vh.txtMenu.setText(arrayList.get(i));
        vh.onClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        if(arrayList.get(t)=="Chờ xác nhận")
                                activity.startActivity(new Intent(activity, XacnhangiaohangActivity.class));
                      //  pushFragment(new XacnhandonhangFragment(),activity);
                        if(arrayList.get(t)=="Đang giao")
                            activity.startActivity(new Intent(activity, GiaohangActivity.class));
                 //   pushFragment(new GiaoHangFragment(),activity);

            }
        };



    }
    public void loadfragment(Fragment fragment)
    {
        ((FragmentActivity)activity).getSupportFragmentManager().beginTransaction()
                .replace(R.id.Framgiaohang, fragment)
                .commit();
//        FragmentTransaction transaction =
//                ((Activity)context).getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.fragment_container, fragment);
//        transaction.addToBackStack(null);
//        transaction.commit();
    }
    public void pushFragment(Fragment newFragment, Context context){

        FragmentTransaction transaction = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.Framgiaohang, newFragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_NONE);
        transaction.addToBackStack(null);
        transaction.commit();

    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
