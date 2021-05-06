package com.bing.wyqqsplash.main.hangzhou;

import java.util.ArrayList;

public class HangzhouBean {
private int itemType=HangzhouItemType.VERTICAL;
private String dec;
private boolean showImg;
private ArrayList<HangzhouBean> data;

    public int getItemType() {
        return itemType;
    }

    public HangzhouBean setItemType(int itemType) {
        this.itemType = itemType;
        return this;
    }

    public String getDec() {
        return dec;
    }

    public HangzhouBean setDec(String dec) {
        this.dec = dec;
        return this;
    }

    public boolean isShowImg() {
        return showImg;
    }

    public HangzhouBean setShowImg(boolean showImg) {
        this.showImg = showImg;
        return this;
    }

    public ArrayList<HangzhouBean> getData() {
        return data;
    }

    public HangzhouBean setData(ArrayList<HangzhouBean> data) {
        this.data = data;
        return this;
    }

    public interface HangzhouItemType{
        int VERTICAL=0;
        int HORIZONTAL=1;
    }
}
