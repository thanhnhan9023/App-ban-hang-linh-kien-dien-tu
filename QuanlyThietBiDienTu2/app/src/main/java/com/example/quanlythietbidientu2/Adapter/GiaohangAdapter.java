package com.example.quanlythietbidientu2.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlythietbidientu2.Model.Chitietdondathang;
import com.example.quanlythietbidientu2.Model.ChitietdondathangCustoms;
import com.example.quanlythietbidientu2.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import GUI.GiaoHang;

public class GiaohangAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private Context context;
    private List<ChitietdondathangCustoms> arrayList;
    public GiaohangAdapter  (Context context, List<ChitietdondathangCustoms>  arrayList)
    {
                this.context=context;
                this.arrayList=arrayList;
    }

    public class VH extends RecyclerView.ViewHolder implements  View.OnClickListener {


        TextView NamProduct,txtsoluong,thanhtien;
        ImageView LinkImage;
        TextView Giasp;
        Button btnchoxuly;
        TextView txthuybo;

        public VH(@NonNull View itemView) {
            super(itemView);
            NamProduct=itemView.findViewById(R.id.txtTenspXacnhan);
            txtsoluong=itemView.findViewById(R.id.txtSoluongxacnhan);
            LinkImage=itemView.findViewById(R.id.ImageSpxaxnhan);
            Giasp=itemView.findViewById(R.id.txtGiaspxacnhan);
            thanhtien=itemView.findViewById(R.id.txtThanhtienxacnhan);
            btnchoxuly=itemView.findViewById(R.id.btnChoxuly);
            txthuybo=itemView.findViewById(R.id.txtHuybo);



        }

        @Override
        public void onClick(View v) {

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
        VH vh = (VH)viewHolder;
        vh.NamProduct.setText(arrayList.get(i).getTenHH());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        vh.Giasp.setText(decimalFormat.format(arrayList.get(i).getGiaban())+"VNĐ");
        vh.txtsoluong.setText("x"+arrayList.get(i).getSoluong());
        Picasso.with(context).load(arrayList.get(i).getDuongdan()).into(vh.LinkImage);
        vh.thanhtien.setText(decimalFormat.format(arrayList.get(i).getThanhtien()));
        vh.btnchoxuly.setText("Đang Giao Hàng");
        vh.txthuybo.setVisibility(View.INVISIBLE);

//        vh.txtsoluong.setText(arrayList.get(i).getSoluong());

    }

    @Override
    public int getItemCount() {

        return arrayList.size();
    }
}
