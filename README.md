##ExpandableListView的使用

ExpandableListView组件是android中一个比较常用的组件，当点击一个父item的时候可以将它的子item显示出来，像手机QQ中的好友列表就是实现的类型效果。使用ExpandableListView组件的关键就是设置它的adapter，这个adapter必须继承BaseExpandbaleListAdapter类，所以实现运用ExpandableListView的核心就是学会继承这个BaseExpanableListAdapter类。
 
一般适用于ExpandableListView的Adapter都要继承BaseExpandableListAdapter这个类，并且必须重载getGroupView和getChildView这两个最为重要的方法。

下面是一个小例子：

activity_main.xml:

	<?xml version="1.0" encoding="utf-8"?>
	<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    	<ExpandableListView
        	android:id="@+id/elv_listview"
        	android:layout_width="match_parent"
        	android:layout_height="wrap_content" />
	</LinearLayout>

parent_item.xml:

	<?xml version="1.0" encoding="utf-8"?>
	<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical" android:layout_width="match_parent"
    android:layout_height="match_parent">

	    <TextView
	        android:id="@+id/option"
	        android:layout_width="match_parent"
	        android:layout_height="50dp"
	        android:gravity="center_vertical"
	        android:layout_marginLeft="10dp"
	        android:text="已兑换"
	        android:textSize="30sp"/>

	</LinearLayout>

child_item.xml:

	<?xml version="1.0" encoding="utf-8"?>
	<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#f00">

	    <TextView
	        android:id="@+id/card_type"
	        android:layout_width="wrap_content"
	        android:layout_height="wrap_content"
	        android:text="金卡"
	        android:textColor="#ffa500"
	        android:textSize="15sp"/>
	
	    <TextView
	        android:layout_width="wrap_content"
	        android:layout_height="wrap_content"
	        android:layout_gravity="center"
	        android:gravity="center_vertical"
	        android:text="贵宾卡"
	        android:textColor="#ffa500"
	        android:layout_weight="1"
	        android:textSize="30sp"/>
	
	    <TextView
	        android:id="@+id/y_n"
	        android:layout_width="wrap_content"
	        android:layout_height="wrap_content"
	        android:layout_gravity="end"
	        android:textColor="#ffa500"
	        android:text="未兑换"
	        android:textSize="15sp"/>

	</LinearLayout>

MainActivity.java:

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

ExpandableAdapter.java:

	package com.wtianyi.expandablelistview.adapter;

	import android.content.Context;
	import android.view.View;
	import android.view.ViewGroup;
	import android.widget.BaseExpandableListAdapter;
	import android.widget.TextView;
	
	import com.wtianyi.expandablelistview.R;
	import com.wtianyi.expandablelistview.bean.Card;
	
	import java.util.List;
	
	/**
	 * Author：wtianyi on 2016/7/9 14:39.
	 */
	public class ExpandableAdapter extends BaseExpandableListAdapter{
	    private Context context;
	
	    private String[] option = new String[]{"金卡区", "银卡区"};
	
	    private List<Card> goldCard;
	    private List<Card> silverCard;
	
	    public ExpandableAdapter(Context context, List<Card> goldCard, List<Card> silverCard) {
	        this.context = context;
	        this.goldCard = goldCard;
	        this.silverCard = silverCard;
	    }
	
	    /**********组列表********/
	    @Override
	    public int getGroupCount() {
	        return option.length;
	    }
	
	    @Override
	    public Object getGroup(int groupPosition) {
	        return option[groupPosition];
	    }
	
	    @Override
	    public long getGroupId(int groupPosition) {
	        return groupPosition;
	    }
	
	    @Override
	    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
	
	        ViewHolder holder = null;
	        if(convertView == null) {
	            holder = new ViewHolder();
	            convertView = View.inflate(context, R.layout.parent_item, null);
	            holder.option = (TextView) convertView.findViewById(R.id.option);
	
	            convertView.setTag(holder);
	        }else {
	            holder = (ViewHolder) convertView.getTag();
	        }
	
	        holder.option.setText(option[groupPosition]);
	        return convertView;
	    }
	
	    /**********子列表********/
	    @Override
	    public int getChildrenCount(int groupPosition) {
	        if(option[groupPosition]==option[0]) {
	            return goldCard.size();
	        }else {
	            return silverCard.size();
	        }
	    }
	
	    @Override
	    public Object getChild(int groupPosition, int childPosition) {
	        if(option[groupPosition]==option[0]) {
	            return goldCard.get(groupPosition);
	        }else {
	            return silverCard.get(groupPosition);
	        }
	    }
	
	    @Override
	    public long getChildId(int groupPosition, int childPosition) {
	        return childPosition;
	    }
	
	    @Override
	    public boolean hasStableIds() {
	        return true;
	    }
	
	    @Override
	    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
	
	        ViewHolder holder = null;
	        if(convertView == null) {
	            holder = new ViewHolder();
	            convertView = View.inflate(context, R.layout.child_item, null);
	            holder.card_type = (TextView) convertView.findViewById(R.id.card_type);
	            holder.y_n = (TextView) convertView.findViewById(R.id.y_n);
	
	            convertView.setTag(holder);
	        }else {
	            holder = (ViewHolder) convertView.getTag();
	        }
	
	        if(option[groupPosition]==option[0]) {
	            holder.card_type.setText(goldCard.get(groupPosition).getCard_type());
	            holder.y_n.setText(goldCard.get(groupPosition).getY_n());
	        }else {
	            holder.card_type.setText(silverCard.get(groupPosition).getCard_type());
	            holder.y_n.setText(silverCard.get(groupPosition).getY_n());
	        }
	
	        return convertView;
	    }
	
	    @Override
	    public boolean isChildSelectable(int groupPosition, int childPosition) {
	        return true;
	    }
	
	    class ViewHolder{
	        TextView card_type;
	        TextView y_n;
	        TextView option;
	    }
	}

Card.java:

	package com.wtianyi.expandablelistview.bean;

	/**
	 * Author：wtianyi on 2016/7/9 14:41.
	 */
	public class Card {
	    private String card_type;   //卡类型
	    private String y_n;         //是否兑换
	
	    public Card(String card_type, String y_n) {
	        this.card_type = card_type;
	        this.y_n = y_n;
	    }
	
	    public String getCard_type() {
	        return card_type;
	    }
	
	    public void setCard_type(String card_type) {
	        this.card_type = card_type;
	    }
	
	    public String getY_n() {
	        return y_n;
	    }
	
	    public void setY_n(String y_n) {
	        this.y_n = y_n;
	    }
	}
