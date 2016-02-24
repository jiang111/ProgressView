/**
 * created by jiang, 16/2/24
 * Copyright (c) 2016, jyuesong@gmail.com All Rights Reserved.
 * *                #                                                   #
 * #                       _oo0oo_                     #
 * #                      o8888888o                    #
 * #                      88" . "88                    #
 * #                      (| -_- |)                    #
 * #                      0\  =  /0                    #
 * #                    ___/`---'\___                  #
 * #                  .' \\|     |# '.                 #
 * #                 / \\|||  :  |||# \                #
 * #                / _||||| -:- |||||- \              #
 * #               |   | \\\  -  #/ |   |              #
 * #               | \_|  ''\---/''  |_/ |             #
 * #               \  .-\__  '-'  ___/-. /             #
 * #             ___'. .'  /--.--\  `. .'___           #
 * #          ."" '<  `.___\_<|>_/___.' >' "".         #
 * #         | | :  `- \`.;`\ _ /`;.`/ - ` : | |       #
 * #         \  \ `_.   \_ __\ /__ _/   .-` /  /       #
 * #     =====`-.____`.___ \_____/___.-`___.-'=====    #
 * #                       `=---='                     #
 * #     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   #
 * #                                                   #
 * #               佛祖保佑         永无BUG              #
 * #                                                   #
 */

package android.jiang.com.progressview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by jiang on 16/2/24.
 */
public class ProgressView extends View {

    private static final String TAG = "ProgressView";

    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    private int circleRadius;
    private int stokenRadius;
    private int stokenWidth;
    private int linePadding;
    private int checkedColor = Color.WHITE;
    private int uncheckedColor = Color.DKGRAY;
    private int normaltextSize;
    private int textPaddingTop;

    private int circleCount;

    private Paint.FontMetricsInt fontMetricsInt;


    public ProgressView(Context context) {
        this(context, null);
    }

    public ProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        circleCount = 3;
        initValues();
        initpaint();
    }

    private void initValues() {
        circleRadius = Utils.dip2px(getContext(), 3);
        stokenRadius = Utils.dip2px(getContext(), 6);
        stokenWidth = Utils.dip2px(getContext(), 1);
        linePadding = Utils.dip2px(getContext(), 3);
        textPaddingTop = Utils.dip2px(getContext(), 10);
        normaltextSize = Utils.sp2px(getContext(), 14);
    }

    private void initpaint() {
        mPaint = new Paint();
        mPaint.setStrokeWidth(stokenWidth);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        mHeight = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCircle(canvas);
        drawLine(canvas);
    }

    private void drawCircle(Canvas canvas) {

//        for (int i = 0; i < circleCount; i++) {
//            int circlePointX = mWidth / circleCount + 1 - i;
//
//        }

        int circlePointX = mWidth / circleCount + 1;
        int circlePointY = mHeight / 2;
        drawCircleWithParam(canvas, circlePointX, circlePointY, true, checkedColor, "确认密码");

        int twoCirclePointX = mWidth - circlePointX;
        drawCircleWithParam(canvas, twoCirclePointX, circlePointY, false, uncheckedColor, "邮件地址");
    }

    private void drawLine(Canvas canvas) {

        int startX = mWidth / circleCount + 1 + stokenRadius + stokenWidth + linePadding;
        int endX = mWidth / 2 + (mWidth / 2 - startX) + stokenWidth + (stokenRadius - circleRadius);
        int startY = mHeight / 2;

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(checkedColor);
        canvas.drawLine(startX, startY, endX, startY, mPaint);
    }

    private void drawCircleWithParam(Canvas canvas, int circleX, int circleY, boolean needStoken, int color, String value) {

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(color);
        canvas.drawCircle(circleX, circleY, circleRadius, mPaint);
        mPaint.setTextSize(normaltextSize);
        int textWidth = (int) mPaint.measureText(value, 0, value.length());
        int textStartX = circleX - textWidth / 2;
        fontMetricsInt = mPaint.getFontMetricsInt();
        int textStartY = Math.abs(Math.abs(fontMetricsInt.bottom) + Math.abs(fontMetricsInt.top)) / 2 + circleY + textPaddingTop;
        textStartY += stokenRadius + stokenWidth;

        Log.i(TAG, "drawCircleWithParam: " + textWidth + ":" + fontMetricsInt.toString() + ";" + textStartX + ":" + textStartY + ";" + circleX + ":" + circleY);
        canvas.drawText(value, textStartX, textStartY, mPaint);
        if (needStoken) {
            mPaint.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(circleX, circleY, stokenRadius, mPaint);
        }
    }
}
