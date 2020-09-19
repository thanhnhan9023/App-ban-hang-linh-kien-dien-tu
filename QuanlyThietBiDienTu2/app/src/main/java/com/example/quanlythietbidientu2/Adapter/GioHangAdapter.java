package com.example.quanlythietbidientu2.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.quanlythietbidientu2.Model.LoaiProduct;
import com.example.quanlythietbidientu2.Model.SanPham;
import com.example.quanlythietbidientu2.R;
import com.google.common.base.FinalizablePhantomReference;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.List;

import Database.Database.DBHelper;
import Database.Model.GioHang;


public class GioHangAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<GioHang>  gioHangs;
         Context context;
    DBHelper dbHelper;
    Activity activity;
    public GioHangAdapter(Activity activity, List<GioHang> gioHangs) {
        this.activity = activity;
        this.gioHangs = gioHangs;
    }


    public class VH extends RecyclerView.ViewHolder implements  View.OnClickListener {
        //        ItemClickListnear itemClickListnear;
//        public void setItemClickListnear(ItemClickListnear itemClickListnear) {
//            this.itemClickListnear = itemClickListnear;
//        }
        View.OnClickListener onClickListener;
        //    ItemClickListnear itemClickListnear;


        TextView NamProduct,txtxoa1,txtSua;
        ImageView LinkImage;
        TextView Giasp;
        ElegantNumberButton txtsoluong;

        public VH(@NonNull View itemView) {
            super(itemView);
            NamProduct = itemView.findViewById(R.id.txtTenSpGioHang);
            LinkImage = itemView.findViewById(R.id.ImagespGioHang);
            Giasp=itemView.findViewById(R.id.txtGiaSpGioGang);
            txtSua=itemView.findViewById(R.id.txtSua);
            txtsoluong=itemView.findViewById(R.id.txtCountGioHang);
            txtxoa1=itemView.findViewById(R.id.txtXoa);
          //  txtsoluong.setEnabled(false);

        }

        @Override
        public void onClick(View v) {

        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(activity).inflate(R.layout.giohang_item_layout,null);


        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        VH vh = (VH)viewHolder;
            dbHelper=new DBHelper(activity);
        final  int positon=i;
        Picasso.with(activity).load(gioHangs.get(i).getLinksp()).into(vh.LinkImage);
        vh.NamProduct.setText(gioHangs.get(i).getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        float giasp=gioHangs.get(i).getGiasp();
        vh.Giasp.setText(decimalFormat.format(giasp)+" VNĐ");
        vh.txtsoluong.setEnabled(false);
        for (int k = 0; k < vh.txtsoluong.getChildCount(); k++) {
            View child = vh.txtsoluong.getChildAt(k);
            child.setEnabled(false);
        }
        vh.txtsoluong.setNumber(gioHangs.get(i).getSoluongsp()+"");

        vh.txtSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vh.txtSua.setText("Xong");
                vh.txtxoa1.setVisibility(View.VISIBLE);


            }
        });
        vh.txtxoa1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    dbHelper.xoa1sanpham(gioHangs.get(positon).getIdgiohang());
                Intent i = new Intent(activity, GUI.GioHang.class);
             //   i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                activity.startActivity(i);
                activity.finish();
             //   ((Activity) vh.txtxoa1.getContext()).finish();
//                ((Activity)context).finish();
            }
        });

    }

    @Override
    public int getItemCount() {
        return gioHangs.size();
    }
}
