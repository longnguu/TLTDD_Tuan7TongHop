package com.example.tltdd_tuan7.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.tltdd_tuan7.R;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

public class SlideAdapter extends PagerAdapter {
    Context mContext;
    List<SlideModel> slideModels;

    public SlideAdapter(Context mContext, List<SlideModel> slideModels) {
        this.mContext = mContext;
        this.slideModels = slideModels;
    }

    public SlideAdapter(List<SlideModel> slideModels) {
        this.slideModels = slideModels;
    }

    @Override
    public int getCount() {
        if (slideModels!=null)
        return slideModels.size();
        else return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.slide_layout,container,false);
        ImageView banner = (ImageView) view.findViewById(R.id.banner_slide);
        TextView tv = (TextView) view.findViewById(R.id.textslide);

        tv.setText(slideModels.get(position).getChu());
        if (slideModels.get(position).getBanner()!=0)
            banner.setImageResource(slideModels.get(position).getBanner());
        else {
            InputStream is = new ByteArrayInputStream(slideModels.get(position).getAnh());
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            banner.setImageBitmap(bitmap);
        }
        container.addView(view,0);
        return view;
    }
}
