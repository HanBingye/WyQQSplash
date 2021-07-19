package com.bing.wyqqsplash.main.shenzhen;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bing.wyqqsplash.R;
import com.bing.wyqqsplash.base.BaseActivity;
import com.bing.wyqqsplash.base.ViewInject;
import com.bing.wyqqsplash.base.tools.FileUtils;
import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8Object;

import butterknife.BindView;

@ViewInject(mainlayoutid = R.layout.activity_weixin)
public class WeixinActivity extends BaseActivity {


    @BindView(R.id.bt_JS)
    Button btJS;
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    @BindView(R.id.bt_xPosed)
    Button btXPosed;

    @Override
    public void afterBindView() {
        btJS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //使用v8引擎
                V8 v8 = V8.createV8Runtime();
                V8Object v8Object = new V8Object(v8);
                v8.add("wx", v8Object);
                WxTextView wxTextView = new WxTextView();
                v8Object.registerJavaMethod(wxTextView, "textView", "textView", new Class[]{String.class});

                //解析js文件
                String content = FileUtils.getAssetsContent(WeixinActivity.this, "js/wx.js");
                //使用v8引擎去执行v8脚本
                v8.executeVoidScript(content);
            }
        });
        btXPosed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btXPosed.setText(getInfo());
            }
        });
    }

    private String getInfo() {
        return "xPosed是你弟弟";
    }

    public class WxTextView {
        public void textView(String text) {
            TextView textView = new TextView(WeixinActivity.this);
            textView.setText(text);
            llContent.addView(textView);
        }
    }
}