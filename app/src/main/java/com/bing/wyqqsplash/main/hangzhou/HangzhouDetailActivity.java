package com.bing.wyqqsplash.main.hangzhou;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;

import com.bing.ipc.IpcManager;
import com.bing.ipc.request.IpcRequest;
import com.bing.ipc.result.IResult;
import com.bing.wyqqsplash.R;
import com.bing.wyqqsplash.base.BaseActivity;
import com.bing.wyqqsplash.base.ViewInject;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@ViewInject(mainlayoutid = R.layout.activity_hangzhou_detail)
public class HangzhouDetailActivity extends BaseActivity {
    @BindView(R.id.iv_hangzhou_detail)
    ImageView ivHangzhouDetail;
    private static String s = "HangzhouDetailActivity";
    private ArrayList<HangzhouDetailBean.ResultBean.DataBean> list;

    @Override
    public void afterBindView() {
        // TODO: 2021/5/8
        initAnimation();
//        initGetNetData();
        initIpc();

//        initPostNetData();
    }

    private void initIpc() {
        IpcRequest request = new IpcRequest("hangzhou");
        IpcManager.getInstance(this).enqueue(request, new com.bing.ipc.Callback() {
            @Override
            public void callback(IResult result) {
                String data = result.getData();
                Log.d("initIpc", data);
            }
        });
//        IResult iResult = IpcManager.getInstance(this).execute(request);
//        Log.d("initIpc", iResult.getData());
    }

    private void initPostNetData() {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("key", "0f08cd674792667feb5ce236ea028747")
                .build();


        Request request = new Request.Builder()
                .url("http://apis.juhe.cn/lottery/types")
                .post(body)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("initGetNetData", "onFailure: " + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("initGetNetData", "onResponse");
            }
        });
    }

    private void initGetNetData() {
//        XiaohuaTask xiaohuaTask = new XiaohuaTask();
//        xiaohuaTask.execute("desc", "1", "2");


        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder builder = HttpUrl.parse("http://v.juhe.cn/joke/content/list.php").newBuilder();
        builder.addQueryParameter("sort", "desc");
        builder.addQueryParameter("page", "1");
        builder.addQueryParameter("pagesize", "2");
        builder.addQueryParameter("time", "" + System.currentTimeMillis() / 1000);
        builder.addQueryParameter("key", "bbc57dd5e4f05991aff09eafd2e667e0");

        Request request = new Request.Builder()
                .url(builder.build())
                .get()
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("initGetNetData", "onFailure: " + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("initGetNetData", "onResponse");
            }
        });
    }

    private void initAnimation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ViewCompat.setTransitionName(ivHangzhouDetail, s);
            //开启转场动画
            startPostponedEnterTransition();
        }
    }


    public static void start(Activity activity, View v) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Intent intent = new Intent(activity, HangzhouDetailActivity.class);
            Pair pair = new Pair(v, s);
            ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pair);
            ActivityCompat.startActivity(activity, intent, activityOptionsCompat.toBundle());
        }
    }
}