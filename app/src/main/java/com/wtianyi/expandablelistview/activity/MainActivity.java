package com.wtianyi.expandablelistview.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.wtianyi.expandablelistview.R;
import com.wtianyi.expandablelistview.adapter.ExpandableAdapter;
import com.wtianyi.expandablelistview.bean.Card;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private ExpandableListView elv_listview;
    private ExpandableAdapter adapter;
    private List<Card> goldCardList = new ArrayList<>();    //金卡
    private List<Card> silverCardList = new ArrayList<>();  //银卡


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        elv_listview = (ExpandableListView)findViewById(R.id.elv_listview);

        setData();

        //将数据传递到 adapter
        adapter = new ExpandableAdapter(this, goldCardList, silverCardList);
        elv_listview.setAdapter(adapter);

        //ExpandableListView子项的点击事件
        elv_listview.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(MainActivity.this, "##"+groupPosition+"##"+childPosition, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    /**
     * 添加数据
     */
    public void setData() {
        Card goldCard = new Card("金卡","未兑换");
        Card goldCard2 = new Card("金卡","未兑换");

        Card silverCard = new Card("银卡","未兑换");
        Card silverCard2 = new Card("银卡","未兑换");

        goldCardList.add(goldCard);
        goldCardList.add(goldCard2);

        silverCardList.add(silverCard);
        silverCardList.add(silverCard2);


    }
}
