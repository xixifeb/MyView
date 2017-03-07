package com.example.xitom.myview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by XiTom on 2017/3/2.
 */

public class PieChart extends View{

    private Rect mBound;
    private Paint mPaint;

    private String mTitleText;
    private int mTitleTextColor;
    private int mTitleTextSize;
    private boolean mShowText;
    private int mTextPos;
    private int mTextSize;

    public PieChart(Context context, AttributeSet attrs){
        super(context, attrs);
        //获取定义的控件属性
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.PieChart, 0, 0);

        //

        mTitleTextSize = array.getDimensionPixelSize(R.styleable.PieChart_textSize,
                (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16,
                        getResources().getDisplayMetrics()));
        mTitleText = array.getString(R.styleable.PieChart_titleText);
        mTitleTextColor = array.getColor(R.styleable.PieChart_titleTextColor, Color.BLACK);

        mPaint = new Paint();
        mPaint.setTextSize(mTitleTextSize);

        mBound = new Rect();
        mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);



        try {
            mShowText = array.getBoolean(R.styleable.PieChart_showText, false);
            mTextPos = array.getInteger(R.styleable.PieChart_labelPosition, 0);
        }catch(Exception e){

        }finally {
            array.recycle();
        }
    }

    public boolean isShowText(){
        return mShowText;
    }

    public void setShowText(boolean showText){
        mShowText = showText;
        invalidate();
        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width = 0;
        int height = 0;

        if(widthMode == MeasureSpec.EXACTLY){
            width = widthSize;
        }else{
            mPaint.setTextSize(mTitleTextSize);
            mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);
            float textWidth = mBound.width();
            int desired = (int)(getPaddingLeft()+textWidth+getPaddingRight());
            width = desired;
        }



        if(heightMode == MeasureSpec.EXACTLY){
            height = heightSize;
        }else{

        }

        setMeasuredDimension(width, height);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.RED);
        canvas.drawRect(0,0,getMeasuredWidth(), getMeasuredHeight(), mPaint);

        mPaint.setColor(mTitleTextColor);
        canvas.drawText(mTitleText,
                getWidth() / 2 - mBound.width() / 2,
                getHeight() / 2 + mBound.height() / 2,
                mPaint);

    }









}
