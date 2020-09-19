package com.example.quanlythietbidientu2.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlythietbidientu2.Model.LoaiProduct;
import com.example.quanlythietbidientu2.R;
import GUI.SanPhamActivity;
import com.example.quanlythietbidientu2.Utils.Common;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryViewHolder extends RecyclerView.Adapter<ViewHolder>
{
    Context context;
    List<LoaiProduct> loaiProducts;
    RecyclerView recyclerView;


    public CategoryViewHolder(Context context,List<LoaiProduct> loaiProducts) {
        this.context = context;
        this.loaiProducts=loaiProducts;
    }

    public class VH extends RecyclerView.ViewHolder  implements  View.OnClickListener

    {
//        ItemClickListnear itemClickListnear;
//        public void setItemClickListnear(ItemClickListnear itemClickListnear) {
//            this.itemClickListnear = itemClickListnear;
//        }
            View.OnClickListener onClickListener;
    //    ItemClickListnear itemClickListnear;


        TextView NamProduct;
        ImageView LinkImage;

        public VH(@NonNull View itemView) {
            super(itemView);
            NamProduct = itemView.findViewById(R.id.txtTenLoaiSp);
            LinkImage= itemView.findViewById(R.id.ImageLoaiSp);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
               onClickListener.onClick(v);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.danhmucsanpham_layout,null);

        return new VH(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder,  int i) {

         VH vh = (VH)viewHolder;
         final  LoaiProduct loaiProduct=loaiProducts.get(i);
        Picasso.with(context).load(loaiProducts.get(i).getLinkloaihang()).into(vh.LinkImage);
        vh.NamProduct.setText(loaiProducts.get(i).getTenLoai());

        vh.onClickListener =new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  Toast.makeText(context,"ấdsa",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context.getApplicationContext(),SanPhamActivity.class);
               Common.currentLoaiProduct=loaiProduct;
                context.startActivity(intent);
            }
        };



    }

    @Override
    public int getItemCount() {
        return loaiProducts.size();
    }


}
