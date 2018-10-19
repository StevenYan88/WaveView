package com.steven.waveview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Description:
 * Data：10/19/2018-9:33 AM
 *
 * @author yanzhiwen
 */
public class BezierView extends View {
    private Paint mPaint;

    public BezierView(Context context) {
        this(context, null);
    }

    public BezierView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.parseColor("#FF3891"));
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = new Path();
        //x1:控制点x坐标
        //y1:在控制点y坐标
        //x2：终点x坐标
        //y2:终点y坐标
        path.moveTo(getWidth() / 2 - 200, getHeight() / 2);
        path.quadTo(getWidth() / 2 - 100, getHeight() / 2 - 100, getWidth() / 2, getHeight() / 2);
        path.quadTo(getWidth() / 2 + 100, getHeight() / 2 + 100, getWidth() / 2 + 200, getHeight() / 2);
        // path.close();
        canvas.drawPath(path, mPaint);


        //dx1：控制点相对起点的x位移
        //dy1：控制点相对起点的y位移
        //dx2：终点相对起点的x位移
        //dy2：终点相对起点的y位移
//        path.moveTo(getWidth() / 2 - 200, getHeight() / 2);
//        path.rQuadTo(100, -100, 200, 0);
//        path.rQuadTo(100, 100, 200, 0);
//        path.close();
//        canvas.drawPath(path, mPaint);
    }
}
