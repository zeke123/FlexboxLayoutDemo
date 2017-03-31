package com.zhoujian.flexboxdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import zhoujian.flexbox.interfaces.OnFlexboxSubscribeListener;
import zhoujian.flexbox.widget.TagFlowLayout;

public class MainActivity extends AppCompatActivity
{

    private TextView btnCount;
    private TextView btnCount1;
    private StringTagAdapter adapter;
    private StringTagAdapter adapter1;
    private List<String> sourceData;
    private List<String> sourceData1;
    private List<String> selectItems;
    private List<String> selectItems1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initData1();
        initViews();
    }

    private void initData() {
        sourceData = new ArrayList<>();
        sourceData.add("体育");
        sourceData.add("财经");
        sourceData.add("汽车");
        sourceData.add("社会");
        sourceData.add("搞笑");
        sourceData.add("军事");
        sourceData.add("历史");
        sourceData.add("教育");
        sourceData.add("数码");
        sourceData.add("健康");
        sourceData.add("旅游");
        sourceData.add("美食");
        sourceData.add("游戏");
        sourceData.add("电影");
        selectItems = new ArrayList<>();
    }


    private void initData1() {
        sourceData1 = new ArrayList<>();
        sourceData1.add("体育");
        sourceData1.add("财经");
        sourceData1.add("汽车");
        sourceData1.add("社会");
        sourceData1.add("搞笑");
        sourceData1.add("军事");
        sourceData1.add("历史");
        sourceData1.add("教育");
        sourceData1.add("数码");
        sourceData1.add("健康");
        sourceData1.add("旅游");
        sourceData1.add("美食");
        sourceData1.add("游戏");
        sourceData1.add("电影");
        selectItems1 = new ArrayList<>();
    }


    private void initViews() {
        TagFlowLayout flowLayout = (TagFlowLayout) findViewById(R.id.flow_layout);
        btnCount = (TextView) findViewById(R.id.btn_get_count);
        adapter = new StringTagAdapter(this, sourceData, selectItems);
        adapter.setOnSubscribeListener(new OnFlexboxSubscribeListener<String>() {
            @Override
            public void onSubscribe(List<String> selectedItem) {
                btnCount.setText("已选择" + selectedItem.size() + "个" + "\n" + "选中的是：" + selectedItem.toString());
            }
        });
        flowLayout.setAdapter(adapter);


        TagFlowLayout flow = (TagFlowLayout) findViewById(R.id.flow);
        btnCount1 = (TextView) findViewById(R.id.btn_get);
        adapter1 = new StringTagAdapter(this, sourceData1, selectItems1);
        adapter1.setOnSubscribeListener(new OnFlexboxSubscribeListener<String>() {
            @Override
            public void onSubscribe(List<String> selectedItem1) {
                btnCount1.setText("已选择" + selectedItem1.size() + "个" + "\n" + "选中的是：" + selectedItem1.toString());
            }
        });
        flow.setAdapter(adapter1);
    }

}
