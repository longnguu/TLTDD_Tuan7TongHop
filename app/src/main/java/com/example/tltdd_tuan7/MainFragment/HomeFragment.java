package com.example.tltdd_tuan7.MainFragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.tltdd_tuan7.Adapter.SlideAdapter;
import com.example.tltdd_tuan7.Adapter.SlideModel;
import com.example.tltdd_tuan7.Class.BTP;
import com.example.tltdd_tuan7.MainActivity;
import com.example.tltdd_tuan7.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator3;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ViewPager sliderBanner;
    CircleIndicator3 circleIndicator3;

    public static SlideAdapter slideAdapter;
    public static List<SlideModel> slideModelList= new ArrayList<SlideModel>();
    private Timer timer;
    int current = 0;
    final private  long delay=3000;
    final private long PERIOD=3000;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFracment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        sliderBanner = (ViewPager) view.findViewById(R.id.viewpagerhome);
        slideModelList.add(new SlideModel(R.drawable.img_4,null,"Quảng Trị"));
        slideModelList.add(new SlideModel(R.drawable.img_1,null,"Hà Nội"));
        slideModelList.add(new SlideModel(R.drawable.img_2,null,"Đà Nẵng"));
        slideModelList.add(new SlideModel(R.drawable.img_3,null,"Thành phố Hồ Chí Minh"));
        CardView cv2= (CardView) view.findViewById(R.id.cardviewhomefr2);
        CardView cv1= (CardView) view.findViewById(R.id.cardviewhomefr1);
        CardView cv3= (CardView) view.findViewById(R.id.cardviewhomefr3);
        CardView cv4= (CardView) view.findViewById(R.id.cardviewhomefr4);

        cv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Settings.ACTION_SETTINGS),null);
            }
        });
        cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(getContext().getPackageManager().getLaunchIntentForPackage("com.facebook.katana")));
                Intent lauchIntent = getActivity().getPackageManager().getLaunchIntentForPackage("com.ohvui.cotuong");
                if (lauchIntent!=null)
                startActivity(lauchIntent);
                else
                    System.out.println("abc");
            }
        });
        cv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        cv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.bottomNavigationView.getMenu().findItem(R.id.navprofile).setChecked(true);
                MainActivity.viewPager.setCurrentItem(2);
            }
        });
        if (BTP.slideModelList.size()==0) {
            BTP.slideModelList=slideModelList;
        }

        System.out.println("a");
        sliderBanner.setOffscreenPageLimit(3);
        sliderBanner.setClipToPadding(false);
        sliderBanner.setClipChildren(false);
        slideAdapter = new SlideAdapter(getActivity(),BTP.slideModelList);
        sliderBanner.setAdapter(slideAdapter);

//        autoSlide();
//        ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                current = position ;
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//                if (state==ViewPager.SCROLL_STATE_IDLE){
//                    pageLooper();
//                }
//            }
//        };
//        sliderBanner.addOnPageChangeListener(onPageChangeListener);
        startBanner();
        sliderBanner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                stopBanner();
                if (motionEvent.getAction()==MotionEvent.ACTION_UP){
                    startBanner();
                }
                return false;
            }
        });

    }
    private  void pageLooper(){
    }
    private void startBanner(){
        Handler handler = new Handler();
        Runnable update = new Runnable() {
            @Override
            public void run() {
                current = sliderBanner.getCurrentItem();
                if (current< BTP.slideModelList.size()-1){
                    current ++;
                    try {
                        sliderBanner.setCurrentItem(current);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }else  {
                    try {
                        sliderBanner.setCurrentItem(0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        },500,2000);
    }
    private void stopBanner(){
        if (timer!=null) timer.cancel();
    }
    private void autoSlide(){
        if (slideModelList == null || slideModelList.isEmpty() || sliderBanner == null){
            return;
        }
        //Init timer
        if (timer == null){
            System.out.println("a");
            return;
        }
        Handler handler = new Handler();
        Runnable update = new Runnable() {
            @Override
            public void run() {
                current = sliderBanner.getCurrentItem();
                int totalItem = slideModelList.size()-1;
                if (current< totalItem){
                    current ++;
                    sliderBanner.setCurrentItem(current);
                }else  {
                    sliderBanner.setCurrentItem(0);
                }
            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        },100,1000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(timer!=null) {
            timer.cancel();
        }
    }
}