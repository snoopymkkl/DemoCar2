package com.example.administrator.democar2;


import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.democar2.fragments.FindFragment;
import com.example.administrator.democar2.fragments.NewsFragment;
import com.example.administrator.democar2.fragments.PriceFragment;
import com.example.administrator.democar2.fragments.QuestionFragment;
import com.example.administrator.democar2.fragments.SelfFragment;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * 主Activity, 控制五个标签的选择, Fragment的切换
 */

public class MainActivity extends FragmentActivity {
    //资讯
    @ViewInject(R.id.car_llyt_news)
    private LinearLayout car_llyt_news;
    @ViewInject(R.id.car_iv_news)
    private ImageView car_iv_news;
    @ViewInject(R.id.car_tv_news)
    private TextView car_tv_news;
    //找车
    @ViewInject(R.id.car_llyt_find)
    private LinearLayout car_llyt_find;
    @ViewInject(R.id.car_iv_find)
    private ImageView car_iv_find;
    @ViewInject(R.id.car_tv_find)
    private TextView car_tv_find;
    //降价
    @ViewInject(R.id.car_llyt_price)
    private LinearLayout car_llyt_price;
    @ViewInject(R.id.car_iv_price)
    private ImageView car_iv_price;
    @ViewInject(R.id.car_tv_price)
    private TextView car_tv_price;
    //问答
    @ViewInject(R.id.car_llyt_question)
    private LinearLayout car_llyt_question;
    @ViewInject(R.id.car_iv_question)
    private ImageView car_iv_question;
    @ViewInject(R.id.car_tv_question)
    private TextView car_tv_question;
    //我的
    @ViewInject(R.id.car_llyt_self)
    private LinearLayout car_llyt_self;
    @ViewInject(R.id.car_iv_self)
    private ImageView car_iv_self;
    @ViewInject(R.id.car_tv_self)
    private TextView car_tv_self;

    private int chooseIndex = -1; //标签索引
    //Fragment事务
    private FragmentTransaction ft;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        x.view().inject(this);
        //默认选中第一个标签
        car_iv_news.setSelected(true);
        car_tv_news.setTextColor(getResources().getColor(R.color.car_cl_choose));
    }

    //这里点击整个模块, 使用LinearLayout ID
    @Event(value = {
            R.id.car_llyt_news,
            R.id.car_llyt_find,
            R.id.car_llyt_price,
            R.id.car_llyt_question,
            R.id.car_llyt_self}, type = View.OnClickListener.class)
    private void viewOnClick(View view) {
        //初始化FragmentManger
        ft = getSupportFragmentManager().beginTransaction();
        switch (view.getId()) {
            case R.id.car_llyt_news: //资讯
                //是否当前已选中, 未选中才加载, 以免浪费资源重复加载
                if (chooseIndex != 0) {
                    chooseIndex = 0;
                    tabBgChange(chooseIndex);
                    //替换当前Fragment
                    ft.replace(R.id.car_flyt_content,
                            /**
                             * 替换当前的Fragment,instantiate创建Fragment实例, 相当调用无参构造
                             * 参数一: context, 参数二: Fragment类名 参数三: bundle
                             */
                            NewsFragment.instantiate(
                                    MainActivity.this, NewsFragment.class.getName(), null),
                            "NewsFragment");
                }
                break;
            case R.id.car_llyt_find: //找车
                if (chooseIndex != 1) {
                    chooseIndex = 1;
                    tabBgChange(chooseIndex);
                    ft.replace(R.id.car_flyt_content,
                            FindFragment.instantiate(MainActivity.this, FindFragment.class.getName(), null),
                            "FindFragment");
                }
                break;
            case R.id.car_llyt_price: //降价
                if (chooseIndex != 2) {
                    chooseIndex = 2;
                    ft.replace(R.id.car_flyt_content,
                            PriceFragment.instantiate(MainActivity.this, PriceFragment.class.getName(), null),
                            "PriceFragment");
                    tabBgChange(chooseIndex);
                }

                break;
            case R.id.car_llyt_question: //问答
                if(chooseIndex != 3) {
                    chooseIndex = 3;
                    tabBgChange(chooseIndex);
                    ft.replace(R.id.car_flyt_content,
                            QuestionFragment.instantiate(MainActivity.this, QuestionFragment.class.getName(), null),
                            "QuestionFragment");
                }
                break;
            case R.id.car_llyt_self: //我的
                if(chooseIndex != 4){
                    chooseIndex = 4;
                    tabBgChange(chooseIndex);
                    ft.replace(R.id.car_flyt_content, SelfFragment.instantiate(MainActivity.this, SelfFragment.class.getName(), null),
                            "SelfFragment");
                }
                break;
            default:
                break;

        }
        ft.commit();
    }

    public void tabBgChange(int index) {
        switch (index) {
            case 0:

                car_iv_news.setSelected(true);
                car_tv_news.setTextColor(getResources().getColor(R.color.car_cl_choose));

                car_iv_find.setSelected(false);
                car_tv_find.setTextColor(getResources().getColor(R.color.car_cl_unchoose));
                car_iv_price.setSelected(false);
                car_tv_price.setTextColor(getResources().getColor(R.color.car_cl_unchoose));
                car_iv_question.setSelected(false);
                car_tv_question.setTextColor(getResources().getColor(R.color.car_cl_unchoose));
                car_iv_self.setSelected(false);
                car_tv_self.setTextColor(getResources().getColor(R.color.car_cl_unchoose));

                break;
            case 1:

                car_iv_find.setSelected(true);
                car_tv_find.setTextColor(getResources().getColor(R.color.car_cl_choose));

                car_iv_news.setSelected(false);
                car_tv_news.setTextColor(getResources().getColor(R.color.car_cl_unchoose));
                car_iv_price.setSelected(false);
                car_tv_price.setTextColor(getResources().getColor(R.color.car_cl_unchoose));
                car_iv_question.setSelected(false);
                car_tv_question.setTextColor(getResources().getColor(R.color.car_cl_unchoose));
                car_iv_self.setSelected(false);
                car_tv_self.setTextColor(getResources().getColor(R.color.car_cl_unchoose));

                break;
            case 2:

                car_iv_price.setSelected(true);
                car_tv_price.setTextColor(getResources().getColor(R.color.car_cl_choose));

                car_iv_find.setSelected(false);
                car_tv_find.setTextColor(getResources().getColor(R.color.car_cl_unchoose));
                car_iv_news.setSelected(false);
                car_tv_news.setTextColor(getResources().getColor(R.color.car_cl_unchoose));
                car_iv_question.setSelected(false);
                car_tv_question.setTextColor(getResources().getColor(R.color.car_cl_unchoose));
                car_iv_self.setSelected(false);
                car_tv_self.setTextColor(getResources().getColor(R.color.car_cl_unchoose));

                break;
            case 3:

                car_iv_question.setSelected(true);
                car_tv_question.setTextColor(getResources().getColor(R.color.car_cl_choose));

                car_iv_price.setSelected(false);
                car_tv_price.setTextColor(getResources().getColor(R.color.car_cl_unchoose));
                car_iv_find.setSelected(false);
                car_tv_find.setTextColor(getResources().getColor(R.color.car_cl_unchoose));
                car_iv_news.setSelected(false);
                car_tv_news.setTextColor(getResources().getColor(R.color.car_cl_unchoose));
                car_iv_self.setSelected(false);
                car_tv_self.setTextColor(getResources().getColor(R.color.car_cl_unchoose));

                break;
            case 4:

                car_iv_self.setSelected(true);
                car_tv_self.setTextColor(getResources().getColor(R.color.car_cl_choose));

                car_iv_question.setSelected(false);
                car_tv_question.setTextColor(getResources().getColor(R.color.car_cl_unchoose));
                car_iv_price.setSelected(false);
                car_tv_price.setTextColor(getResources().getColor(R.color.car_cl_unchoose));
                car_iv_find.setSelected(false);
                car_tv_find.setTextColor(getResources().getColor(R.color.car_cl_unchoose));
                car_iv_news.setSelected(false);
                car_tv_news.setTextColor(getResources().getColor(R.color.car_cl_unchoose));
                car_iv_question.setSelected(false);
                car_tv_question.setTextColor(getResources().getColor(R.color.car_cl_unchoose));

                break;
        }
    }
    //第一次点击事件, 方法中获取实际数值
    private long firstTime = 0;
    //双击退出客户端
    @Override
    public void onBackPressed() {
        long secondTime = System.currentTimeMillis();
        if (secondTime - firstTime > 1000) { //按第一次返回键弹出提示
            Toast.makeText(this, "在按一次退出客户端", Toast.LENGTH_SHORT).show();
            //更新firstTime
            firstTime = secondTime;//将第一次按返回键的时间记录下来
        } else {
            finish();
        }
    }
}

