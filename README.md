## 简介

Flexbox 是前端领域CSS的一种布局方案，可以简便、完整、响应式地实现各种页面布局，并且 React Native 也是使用的 Flex 布局。

FlexboxLayout 是针对 Android 平台的，实现类似 Flexbox 布局方案的一个开源项目，开源地址：

[https://github.com/google/flexbox-layout](https://github.com/google/flexbox-layout)


## 实现效果


![这里写图片描述](http://img.blog.csdn.net/20170331134725319?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxNDAwNTMxNg==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)



## 代码实现

MainActivity.java

```
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

```


activity_main.xml

```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context="com.zhoujian.flexboxdemo.MainActivity">


    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginLeft="10dp"
        android:layout_marginTop="20dp"
        android:text="多选:"
        android:textSize="18sp"
        android:textStyle="bold"/>

    <zhoujian.flexbox.widget.TagFlowLayout
        android:id="@+id/flow_layout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:alignContent="flex_start"
        app:alignItems="center"
        app:defaultDrawable="@drawable/bg_flow_unselect"
        app:defaultTextColor="@color/app_green"
        app:dividerDrawable="@drawable/bg_flow_divider"
        app:flexDirection="row"
        app:flexWrap="wrap"
        app:justifyContent="flex_start"
        app:mode="MULTI"
        app:selectDrawable="@drawable/bg_flow_selected"
        app:selectTextColor="@android:color/white"
        app:showDivider="beginning|middle|end"/>

    <TextView
        android:id="@+id/btn_get_count"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginLeft="10dp"
        android:text=""/>


    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginLeft="10dp"
        android:layout_marginTop="50dp"
        android:text="单选:"
        android:textSize="18sp"
        android:textStyle="bold"/>

    <zhoujian.flexbox.widget.TagFlowLayout
        android:id="@+id/flow"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:alignContent="flex_start"
        app:alignItems="center"
        app:defaultDrawable="@drawable/bg_flow_unselect"
        app:defaultTextColor="@color/app_green"
        app:dividerDrawable="@drawable/bg_flow_divider"
        app:flexDirection="row"
        app:flexWrap="wrap"
        app:justifyContent="flex_start"
        app:mode="SINGLE"
        app:selectDrawable="@drawable/bg_flow_selected"
        app:selectTextColor="@android:color/white"
        app:showDivider="beginning|middle|end"/>

    <TextView
        android:id="@+id/btn_get"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginLeft="10dp"
        android:text=""/>


</LinearLayout>

```












