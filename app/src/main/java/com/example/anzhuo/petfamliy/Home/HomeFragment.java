package com.example.anzhuo.petfamliy.Home;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.anzhuo.petfamliy.BmobDataInfo.dog_Disease;
import com.example.anzhuo.petfamliy.BmobDataInfo.dog_Expertise;
import com.example.anzhuo.petfamliy.BmobDataInfo.dog_Type;
import com.example.anzhuo.petfamliy.Home.KindTextFragment.KindMainActivity;
import com.example.anzhuo.petfamliy.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by anzhuo on 2016/10/24.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {
    private ViewPager mViewPager;
    private List<ImageView> mlist;
    private TextView mTextView;
    private LinearLayout mLinearLayout;
    private int click_position;
    private int Click_item;
    private  int expertiseChooseItem;
    private  int diseaseChooseItem;

    // 广告图素材
    private int[] bannerImages = {R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4};
    // 广告语
    private String[] bannerTexts = {"因为专业 所以卓越", "坚持创新 行业领跑", "诚信 专业 双赢", "精细 和谐 大气 开放"};

    // ViewPager适配器与监听器
    private BannerAdapter mAdapter;
    private BannerListener bannerListener;

    // 圆圈标志位
    private int pointIndex = 0;
    // 线程标志
    private boolean isStop = false;

    private static final int MSG = 11;
    BmobQuery<dog_Type> query;
    BmobQuery<dog_Expertise> query3;
    BmobQuery<dog_Disease> query4;

    boolean isCache;

    ImageView kind_img_one;
    TextView kind_one;
    ImageView kind_img_two;
    TextView kind_two;
    ImageView kind_img_three;
    TextView kind_three;
    ImageView kind_img_four;
    TextView kind_four;
    ImageView kind_img_five;
    TextView kind_five;
    ImageView kind_img_six;
    TextView kind_six;

    ImageView encyclopedia_img_one;
    TextView encyclopedia_one;
    ImageView encyclopedia_img_two;
    TextView encyclopedia_two;
    ImageView encyclopedia_img_three;
    TextView encyclopedia_three;
    ImageView encyclopedia_img_four;
    TextView encyclopedia_four;

    ImageView disease_img_one;
    TextView disease_one;
    ImageView disease_img_two;
    TextView disease_two;
    ImageView disease_img_three;
    TextView disease_three;
    ImageView disease_img_four;
    TextView disease_four;

    ImageView experience_img_one;
    TextView experience_one;
    ImageView experience_img_two;
    TextView experience_two;
    ImageView experience_img_three;
    TextView experience_three;
    ImageView experience_img_four;
    TextView experience_four;


    TextView kind_more;
    TextView encyclopedia_more;
    TextView experience_more;
    TextView disease_more;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG:
                    query.setCachePolicy(BmobQuery.CachePolicy.CACHE_ELSE_NETWORK);
                    isCache = query.hasCachedResult(dog_Type.class);
                    query.setMaxCacheAge(TimeUnit.DAYS.toMillis(30));
                    if (isCache && !checkNetworkInfo()) {
                        //--此为举个例子，并不一定按这种方式来设置缓存策略
                        Log.i("textfragment", "缓存存在");
                        query.setCachePolicy(BmobQuery.CachePolicy.CACHE_ONLY);    // 如果有缓存的话，则设置策略为CACHE_ELSE_NETWORK
                    } else if (isCache && checkNetworkInfo()) {
                        query.setCachePolicy(BmobQuery.CachePolicy.NETWORK_ELSE_CACHE);    // 如果没有缓存的话，则设置策略为NETWORK_ELSE_CACHE
                    }
                    query.findObjects(new FindListener<dog_Type>() {
                        @Override
                        public void done(List<dog_Type> mlist, BmobException e) {
                            if (e == null) {

                                kind_one.setText(mlist.get(0).getName());
                                Glide.with(getActivity()).load(mlist.get(0).getImage_head()).into(kind_img_one);
                                kind_two.setText(mlist.get(1).getName());
                                Glide.with(getActivity()).load(mlist.get(1).getImage_head()).into(kind_img_two);
                                kind_three.setText(mlist.get(2).getName());
                                Glide.with(getActivity()).load(mlist.get(2).getImage_head()).into(kind_img_three);
                                kind_four.setText(mlist.get(3).getName());
                                Glide.with(getActivity()).load(mlist.get(3).getImage_head()).into(kind_img_four);
                                kind_five.setText(mlist.get(4).getName());
                                Glide.with(getActivity()).load(mlist.get(4).getImage_head()).into(kind_img_five);
                                kind_six.setText(mlist.get(5).getName());
                                Glide.with(getActivity()).load(mlist.get(5).getImage_head()).into(kind_img_six);

                            } else {
                                Log.i("info", e + "**************");

                            }
                        }
                    });
                    encyclopedia_one.setText("狗狗营养");
                    Glide.with(getActivity()).load("http://www.chongshequ.com/attachment/gou/baike/2016/1024/1f0cf03d06f5faa.jpg").into(encyclopedia_img_one);
                    encyclopedia_two.setText("狗狗美容");
                    Glide.with(getActivity()).load("http://www.chongshequ.com/attachment/gou/baike/2016/1020/efc7954eb6ff799.jpg").into(encyclopedia_img_two);
                    encyclopedia_three.setText("狗狗交配");
                    Glide.with(getActivity()).load("http://www.chongshequ.com/attachment/gou/baike/2016/1014/6bcdccb87c7c3b8.jpg").into(encyclopedia_img_three);
                    encyclopedia_four.setText("狗狗训练");
                    Glide.with(getActivity()).load("http://www.chongshequ.com/attachment/gou/baike/2016/1014/679f9bf06ea288d.jpg").into(encyclopedia_img_four);


                    query3.order("-createdAt");
                    query3.findObjects(new FindListener<dog_Expertise>() {
                        @Override
                        public void done(List<dog_Expertise> mlist, BmobException e) {
                            if (e == null) {
                                experience_one.setText(mlist.get(0).getTitle());
                                Glide.with(getActivity()).load(mlist.get(0).getImage_content()).into(experience_img_one);
                                experience_two.setText(mlist.get(1).getTitle());
                                Glide.with(getActivity()).load(mlist.get(1).getImage_content()).into(experience_img_two);
                                experience_three.setText(mlist.get(2).getTitle());
                                Glide.with(getActivity()).load(mlist.get(2).getImage_content()).into(experience_img_three);
                                experience_four.setText(mlist.get(3).getTitle());
                                Glide.with(getActivity()).load(mlist.get(3).getImage_content()).into(experience_img_four);
                            }
                        }
                    });

                    query4.findObjects(new FindListener<dog_Disease>() {
                        @Override
                        public void done(List<dog_Disease> mlist, BmobException e) {
                            disease_one.setText(mlist.get(0).getDiseaseName());
                            Glide.with(getActivity()).load(mlist.get(0).getImage_pro()).into(disease_img_one);
                            disease_two.setText(mlist.get(1).getDiseaseName());
                            Glide.with(getActivity()).load(mlist.get(1).getImage_pro()).into(disease_img_two);
                            disease_three.setText(mlist.get(2).getDiseaseName());
                            Glide.with(getActivity()).load(mlist.get(2).getImage_pro()).into(disease_img_three);
                            disease_four.setText(mlist.get(3).getDiseaseName());
                            Glide.with(getActivity()).load(mlist.get(3).getImage_pro()).into(disease_img_four);
                        }
                    });
                    break;
            }

        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View mainview = inflater.inflate(R.layout.home_layout, null);
        query = new BmobQuery<dog_Type>();
        query3 = new BmobQuery<dog_Expertise>();
        query4 = new BmobQuery<dog_Disease>();
        mViewPager = (ViewPager) mainview.findViewById(R.id.viewpager);
        mTextView = (TextView) mainview.findViewById(R.id.tv_bannertext);
        mLinearLayout = (LinearLayout) mainview.findViewById(R.id.points);

        kind_more= (TextView) mainview.findViewById(R.id.kind_more);
        encyclopedia_more= (TextView) mainview.findViewById(R.id.encyclopedia_more);
        experience_more= (TextView) mainview.findViewById(R.id.experience_more);
        disease_more= (TextView) mainview.findViewById(R.id.disease_more);

        kind_one = (TextView) mainview.findViewById(R.id.kind_one);
        kind_two = (TextView) mainview.findViewById(R.id.kind_two);
        kind_three = (TextView) mainview.findViewById(R.id.kind_three);
        kind_four = (TextView) mainview.findViewById(R.id.kind_four);
        kind_five = (TextView) mainview.findViewById(R.id.kind_five);
        kind_six = (TextView) mainview.findViewById(R.id.kind_six);

        encyclopedia_one = (TextView) mainview.findViewById(R.id.encyclopedia_one);
        encyclopedia_two = (TextView) mainview.findViewById(R.id.encyclopedia_two);
        encyclopedia_three = (TextView) mainview.findViewById(R.id.encyclopedia_three);
        encyclopedia_four = (TextView) mainview.findViewById(R.id.encyclopedia_four);

        experience_one = (TextView) mainview.findViewById(R.id.experience_one);
        experience_two = (TextView) mainview.findViewById(R.id.experience_two);
        experience_three = (TextView) mainview.findViewById(R.id.experience_three);
        experience_four = (TextView) mainview.findViewById(R.id.experience_four);

        disease_one = (TextView) mainview.findViewById(R.id.disease_one);
        disease_two = (TextView) mainview.findViewById(R.id.disease_two);
        disease_three = (TextView) mainview.findViewById(R.id.disease_three);
        disease_four = (TextView) mainview.findViewById(R.id.disease_four);

        kind_img_one = (ImageView) mainview.findViewById(R.id.kind_img_one);
        kind_img_two = (ImageView) mainview.findViewById(R.id.kind_img_two);
        kind_img_three = (ImageView) mainview.findViewById(R.id.kind_img_three);
        kind_img_four = (ImageView) mainview.findViewById(R.id.kind_img_four);
        kind_img_five = (ImageView) mainview.findViewById(R.id.kind_img_five);
        kind_img_six = (ImageView) mainview.findViewById(R.id.kind_img_six);


        encyclopedia_img_one = (ImageView) mainview.findViewById(R.id.encyclopedia_img_one);
        encyclopedia_img_two = (ImageView) mainview.findViewById(R.id.encyclopedia_img_two);
        encyclopedia_img_three = (ImageView) mainview.findViewById(R.id.encyclopedia_img_three);
        encyclopedia_img_four = (ImageView) mainview.findViewById(R.id.encyclopedia_img_four);

        experience_img_one = (ImageView) mainview.findViewById(R.id.experience_img_one);
        experience_img_two = (ImageView) mainview.findViewById(R.id.experience_img_two);
        experience_img_three = (ImageView) mainview.findViewById(R.id.experience_img_three);
        experience_img_four = (ImageView) mainview.findViewById(R.id.experience_img_four);

        disease_img_one = (ImageView) mainview.findViewById(R.id.disease_img_one);
        disease_img_two = (ImageView) mainview.findViewById(R.id.disease_img_two);
        disease_img_three = (ImageView) mainview.findViewById(R.id.disease_img_three);
        disease_img_four = (ImageView) mainview.findViewById(R.id.disease_img_four);

        kind_img_one.setOnClickListener(this);
        kind_img_two.setOnClickListener(this);
        kind_img_three.setOnClickListener(this);
        kind_img_four.setOnClickListener(this);
        kind_img_five.setOnClickListener(this);
        kind_img_six.setOnClickListener(this);


        encyclopedia_img_one.setOnClickListener(this);
        encyclopedia_img_two.setOnClickListener(this);
        encyclopedia_img_three.setOnClickListener(this);
        encyclopedia_img_four.setOnClickListener(this);

        experience_img_one.setOnClickListener(this);
        experience_img_two.setOnClickListener(this);
        experience_img_three.setOnClickListener(this);
        experience_img_four.setOnClickListener(this);

        disease_img_one.setOnClickListener(this);
        disease_img_two.setOnClickListener(this);
        disease_img_three.setOnClickListener(this);
        disease_img_four.setOnClickListener(this);

        kind_more.setOnClickListener(this);
        encyclopedia_more.setOnClickListener(this);
        experience_more.setOnClickListener(this);
        disease_more.setOnClickListener(this);
        initData();
        initAction();
        starThread();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isStop) {
                    SystemClock.sleep(2000);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
                        }
                    });
                }
            }

        }).start();

        return mainview;
    }

    public void starThread() {
        handler.sendEmptyMessage(MSG);
    }


    /**
     * 初始化事件
     */
    private void initAction() {
        bannerListener = new BannerListener();
        mViewPager.setOnPageChangeListener(bannerListener);
        //取中间数来作为起始位置
        int index = (Integer.MAX_VALUE / 2) - (Integer.MAX_VALUE / 2 % mlist.size());
        //用来出发监听器
        mViewPager.setCurrentItem(index);
        mLinearLayout.getChildAt(pointIndex).setEnabled(true);


    }

    /**
     * 初始化数据
     */
    private void initData() {
        mlist = new ArrayList<ImageView>();
        View view;
        LayoutParams params;
        for (int i = 0; i < bannerImages.length; i++) {
            // 设置广告图
            ImageView imageView = new ImageView(getActivity());
            imageView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setBackgroundResource(bannerImages[i]);
            mlist.add(imageView);
            // 设置圆圈点
            view = new View(getActivity());
            params = new LayoutParams(5, 5);
            params.leftMargin = 10;
            view.setBackgroundResource(R.drawable.point_background);
            view.setLayoutParams(params);
            view.setEnabled(false);
            mLinearLayout.addView(view);
        }
        mAdapter = new BannerAdapter(mlist);
        mViewPager.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.kind_more:
                Intent intentKindMore=new Intent(getActivity(),KindListViewActivity.class);
                startActivity(intentKindMore);
                break;
            case R.id.kind_img_one:
                 click_position=0;
                Intent intent=new Intent(getActivity(), KindMainActivity.class);
                intent.putExtra("number",click_position);
                startActivity(intent);
                break;
            case R.id.kind_img_two:
                click_position=1;
                Intent intent1=new Intent(getActivity(), KindMainActivity.class);
                intent1.putExtra("number",click_position);
                startActivity(intent1);
                break;
            case R.id.kind_img_three:
                click_position=2;
                Intent intent2=new Intent(getActivity(), KindMainActivity.class);
                intent2.putExtra("number",click_position);
                startActivity(intent2);
                break;
            case R.id.kind_img_four:
                click_position=3;
                Intent intent3=new Intent(getActivity(), KindMainActivity.class);
                intent3.putExtra("number",click_position);
                startActivity(intent3);
                break;
            case R.id.kind_img_five:
                click_position=4;
                Intent intent4=new Intent(getActivity(), KindMainActivity.class);
                intent4.putExtra("number",click_position);
                startActivity(intent4);
                break;
            case R.id.kind_img_six:
                click_position=5;
                Intent intent5=new Intent(getActivity(), KindMainActivity.class);
                intent5.putExtra("number",click_position);
                startActivity(intent5);
                break;
            case R.id.encyclopedia_more:
                Intent intentEncyclopediaMore=new Intent(getActivity(), EncyclopediaListViewActivity.class);
                startActivity(intentEncyclopediaMore);
                break;
            case R.id.encyclopedia_img_one:
                Click_item=0;
                Intent encyclopediaOne=new Intent(getActivity(),NutritionActivity.class);
                encyclopediaOne.putExtra("itemposition",Click_item);
                startActivity(encyclopediaOne);
                break;
            case R.id.encyclopedia_img_two:
                Click_item=1;
                Intent encyclopediaTwo=new Intent(getActivity(),NutritionActivity.class);
                encyclopediaTwo.putExtra("itemposition",Click_item);
                startActivity(encyclopediaTwo);
                break;
            case R.id.encyclopedia_img_three:
                Click_item=2;
                Intent encyclopediaThree=new Intent(getActivity(),NutritionActivity.class);
                encyclopediaThree.putExtra("itemposition",Click_item);
                startActivity(encyclopediaThree);
                break;
            case R.id.encyclopedia_img_four:
                Click_item=3;
                Intent encyclopediaFour=new Intent(getActivity(),NutritionActivity.class);
                encyclopediaFour.putExtra("itemposition",Click_item);
                startActivity(encyclopediaFour);
                break;
            case R.id.experience_more:
                Intent experienceIntent=new Intent(getActivity(),ExpertiseListViewActivity.class);
                startActivity(experienceIntent);
                break;
            case R.id.experience_img_one:
                Intent  experienceOne=new Intent(getActivity(),ExpertiseContentActivity.class);
                expertiseChooseItem=0;
                experienceOne.putExtra("expertiseChooseItem",expertiseChooseItem);
                startActivity(experienceOne);
                break;
            case R.id.experience_img_two:
                Intent  experienceTwo=new Intent(getActivity(),ExpertiseContentActivity.class);
                expertiseChooseItem=1;
                experienceTwo.putExtra("expertiseChooseItem",expertiseChooseItem);
                startActivity(experienceTwo);
                break;
            case R.id.experience_img_three:
                Intent  experienceThree=new Intent(getActivity(),ExpertiseContentActivity.class);
                expertiseChooseItem=2;
                experienceThree.putExtra("expertiseChooseItem",expertiseChooseItem);
                startActivity(experienceThree);
                break;
            case R.id.experience_img_four:
                Intent  experienceFour=new Intent(getActivity(),ExpertiseContentActivity.class);
                expertiseChooseItem=3;
                experienceFour.putExtra("expertiseChooseItem",expertiseChooseItem);
                startActivity(experienceFour);
                break;

            case R.id.disease_more:
                Intent diseaseMore=new Intent(getActivity(),DiseaseListViewActivity.class);
                  startActivity(diseaseMore);
                break;
            case R.id.disease_img_one:
                Intent  diseaseOne=new Intent(getActivity(),DiseaseContentActivity.class);
                diseaseChooseItem=0;
                diseaseOne.putExtra("diseaseChooseItem",diseaseChooseItem);
                startActivity(diseaseOne);
                break;
            case R.id.disease_img_two:
                Intent  diseaseTwo=new Intent(getActivity(),DiseaseContentActivity.class);
                diseaseChooseItem=1;
                diseaseTwo.putExtra("diseaseChooseItem",diseaseChooseItem);
                startActivity(diseaseTwo);
                break;
            case R.id.disease_img_three:
                Intent  diseaseThree=new Intent(getActivity(),DiseaseContentActivity.class);
                diseaseChooseItem=2;
                diseaseThree.putExtra("diseaseChooseItem",diseaseChooseItem);
                startActivity(diseaseThree);
                break;
            case R.id.disease_img_four:
                Intent  diseaseFour=new Intent(getActivity(),DiseaseContentActivity.class);
                diseaseChooseItem=3;
                diseaseFour.putExtra("diseaseChooseItem",diseaseChooseItem);
                startActivity(diseaseFour);
                break;






        }



    }

    /**
     * 初始化View操作
     */


    class BannerAdapter extends PagerAdapter {
        private List<ImageView> mList;

        public BannerAdapter(List<ImageView> list) {
            this.mList = list;
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mList.get(position % mList.size()));
            return mList.get(position % mList.size());
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mList.get(position % mList.size()));
        }

    }

    class BannerListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int position) {

            int newPosition = position % bannerImages.length;
            mTextView.setText(bannerTexts[newPosition]);
            mLinearLayout.getChildAt(newPosition).setEnabled(true);
            mLinearLayout.getChildAt(pointIndex).setEnabled(false);
            // 更新标志位
            pointIndex = newPosition;

        }

    }

    @Override
    public void onDestroy() {
        // 关闭定时器
        isStop = true;
        super.onDestroy();
    }


    public boolean checkNetworkInfo() {
        ConnectivityManager conMan = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo.State mobile = conMan.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
        NetworkInfo.State wifi = conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
        if (mobile == NetworkInfo.State.CONNECTED || mobile == NetworkInfo.State.CONNECTING)
            return true;
        if (wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING)
            return true;
        return false;
    }

}
