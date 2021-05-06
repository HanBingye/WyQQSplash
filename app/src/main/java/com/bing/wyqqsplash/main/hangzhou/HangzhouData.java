package com.bing.wyqqsplash.main.hangzhou;

import java.util.ArrayList;

public class HangzhouData {
    private static ArrayList<HangzhouBean> getVerData(int len) {
        ArrayList<HangzhouBean> list = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            HangzhouBean bean = new HangzhouBean();
            bean.setShowImg(false).setDec("杭州欢迎您");

            list.add(bean);

        }
        return list;
    }

    private static ArrayList<HangzhouBean> getHorData(int len) {
        ArrayList<HangzhouBean> list = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            HangzhouBean bean = new HangzhouBean();
            bean.setShowImg(true).setDec("杭州的西湖");

            list.add(bean);

        }
        return list;
    }

    public static ArrayList<HangzhouBean> getData() {
        ArrayList<HangzhouBean> list = new ArrayList<>();

        list.addAll(getVerData(6));
        list.add(new HangzhouBean().setData(getHorData(4)).setItemType(HangzhouBean.HangzhouItemType.HORIZONTAL));
        list.addAll(getVerData(6));
        list.add(new HangzhouBean().setData(getHorData(4)).setItemType(HangzhouBean.HangzhouItemType.HORIZONTAL));


        return list;
    }
}
