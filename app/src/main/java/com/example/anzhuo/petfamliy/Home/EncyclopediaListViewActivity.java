package com.example.anzhuo.petfamliy.Home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.anzhuo.petfamliy.Adapter.EncyclopediaBaseAdapter;
import com.example.anzhuo.petfamliy.AdapterInfo.EncyclopediaInfo;
import com.example.anzhuo.petfamliy.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anzhuo on 2016/10/27.
 */
public class EncyclopediaListViewActivity extends Activity {
    List<EncyclopediaInfo> list;
    EncyclopediaBaseAdapter encyclopediaBaseAdapter;
    ListView encyclopedia_lv_list;
    private int Click_item;
    ImageView encyclopedia_iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.encyclopedia_layout);
        encyclopedia_iv_back= (ImageView) findViewById(R.id.encyclopedia_iv_back);
        encyclopedia_iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        encyclopedia_lv_list = (ListView) findViewById(R.id.encyclopedia_lv_list);
        list = new ArrayList<>();
        EncyclopediaInfo encyclopediaInfo1 = new EncyclopediaInfo();
        encyclopediaInfo1.setEncyclopedia_iv_content(R.drawable.food);
        encyclopediaInfo1.setEncyclopedia_title("狗狗营养");
        list.add(encyclopediaInfo1);
        EncyclopediaInfo encyclopediaInfo2 = new EncyclopediaInfo();
        encyclopediaInfo2.setEncyclopedia_iv_content(R.drawable.hair);
        encyclopediaInfo2.setEncyclopedia_title("狗狗美容");
        list.add(encyclopediaInfo2);
        EncyclopediaInfo encyclopediaInfo3 = new EncyclopediaInfo();
        encyclopediaInfo3.setEncyclopedia_iv_content(R.drawable.copulation);
        encyclopediaInfo3.setEncyclopedia_title("狗狗交配");
        list.add(encyclopediaInfo3);
        EncyclopediaInfo encyclopediaInfo4 = new EncyclopediaInfo();
        encyclopediaInfo4.setEncyclopedia_iv_content(R.drawable.play);
        encyclopediaInfo4.setEncyclopedia_title("狗狗训练");
        list.add(encyclopediaInfo4);
        EncyclopediaInfo encyclopediaInfo5 = new EncyclopediaInfo();
        encyclopediaInfo5.setEncyclopedia_iv_content(R.drawable.foodstop);
        encyclopediaInfo5.setEncyclopedia_title("饮食禁忌");
        list.add(encyclopediaInfo5);
        EncyclopediaInfo encyclopediaInfo6 = new EncyclopediaInfo();
        encyclopediaInfo6.setEncyclopedia_iv_content(R.drawable.makelove);
        encyclopediaInfo6.setEncyclopedia_title("狗狗发情");
        list.add(encyclopediaInfo6);
        encyclopediaBaseAdapter = new EncyclopediaBaseAdapter(EncyclopediaListViewActivity.this, list);
        encyclopedia_lv_list.setAdapter(encyclopediaBaseAdapter);
        encyclopediaBaseAdapter.notifyDataSetChanged();
        encyclopedia_lv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        Intent intent = new Intent(EncyclopediaListViewActivity.this, NutritionActivity.class);
                        Click_item = 0;
                        intent.putExtra("itemposition", Click_item);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(EncyclopediaListViewActivity.this, NutritionActivity.class);
                        Click_item = 1;
                        intent1.putExtra("itemposition", Click_item);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(EncyclopediaListViewActivity.this, NutritionActivity.class);
                        Click_item = 2;
                        intent2.putExtra("itemposition", Click_item);
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(EncyclopediaListViewActivity.this, NutritionActivity.class);
                        Click_item = 3;
                        intent3.putExtra("itemposition", Click_item);
                        startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4 = new Intent(EncyclopediaListViewActivity.this, NutritionActivity.class);
                        Click_item = 4;
                        intent4.putExtra("itemposition", Click_item);
                        startActivity(intent4);
                        break;
                    case 5:
                        Intent intent5 = new Intent(EncyclopediaListViewActivity.this, NutritionActivity.class);
                        Click_item = 5;
                        intent5.putExtra("itemposition", Click_item);
                        startActivity(intent5);
                        break;

                }


            }
        });
    }
}
