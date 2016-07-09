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
