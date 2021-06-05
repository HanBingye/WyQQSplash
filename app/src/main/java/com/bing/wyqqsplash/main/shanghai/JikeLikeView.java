package com.bing.wyqqsplash.main.shanghai;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.bing.wyqqsplash.R;
import com.bing.wyqqsplash.tools.SystemUtil;

public class JikeLikeView extends View {

    private Bitmap like;
    private Bitmap unLike;
    private Bitmap shining;
    private Paint paint;
    private boolean is_like;
    private int left;
    private int top;
    private float handScale = 1.0f;
    private float centerX;
    private float centerY;

    public JikeLikeView(Context context) {
        this(context, null, 0);

    }


    public JikeLikeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public JikeLikeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.JikeLikeView);
        is_like = typedArray.getBoolean(R.styleable.JikeLikeView_is_like, false);
        typedArray.recycle();
        init();
    }

    private void init() {
        like = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_message_like);
        unLike = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_message_unlike);
        shining = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_message_like_shining);
        paint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measureWidth = 0;
        int measureHeight = 0;

        int maxHeight = like.getHeight() + SystemUtil.dp2px(getContext(), 20);
        int maxWidth = like.getWidth() + SystemUtil.dp2px(getContext(), 30);
        // 拿到当前控件的测量模式
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (mode != MeasureSpec.EXACTLY) {
            //测量模式未指定   如果有背景 背景多大 我们这个控件就有多大
            int suggestedMinimumWidth = getSuggestedMinimumWidth();
            int suggestedMinimumHeight = getSuggestedMinimumHeight();
            if (suggestedMinimumWidth == 0) {
                measureWidth = maxWidth;
            } else {
                measureWidth = Math.min(suggestedMinimumWidth, maxWidth);
            }
            if (suggestedMinimumHeight == 0) {
                measureHeight = maxHeight;
            } else {
                measureHeight = Math.min(suggestedMinimumHeight, maxHeight);
            }
        } else {
            // 测量模式指定  根据用户定义大小来判断
            if (widthSize < maxWidth || heightSize < maxHeight) {
                measureWidth = maxWidth;
                measureHeight = maxHeight;

            } else {
                measureWidth = Math.min(maxWidth, widthSize);
                measureHeight = Math.min(maxHeight, heightSize);
            }
        }
        setMeasuredDimension(measureWidth, measureHeight);
        padding(measureWidth, measureHeight);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Bitmap bitmap = is_like ? like : unLike;


        //使用scale方法及其他的效果方法时,
        // 必须先调用save然后再调用restore(这两个方法成对出现)
        canvas.save();
        canvas.scale(handScale, handScale, centerX, centerY);
        canvas.drawBitmap(bitmap, left, top, paint);
        canvas.restore();
        if (is_like) {
            canvas.drawBitmap(shining, left + 10, 0, paint);
        }


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Click();
                break;
            default:
                break;
        }
        return true;
       
    }

    private void Click() {
        is_like = !is_like;

        ObjectAnimator handScale = ObjectAnimator.ofFloat(this, "handScale", 1.0f, 0.8f, 1.0f);
        handScale.setDuration(250);
        handScale.start();


    }

    //使用ObjectAnimator,系统会自动调用该属性的set方法
    @SuppressLint("ObjectAnimatorBinding")
    public void setHandScale(float value) {
        this.handScale = value;
        //回调draw方法
        invalidate();

    }


    public void padding(int measureWidth, int measureHeight) {

        int likeWidth = like.getWidth();
        int likeHeight = like.getHeight();
        left = (measureWidth - likeWidth) / 2;
        top = (measureHeight - likeHeight) / 2;
        centerX = measureWidth / 2;
        centerY = measureHeight / 2;
    }

    //当这个自定义view从界面脱离的时候调用,做回收资源的操作
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        like.recycle();
        unLike.recycle();
        shining.recycle();
    }
}
