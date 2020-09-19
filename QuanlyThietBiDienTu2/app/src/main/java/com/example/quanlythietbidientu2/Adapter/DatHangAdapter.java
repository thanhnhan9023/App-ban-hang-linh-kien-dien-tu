package com.example.quanlythietbidientu2.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.PrecomputedText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.quanlythietbidientu2.Model.LoaiProduct;
import com.example.quanlythietbidientu2.Model.SanPham;
import com.example.quanlythietbidientu2.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import Database.Model.GioHang;
import GUI.DathangActivity;

public class DatHangAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<GioHang>  gioHangs;
    Context context;

    public DatHangAdapter(Context context , List<GioHang> gioHangs) {
        this.context =context;
        this.gioHangs =gioHangs;
    }





    public class VH extends RecyclerView.ViewHolder implements  View.OnClickListener {


        TextView NamProduct,txtsoluong;
        ImageView LinkImage;
        TextView Giasp;


        public VH(@NonNull View itemView) {
            super(itemView);
            NamProduct = itemView.findViewById(R.id.txtTenSpDathang);
            LinkImage = itemView.findViewById(R.id.ImageDathang);
            Giasp=itemView.findViewById(R.id.txtGiaDathang);
          //  txtSua=itemView.findViewById(R.id.txtSua);
            txtsoluong=itemView.findViewById(R.id.txtsoluongdathang);



        }

        @Override
        public void onClick(View v) {

        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.dathang_layout,null);
        return new DatHangAdapter.VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        VH vh = (VH)viewHolder;
        Picasso.with(context).load(gioHangs.get(i).getLinksp()).into(vh.LinkImage);
        vh.NamProduct.setText(gioHangs.get(i).getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        float giasp=gioHangs.get(i).getGiasp();
        vh.Giasp.setText(decimalFormat.format(giasp)+" VNĐ");



        String s= String.format(String.valueOf(gioHangs.get(i).getSoluongsp()));
        vh.txtsoluong.setText("x"+s);

    }

    @Override
    public int getItemCount() {
        return gioHangs.size();
    }
}
