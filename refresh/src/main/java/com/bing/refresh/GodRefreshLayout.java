package com.bing.refresh;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class GodRefreshLayout extends LinearLayout {

    private Context context;
    private BaseRefreshManger baseRefreshManger;
    private View headView;
    private int downY;
    private int minHeight;
    private int maxHeight;
    private RefreshListener refreshListener;
    private int interceptDownY;
    private int interceptDownX;
    private RecyclerView recyclerView;

    private void initContext(Context context) {

        this.context = context;
    }

    public GodRefreshLayout(Context context) {
        this(context, null, 0);


    }


    public GodRefreshLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public GodRefreshLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initContext(context);
    }

    //开启下拉刷新,下拉效果是默认的
    public void setRefreshManger() {
        baseRefreshManger = new DefaultRefreshManger(context);
        initHeadView();
    }

    //开启下拉刷新,下拉效果是自定义的
    public void setRefreshManger(BaseRefreshManger baseRefreshManger) {
        this.baseRefreshManger = baseRefreshManger;
        initHeadView();
    }

    private void initHeadView() {
        setOrientation(VERTICAL);
        headView = baseRefreshManger.getView();
        headView.measure(0, 0);
        int measuredHeight = headView.getMeasuredHeight();
        minHeight = -measuredHeight;
        maxHeight = (int) (measuredHeight * 0.3f);

        Log.d("initHeadView", " " + measuredHeight);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, measuredHeight);
        params.topMargin = minHeight;
        addView(headView, 0, params);

    }

    public void refreshOver() {
        returnAnimation(getParams());
    }

    public interface RefreshListener {
        void onRefreshing();
    }

    public void setRefreshListener(RefreshListener refreshListener) {
        this.refreshListener = refreshListener;
    }

    private LayoutParams getParams() {
        return (LayoutParams) headView.getLayoutParams();
    }


    //这个方法回调时,可以获取当前viewGroup的子View
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View childAt = getChildAt(0);
        //获取RecyclerView
        if (childAt instanceof RecyclerView) {
            recyclerView = (RecyclerView) childAt;

        }
    }

    //判断子view是否滑动到顶端
    private boolean ChildViewIsTop() {
        if (recyclerView != null) {
            return RefreshScrollingUtil.isRecyclerViewToTop(recyclerView);
        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                interceptDownY = (int) ev.getY();
                interceptDownX = (int) ev.getX();
                break;

            case MotionEvent.ACTION_MOVE:
                int dy = (int) (ev.getY() - interceptDownY);
                int dx = (int) (ev.getX() - interceptDownX);
                if (Math.abs(dy) > Math.abs(dx) && dy > 0 && ChildViewIsTop()) {
                    return true;
                }
                break;
            default:
                break;

        }
        return super.onInterceptTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downY = (int) event.getY();
                return true;
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) event.getY();
                if (downY == 0) {
                    downY = interceptDownY;
                }
                int dy = moveY - downY;
                if (dy > 0) {
                    LayoutParams params = getParams();
                    //阻尼效果
                    int topMargin = (int) Math.min(dy / 1.8f + minHeight, maxHeight);
                    if (topMargin <= 0) {
                        float percent = ((-minHeight) - (-topMargin)) * 1.0f / (-minHeight);
                        //不断回调这个比例用于一些视觉效果
                        baseRefreshManger.downRefreshPercent(percent);
                    }
                    if (topMargin < 0 && refreshState != RefreshState.DOWNREFRESH) {
                        refreshState = RefreshState.DOWNREFRESH;
                        handRefreshState(refreshState);
                    } else if (topMargin >= 0 && refreshState != RefreshState.RELEASEREFRESH) {
                        refreshState = RefreshState.RELEASEREFRESH;
                        handRefreshState(refreshState);
                    }

                    params.topMargin = topMargin;
                    headView.setLayoutParams(params);
                }

                return true;
            case MotionEvent.ACTION_UP:
                if (handActionUp()) {
                    return true;
                }
                break;

        }
        return super.onTouchEvent(event);
    }


    private boolean handActionUp() {
        downY=0;
        LayoutParams params = getParams();
        if (refreshState == RefreshState.DOWNREFRESH) {
            returnAnimation(params);
        } else if (refreshState == RefreshState.RELEASEREFRESH) {
            params.topMargin = 0;
            headView.setLayoutParams(params);
            refreshState = RefreshState.REFRESHING;
            handRefreshState(refreshState);
            if (refreshListener != null) {
                refreshListener.onRefreshing();
            }
        }

        return params.topMargin > minHeight;
    }

    private void returnAnimation(LayoutParams params) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(params.topMargin, minHeight);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                params.topMargin = value;
                headView.setLayoutParams(params);
            }

        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                refreshState = RefreshState.IDLE;
                handRefreshState(refreshState);
            }
        });
        valueAnimator.setDuration(500);
        valueAnimator.start();
    }

    private void handRefreshState(RefreshState refreshState) {
        switch (refreshState) {
            case IDLE:
                baseRefreshManger.idle();
                break;
            case DOWNREFRESH:
                baseRefreshManger.downRefresh();
                break;
            case RELEASEREFRESH:
                baseRefreshManger.releaseRefresh();
                break;
            case REFRESHING:
                baseRefreshManger.refreshing();
        }
    }

    private RefreshState refreshState = RefreshState.IDLE;

    private enum RefreshState {
        IDLE, DOWNREFRESH, REFRESHING, RELEASEREFRESH
    }
}
