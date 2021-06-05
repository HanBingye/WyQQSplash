package com.bing.refresh;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class DefaultRefreshManger extends BaseRefreshManger {

    private TextView headText;

    public DefaultRefreshManger(Context context) {
        super(context);
    }

    @Override
    public View getView() {

        View view = layoutInflater.inflate(R.layout.ulti_header_layout, null, false);
        headText = view.findViewById(R.id.header_text);
        return view;
    }

    @Override
    public void releaseRefresh() {
        headText.setText("释放刷新");
    }

    @Override
    public void downRefresh() {
        headText.setText("下拉刷新");

    }

    @Override
    public void idle() {
        headText.setText("下拉刷新");
    }

    @Override
    public void refreshing() {
        headText.setText("正在刷新");
    }

    @Override
    public void downRefreshPercent(float percent) {

    }
}

