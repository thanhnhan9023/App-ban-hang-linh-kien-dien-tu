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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlythietbidientu2.Model.Chitietdondathang;
import com.example.quanlythietbidientu2.Model.ChitietdondathangCustoms;
import com.example.quanlythietbidientu2.R;
import com.example.quanlythietbidientu2.Retrofit.ResultAPI;
import com.example.quanlythietbidientu2.Utils.Common;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import Fragment.ChitietSanphamActivity;
import GUI.XacnhangiaohangActivity;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import Fragment.XacnhandonhangFragment;

public class ChoxacnhanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    Activity context;
    List<ChitietdondathangCustoms> arrayList;
    ResultAPI resultAPI;
    CompositeDisposable compositeDisposable;

    public  ChoxacnhanAdapter(Activity context,List arrayList)
    {
        this.context=context;
        this.arrayList=arrayList;
    }

    public class VH extends RecyclerView.ViewHolder implements  View.OnClickListener {


        TextView NamProduct,txtsoluong,thanhtien,txthuybo;
        ImageView LinkImage;
        TextView Giasp;
        View.OnClickListener onClickListener;
        RecyclerView recyclerView;

        public VH(@NonNull View itemView) {
            super(itemView);

            NamProduct=itemView.findViewById(R.id.txtTenspXacnhan);
            txtsoluong=itemView.findViewById(R.id.txtSoluongxacnhan);
            LinkImage=itemView.findViewById(R.id.ImageSpxaxnhan);
            Giasp=itemView.findViewById(R.id.txtGiaspxacnhan);
            thanhtien=itemView.findViewById(R.id.txtThanhtienxacnhan);
            txthuybo=itemView.findViewById(R.id.txtHuybo);
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

        View view= LayoutInflater.from(context).inflate(R.layout.choxacnhan_layout,null);

        return new VH(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        final  int positon =i;
        VH vh = (VH) viewHolder;
        vh.NamProduct.setText(arrayList.get(i).getTenHH());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        vh.Giasp.setText(decimalFormat.format(arrayList.get(i).getGiaban()) + "VNĐ");
        vh.txtsoluong.setText("x" + arrayList.get(i).getSoluong());
        Picasso.with(context).load(arrayList.get(i).getDuongdan()).into(vh.LinkImage);
        vh.thanhtien.setText(decimalFormat.format(arrayList.get(i).getThanhtien()));
         resultAPI=Common.getAPI();


//        vh.txtsoluong.setText(arrayList.get(i).getSoluong());
        vh.txthuybo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultAPI.xoa1sanphamchitiet(arrayList.get(positon).getMaDH(),arrayList.get(positon).getMAHH()).enqueue(new Callback<ChitietdondathangCustoms>() {
                    @Override
                    public void onResponse(Call<ChitietdondathangCustoms> call, Response<ChitietdondathangCustoms> response) {
                            context.startActivity(new Intent(context, XacnhangiaohangActivity.class));
                            context.finish();
                    }

                    @Override
                    public void onFailure(Call<ChitietdondathangCustoms> call, Throwable t) {

                    }
                });
            }
        });
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
