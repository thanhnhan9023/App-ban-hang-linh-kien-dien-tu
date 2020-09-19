package com.example.quanlythietbidientu2.Adapter;

import android.content.Context;
import android.graphics.pdf.PdfDocument;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.quanlythietbidientu2.Model.Banner;
import com.example.quanlythietbidientu2.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SildeAdapter extends PagerAdapter {

    Context context;
     List<Banner> banners;
    ImageView banner;
    public  SildeAdapter(Context context,List<Banner> banners)
    {
        this.context=context;
        this.banners=banners;

    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view= LayoutInflater.from(context).inflate(R.layout.silder_layout,container,false);
         banner=view.findViewById(R.id.Baner_SildeCenter);
        Picasso.with(context).load(banners.get(position).getLink()).into(banner);
        container.addView(view,0);
        return view;
    }

    @Override
    public int getCount() {
        return banners.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
       container.removeView((View)object);
    }
}
