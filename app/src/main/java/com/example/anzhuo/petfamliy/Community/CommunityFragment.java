package com.example.anzhuo.petfamliy.Community;

import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.anzhuo.petfamliy.Adapter.CommunityAdapter;
import com.example.anzhuo.petfamliy.Community.PublishFragment.PhotoFragment;
import com.example.anzhuo.petfamliy.Community.PublishFragment.RecommendFragment;
import com.example.anzhuo.petfamliy.Community.PublishFragment.VideoFragment;
import com.example.anzhuo.petfamliy.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anzhuo on 2016/10/24.
 */
public class CommunityFragment extends Fragment {
    ImageView iv_myhead;
    ImageView community_write;
    RadioGroup rg_matter;
    RadioButton rb_recommend;
    RadioButton rb_newest;
    ViewPager vp_view;
    PhotoFragment photoFragment;
    RecommendFragment recommendFragment;
    CommunityAdapter communityAdapter;
    List<Fragment> list;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.community_layout,null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iv_myhead= (ImageView) view.findViewById(R.id.iv_myhead);
        community_write= (ImageView) view.findViewById(R.id.community_write);
        rg_matter= (RadioGroup) view.findViewById(R.id.rg_matter);
        rb_recommend= (RadioButton) view.findViewById(R.id.rb_recommend);
        rb_newest= (RadioButton) view.findViewById(R.id.rb_newest);
        vp_view= (ViewPager) view.findViewById(R.id.vp_view);
        list=new ArrayList<>();
        photoFragment=new PhotoFragment();
        recommendFragment=new RecommendFragment();
        list.add(recommendFragment);
        list.add(photoFragment);
        communityAdapter=new CommunityAdapter(getFragmentManager(),list);
        vp_view.setAdapter(communityAdapter);
        community_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),ContributeActivity.class);
                startActivity(intent);
            }
        });
        rg_matter.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_recommend:
                        vp_view.setCurrentItem(0);
                        break;
                    case R.id.rb_newest:
                        vp_view.setCurrentItem(1);
                        break;
                }
            }
        });
        vp_view.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        rg_matter.check(R.id.rb_recommend);
                        break;
                    case 1:
                        rg_matter.check(R.id.rb_newest);
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}
