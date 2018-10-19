package com.steven.waveview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Description:
 * Data：10/18/2018-10:42 AM
 *
 * @author yanzhiwen
 */
public class WaveView extends View {
    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    private int mWaveHeight;
    private int mWaveDx;
    private int dx;

    public WaveView(Context context) {
        this(context, null);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.parseColor("#FF3891"));
        mPaint.setStyle(Paint.Style.FILL);
        //波长的的长度(这里设置为屏幕的宽度)
        mWaveDx = getResources().getDisplayMetrics().widthPixels;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //控件的宽高
        mWidth = MeasureUtils.measureView(widthMeasureSpec, mWaveDx);
        mHeight = MeasureUtils.measureView(heightMeasureSpec, 300);
        //水波的高度
        mWaveHeight = DensityUtil.dip2px(getContext(), 16);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawWave(canvas);
    }


    private void drawWave(Canvas canvas) {
        Path path = new Path();
        path.reset();
        path.moveTo(-mWaveDx + dx, mHeight / 2);
        for (int i = -mWaveDx; i < getWidth() + mWaveDx; i += mWaveDx) {
            path.rQuadTo(mWaveDx / 4, -mWaveHeight, mWaveDx / 2, 0);
            path.rQuadTo(mWaveDx / 4, mWaveHeight, mWaveDx / 2, 0);

        }
        path.lineTo(mWidth, mHeight);
        path.lineTo(0, mHeight);
        //path.close() 绘制封闭的区域
        path.close();
        canvas.drawPath(path, mPaint);
    }

    public void startAnimation() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, mWaveDx);
        valueAnimator.setDuration(2000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //水平方向的偏移量
                dx = ( int ) animation.getAnimatedValue();
                invalidate();
            }

        });
        valueAnimator.start();

    }
}
