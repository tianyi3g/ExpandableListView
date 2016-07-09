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
