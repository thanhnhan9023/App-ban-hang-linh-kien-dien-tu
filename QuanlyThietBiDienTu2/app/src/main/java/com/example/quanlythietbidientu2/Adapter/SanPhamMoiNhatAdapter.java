package com.example.quanlythietbidientu2.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlythietbidientu2.Model.SanPham;
import com.example.quanlythietbidientu2.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class SanPhamMoiNhatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context context;
    private List<SanPham> sanPhams;
    public  SanPhamMoiNhatAdapter(Context context,List<SanPham> sanPhams)
    {
        this.context=context;
        this.sanPhams=sanPhams;
    }

    public  class Vh extends RecyclerView.ViewHolder {

        private TextView txtTenspMoiNhat,txtGiaSanphammoinhat;
        private ImageView ImgeSanphammoinhat;
        public Vh(@NonNull View itemView) {
            super(itemView);
                txtGiaSanphammoinhat=itemView.findViewById(R.id.txtGiaSanPhamMoiNhat);
                txtTenspMoiNhat=itemView.findViewById(R.id.txtTenSpMoiNhat);
                ImgeSanphammoinhat=itemView.findViewById(R.id.ImageSanphamMoinhat);




        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.grid_sanpham_layout,null);

        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        Vh vh = (Vh)viewHolder;

        Picasso.with(context).load((sanPhams).get(i).getDuongdan()).into(vh.ImgeSanphammoinhat);
        vh.txtTenspMoiNhat.setText(sanPhams.get(i).getTenHH());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");


        float tiensp=Float.parseFloat(sanPhams.get(i).getGiabanle());

        vh.txtGiaSanphammoinhat.setText(decimalFormat.format(tiensp)+" VNĐ");

    }

    @Override
    public int getItemCount() {
        return sanPhams.size();
    }
}
