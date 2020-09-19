package com.example.quanlythietbidientu2.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.quanlythietbidientu2.Model.SanPham;
import com.example.quanlythietbidientu2.R;
import com.example.quanlythietbidientu2.Utils.Common;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import Database.Database.DBHelper;
import Database.Model.GioHang;
import Fragment.ChitietSanphamActivity;


public class SanPhamHomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    List<SanPham> sanPhams;
    Context context;
    long tongtien=0;

    public SanPhamHomeAdapter(List<SanPham> sanPhams, Context context) {
        this.sanPhams = sanPhams;
        this.context = context;
    }

    public class VH extends RecyclerView.ViewHolder  implements  View.OnClickListener
    {

        View.OnClickListener onClickListener;
        TextView NamProduct, Price;
        ImageView LinkImage1;
        Button btnAddcart;

        public VH(@NonNull View itemView) {
            super(itemView);

            Price = itemView.findViewById(R.id.txtPrice);
              NamProduct = itemView.findViewById(R.id.txtNameProductID);
            LinkImage1 = itemView.findViewById(R.id.imageViewID);
            btnAddcart=itemView.findViewById(R.id.btn_AddCart);
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
        View view= LayoutInflater.from(context).inflate(R.layout.sanpham_layout,null);

        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        final int  position=i;
        final SanPham sanPham=sanPhams.get(i);
       VH vh = (VH) viewHolder;
        //   Picasso.with(context).load(sanPhams.get(i).getLinkSP()).into(vh.LinkImage);
        Picasso.with(context).load(sanPhams.get(i).getDuongdan()).into(vh.LinkImage1);
        vh.NamProduct.setText(sanPhams.get(i).getTenHH());

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        float giasp= Float.parseFloat(sanPhams.get(i).getGiabanle());



        vh.Price.setText(decimalFormat.format(giasp)+"VNĐ");
       // vh.Price.setText(sanPhams.get(i).getGiabanle()+" VNĐ");
//        vh.btnAddcart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               // showAddtoCart(position);
//            }
//        });

        vh.btnAddcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddtoCart(position);
            }
        });


        vh.onClickListener= new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context.getApplicationContext(), ChitietSanphamActivity.class);
                Common.curSanPhamID=sanPham;
                context.startActivity(intent);


            }
        };


    }
    private void showAddtoCart (int position)

    {

        AlertDialog.Builder builder =new AlertDialog.Builder(context);
        View item=LayoutInflater.from(context).inflate(R.layout.add_tocart_layout,null);



        ImageView  imageView_product_dialog=item.findViewById(R.id.ImageproductCart);
        ElegantNumberButton soluongconfirm=item.findViewById(R.id.txtSoluongConfirm);
        TextView txt_product_dialog=item.findViewById(R.id.txtNameProductCart);

        TextView txtTongtien=item.findViewById(R.id.txtTongtienConfirm);
        Picasso.with(context).load(sanPhams.
                get(position).getDuongdan()).into(imageView_product_dialog);
        txt_product_dialog.setText(sanPhams.get(position).getTenHH());
        soluongconfirm.setOnClickListener(new ElegantNumberButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

                tongtien= (long) (Integer.parseInt(soluongconfirm.getNumber())*Float.parseFloat(sanPhams.get(position).getGiabanle()));
                txtTongtien.setText(decimalFormat.format(tongtien) + " VNĐ");
            }
        });

        builder.setView(item);
        builder.setNegativeButton("ADD To Cart", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                DBHelper dbHelper=new DBHelper(context);
                dbHelper.createTable();
                ArrayList<GioHang> gioHangArrayList=new ArrayList<>();

                if(Common.getFirebaseAuth().getCurrentUser()==null) {
                    gioHangArrayList.add(new GioHang(0, sanPhams.get(position).getTenHH(),
                            sanPhams.get(position).getMaHH(), sanPhams.get(position).getDuongdan(),
                            Float.parseFloat(sanPhams.get(position).getGiabanle()), tongtien, Integer.parseInt(soluongconfirm.getNumber()), ""));
                }
                else
                {
                    gioHangArrayList.add(new GioHang(0, sanPhams.get(position).getTenHH(),
                            sanPhams.get(position).getMaHH(), sanPhams.get(position).getDuongdan(),
                            Float.parseFloat(sanPhams.get(position).getGiabanle()), tongtien, Integer.parseInt(soluongconfirm.getNumber()),Common.getmail()));
                }
                if(Common.getFirebaseAuth().getCurrentUser()!=null) {
                    boolean kq = dbHelper.isItemAvailable(sanPhams.get(position).getMaHH(),Common.getmail());
                    if (kq == true) {


                        dbHelper.capnhatgiohangtheouser(sanPhams.get(position).getMaHH(), Integer.parseInt(soluongconfirm.getNumber()),Common.getmail());
                        context.startActivity(new Intent(context, GUI.GioHang.class));

                    } else if (kq == false) {

                        dbHelper.insertGioHang(gioHangArrayList);
                        context.startActivity(new Intent(context, GUI.GioHang.class));


                    }
                }
                else {
                    boolean kq = dbHelper.isItemAvailable(sanPhams.get(position).getMaHH(),"");

                    if (kq == true) {
                        //boolean kq=dbHelper.kiemtrasanphamgiohang(sanPhams.get(position).getMaHH());
                        dbHelper.capnhatgiohangtheouser(sanPhams.get(position).getMaHH(), Integer.parseInt(soluongconfirm.getNumber()),"");
                        context.startActivity(new Intent(context, GUI.GioHang.class));

                    } else if (kq == false) {
                        dbHelper.insertGioHang(gioHangArrayList);
                        context.startActivity(new Intent(context, GUI.GioHang.class));


                    }

                }
//
                dialog.dismiss();
            }
        });
        builder.setView(item);
        AlertDialog dialog=builder.create();

        dialog.show();




    }


    @Override
    public int getItemCount() {
        return sanPhams.size();
    }
}
