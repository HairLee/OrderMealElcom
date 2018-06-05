package com.elcom.com.quickbloxex.view.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.elcom.com.quickbloxex.R;
import com.elcom.com.quickbloxex.core.base.BaseActivity;
import com.elcom.com.quickbloxex.databinding.ActivitySourcesBinding;
import com.elcom.com.quickbloxex.view.adapter.DemoCollectionPagerAdapter;
import com.elcom.com.quickbloxex.view.adapter.TestPagerAdapter;
import com.elcom.com.quickbloxex.viewmodel.OrderMealViewModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class OrderMealScheduleActivity extends BaseActivity<OrderMealViewModel, ActivitySourcesBinding> {


    private static final String KEY_ITEM_ID = "item:article";
    private FragmentManager fragmentManager;
    private int currentPosOfDay = 0;
    @Override
    protected Class<OrderMealViewModel> getViewModel() {
        return OrderMealViewModel.class;
    }

    @Override
    protected void onCreate(Bundle instance, OrderMealViewModel viewModel, ActivitySourcesBinding binding) {


        Calendar calSt = Calendar.getInstance();
        calSt.set(Calendar.YEAR, 2018);
        calSt.set(Calendar.MONTH, Calendar.JANUARY);
        calSt.set(Calendar.DAY_OF_MONTH, 1);
        Date stDate = calSt.getTime();

        Calendar calEnd = Calendar.getInstance();
        calEnd.set(Calendar.YEAR, 2018);
        calEnd.set(Calendar.MONTH, Calendar.UNDECIMBER);
        calEnd.set(Calendar.DAY_OF_MONTH, 1);
        Date stEnd = calEnd.getTime();


        Calendar calendar = Calendar.getInstance();
        Date CurremtDate = calendar.getTime();

        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());

        Log.e("hailpt", " OrderMealScheduleActivity Current Day  "+dateFormat.format(CurremtDate));


//        getAllDayOfMonth();

        List<Date> mDates =  getDatesBetweenUsingJava7(stDate, stEnd);
        for (int i = mDates.size() - 1; i >= 0; i--) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(getDatesBetweenUsingJava7(stDate,stEnd).get(i));
            int day= cal.get(Calendar.WEEK_OF_MONTH);

            Log.e("hailpt", " getDatesBetweenUsingJava7 DAY_OF_WEEK "+day);

            int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY){
                mDates.remove(i);
            }
        }

        for (int i = 0; i < mDates.size(); i++) {
            if (dateFormat.format(mDates.get(i)).equalsIgnoreCase(dateFormat.format(CurremtDate))){
                currentPosOfDay = i;
            }
        }


        Log.e("hailpt", " getDatesBetweenUsingJava7 Size "+mDates.size());

        init(viewModel);

        setupViewPager(mDates);




    }

    private void setupViewPager(List<Date> pDates){
        DemoCollectionPagerAdapter mDemoCollectionPagerAdapter = new DemoCollectionPagerAdapter(getSupportFragmentManager(),pDates);
        ViewPager mViewPager = findViewById(R.id.pager);
        mViewPager.setAdapter(mDemoCollectionPagerAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mViewPager.postDelayed(new Runnable() {
            @Override
            public void run() {
//                mViewPager.setCurrentItem(currentPosOfDay);
            }
        }, 50);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_sources;
    }

    public static void start(Context context, int id) {
        Intent starter = new Intent(context, OrderMealScheduleActivity.class);
        starter.putExtra(KEY_ITEM_ID, id);
        context.startActivity(starter);
    }

    private void init(OrderMealViewModel viewModel) {

        viewModel.getArticleList().observe(this, articles ->
                Log.e("hailpt"," OrderMealScheduleActivity "+articles.size())
        );

        viewModel.getSourceList().observe(this, sources -> {
            if (sources == null || sources.size() < 1) {
//                SourcesActivity.start(this);
            }
        });

        viewModel.getDayList().observe(this, days -> {
            Log.e("hailpt"," OrderMealScheduleActivity getDayList "+days.size());
        });

    }



    public static List<Date> getDatesBetweenUsingJava7(
            Date startDate, Date endDate) {
        List<Date> datesInRange = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startDate);

        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(endDate);

        while (calendar.before(endCalendar)) {
            Date result = calendar.getTime();
            datesInRange.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        return datesInRange;
    }

    public void getAllDayOfMonth(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(cal.getTime());
        cal.set(Calendar.DAY_OF_MONTH, 1);
        int myMonth=cal.get(Calendar.MONTH);
        Log.e("hailpt"," getAllDayOfMonth myMonth "+myMonth);
        while (myMonth == cal.get(Calendar.MONTH)) {
            System.out.print(cal.getTime());
            Log.e("hailpt"," getAllDayOfMonth "+cal.getTime());
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
    }
}
