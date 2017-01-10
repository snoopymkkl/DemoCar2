package com.example.administrator.democar2.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.democar2.R;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 逻辑:
 * 1.父Fragment资讯, 嵌套了6个个子Fragment
 * 2.子Fragment通过ViewPager切换
 * 3.ViewPager用法
 *      1.声明子Fragment, 存入List集合中
 *      2.给ViewPager设置FragmentStatePagerAdapter, 覆写的方法中切换Fragment
 * 4.滑动ViewPager时, 子标签也对应滑动
 *      - 内部类继承OnPageChangeListener, onPageSelected()监听当前选中页面,在该方法中改变子标签的属性(背景图)
 *        实际上并不是选中对应的子标签, 而是改变子标签背景, 造成选中的假象
 * 5.点击字标签, 切换到对应的ViewPager
 *      - 点击事件中, 直接使用ViewPager.setCurrentItem()切换
 */

public class NewsFragment extends Fragment {

    @ViewInject(R.id.news_tv_important) //要闻
    private TextView news_tv_important;
    @ViewInject(R.id.news_tv_car) //新车
    private TextView news_tv_car;
    @ViewInject(R.id.news_tv_buy) //导购
    private TextView news_tv_buy;
    @ViewInject(R.id.news_tv_try) //试车
    private TextView news_tv_try;
    @ViewInject(R.id.news_tv_picture) //图解
    private TextView news_tv_picture;
    @ViewInject(R.id.news_tv_market) //行业
    private TextView news_tv_market;
    @ViewInject(R.id.news_vp) //ViewPager
    private ViewPager news_vp;

    private List<Fragment> fragmentList = new ArrayList<>();
    private NewsImportantFragment importantFragment;
    private NewsCarFragment carFragment;
    private NewsBuyFragment buyFragment;
    private NewsTryFragment tryFragment;
    private NewsPictureFragment pictureFragment;
    private NewsMarketFragment marketFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_main, null);
        x.view().inject(this, view);
        initParams();
        return view;
    }

    //初始化子Fragment, 存入List中
    private void initParams() {

        importantFragment = new NewsImportantFragment();
        carFragment = new NewsCarFragment();
        buyFragment = new NewsBuyFragment();
        tryFragment = new NewsTryFragment();
        pictureFragment = new NewsPictureFragment();
        marketFragment = new NewsMarketFragment();

        fragmentList.add(importantFragment);
        fragmentList.add(carFragment);
        fragmentList.add(buyFragment);
        fragmentList.add(tryFragment);
        fragmentList.add(pictureFragment);
        fragmentList.add(marketFragment);

        //这里使用getChildFragmentManager, 获得子FragmentManager
        news_vp.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });
        news_vp.setCurrentItem(0);
        news_vp.setOnPageChangeListener(new DefinePageChangeListener());
    }

    //点击子标签, 切换到对应的ViewPager
    @Event(value = {R.id.news_tv_important, R.id.news_tv_car, R.id.news_tv_buy, R.id.news_tv_try, R.id.news_tv_picture, R.id.news_tv_market},
    type = View.OnClickListener.class)
    private void OnItemClick(View view){
        switch (view.getId()){
            //选中某个标签, 通过setCurrentItem(),ViewPager切换到对应页面
            case R.id.news_tv_important:
                news_vp.setCurrentItem(0);
                break;
            case R.id.news_tv_car:
                news_vp.setCurrentItem(1);
                break;
            case R.id.news_tv_buy:
                news_vp.setCurrentItem(2);
                break;
            case R.id.news_tv_try:
                news_vp.setCurrentItem(3);
                break;
            case R.id.news_tv_picture:
                news_vp.setCurrentItem(4);
                break;
            case R.id.news_tv_market:
                news_vp.setCurrentItem(5);
                break;
            default:
                break;
        }
    }

    //ViewPager视图切换监听器, 切换视图时, 子标签同步切换
    public class DefinePageChangeListener implements ViewPager.OnPageChangeListener{
        //新的页面被选中时, 子标签同步切换
        @Override
        public void onPageSelected(int position) {
            switch (position){
                case 0: //要闻
                    //选中子标签背景图
                    news_tv_important.setBackgroundResource(R.drawable.news_ic_selected);
                    //其他子标签设置为透明背景色
                    news_tv_car.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    news_tv_buy.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    news_tv_try.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    news_tv_picture.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    news_tv_market.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    news_tv_important.setTextColor(getResources().getColor(R.color.news_cl_choose));
                    news_tv_car.setTextColor(getResources().getColor(R.color.news_cl_unchoose));
                    news_tv_buy.setTextColor(getResources().getColor(R.color.news_cl_unchoose));
                    news_tv_try.setTextColor(getResources().getColor(R.color.news_cl_unchoose));
                    news_tv_picture.setTextColor(getResources().getColor(R.color.news_cl_unchoose));
                    news_tv_market.setTextColor(getResources().getColor(R.color.news_cl_unchoose));

                    break;
                // 新车
                case 1:

                    news_tv_car.setBackgroundResource(R.drawable.news_ic_selected);
                    news_tv_important.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    news_tv_buy.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    news_tv_try.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    news_tv_picture.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    news_tv_market.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    news_tv_car.setTextColor(getResources().getColor(R.color.news_cl_choose));
                    news_tv_important.setTextColor(getResources().getColor(R.color.news_cl_unchoose));
                    news_tv_buy.setTextColor(getResources().getColor(R.color.news_cl_unchoose));
                    news_tv_try.setTextColor(getResources().getColor(R.color.news_cl_unchoose));
                    news_tv_picture.setTextColor(getResources().getColor(R.color.news_cl_unchoose));
                    news_tv_market.setTextColor(getResources().getColor(R.color.news_cl_unchoose));

                    break;
                // 导购
                case 2:

                    news_tv_buy.setBackgroundResource(R.drawable.news_ic_selected);
                    news_tv_important.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    news_tv_car.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    news_tv_try.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    news_tv_picture.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    news_tv_market.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    news_tv_buy.setTextColor(getResources().getColor(R.color.news_cl_choose));
                    news_tv_important.setTextColor(getResources().getColor(R.color.news_cl_unchoose));
                    news_tv_car.setTextColor(getResources().getColor(R.color.news_cl_unchoose));
                    news_tv_try.setTextColor(getResources().getColor(R.color.news_cl_unchoose));
                    news_tv_picture.setTextColor(getResources().getColor(R.color.news_cl_unchoose));
                    news_tv_market.setTextColor(getResources().getColor(R.color.news_cl_unchoose));

                    break;
                // 试车
                case 3:

                    news_tv_try.setBackgroundResource(R.drawable.news_ic_selected);
                    news_tv_important.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    news_tv_car.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    news_tv_buy.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    news_tv_picture.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    news_tv_market.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    news_tv_try.setTextColor(getResources().getColor(R.color.news_cl_choose));
                    news_tv_important.setTextColor(getResources().getColor(R.color.news_cl_unchoose));
                    news_tv_car.setTextColor(getResources().getColor(R.color.news_cl_unchoose));
                    news_tv_buy.setTextColor(getResources().getColor(R.color.news_cl_unchoose));
                    news_tv_picture.setTextColor(getResources().getColor(R.color.news_cl_unchoose));
                    news_tv_market.setTextColor(getResources().getColor(R.color.news_cl_unchoose));

                    break;
                // 图解
                case 4:

                    news_tv_picture.setBackgroundResource(R.drawable.news_ic_selected);
                    news_tv_important.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    news_tv_car.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    news_tv_buy.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    news_tv_try.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    news_tv_market.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    news_tv_picture.setTextColor(getResources().getColor(R.color.news_cl_choose));
                    news_tv_important.setTextColor(getResources().getColor(R.color.news_cl_unchoose));
                    news_tv_car.setTextColor(getResources().getColor(R.color.news_cl_unchoose));
                    news_tv_buy.setTextColor(getResources().getColor(R.color.news_cl_unchoose));
                    news_tv_try.setTextColor(getResources().getColor(R.color.news_cl_unchoose));
                    news_tv_market.setTextColor(getResources().getColor(R.color.news_cl_unchoose));

                    break;
                // 行情
                case 5:

                    news_tv_market.setBackgroundResource(R.drawable.news_ic_selected);
                    news_tv_important.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    news_tv_car.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    news_tv_buy.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    news_tv_try.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    news_tv_picture.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    news_tv_market.setTextColor(getResources().getColor(R.color.news_cl_choose));
                    news_tv_important.setTextColor(getResources().getColor(R.color.news_cl_unchoose));
                    news_tv_car.setTextColor(getResources().getColor(R.color.news_cl_unchoose));
                    news_tv_buy.setTextColor(getResources().getColor(R.color.news_cl_unchoose));
                    news_tv_try.setTextColor(getResources().getColor(R.color.news_cl_unchoose));
                    news_tv_picture.setTextColor(getResources().getColor(R.color.news_cl_unchoose));
                    break;
                default:
                    break;
            }
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}























