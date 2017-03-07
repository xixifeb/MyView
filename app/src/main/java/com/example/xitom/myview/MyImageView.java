package com.example.xitom.myview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by XiTom on 2017/3/3.
 */

public class MyImageView extends View {

    private int myImageViewHeight;
    private int myImageViewWidth;
    private int myImageViewColor;
    private String myText;
    private int myTextSize;
    private int myTextColor;
    private Drawable myImageViewSrc;

    private int width;
    private int height;
    private float x = 0;
    private float y = 0;


    public MyImageView(Context context) {
        super(context);
    }

    public MyImageView(Context context, AttributeSet attrs){
        super(context, attrs);
        initAttrs(context, attrs);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);


        if(widthMode == MeasureSpec.EXACTLY){
            width = widthSize;
        }else{
            width = 200;
        }

        if(heightMode == MeasureSpec.EXACTLY){
            height = heightSize;
        }else{
            height = 50;
        }

        setMeasuredDimension(width, height);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint(); 
        paint.setColor(Color.argb(0xff,(int)x,(int)y,(int)(x+y)));
        canvas.drawRect(x, y, x+200, y+200, paint);
    }



    private void initAttrs(Context context, AttributeSet attrs){
        //获取控件自定义属性
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyImageView, 0,0);

        try {
            myImageViewHeight = array.getDimensionPixelSize(R.styleable.MyImageView_MyImageViewHeight,
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                            20, getResources().getDisplayMetrics()));

            myImageViewWidth = array.getDimensionPixelSize(R.styleable.MyImageView_MyImageViewWidth,
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                            20, getResources().getDisplayMetrics()));

            myImageViewColor = array.getColor(R.styleable.MyImageView_MyImageViewColor, Color.BLUE);

            myText = array.getString(R.styleable.MyImageView_MyText);

            myTextSize = array.getDimensionPixelSize(R.styleable.MyImageView_MyTextSize,
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                            20, getResources().getDisplayMetrics()));

            myTextColor = array.getColor(R.styleable.MyImageView_MyTextColor, Color.GREEN);

            myImageViewSrc = array.getDrawable(R.styleable.MyImageView_MyImageViewSrc);

        }catch(Exception e){

        }finally {
            if(array!=null){
                array.recycle();
            }
        }

        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {


                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        x = motionEvent.getX();
                        y = motionEvent.getY();
                        postInvalidate();
//                        Toast.makeText(getContext(), "down", Toast.LENGTH_SHORT).show();
                        break;

                    case MotionEvent.ACTION_MOVE:
                        x = (int)motionEvent.getX();
                        y = (int)motionEvent.getY();
                        //setMeasuredDimension((int)x, (int)y);
                       postInvalidate();
                        break;

                    case MotionEvent.ACTION_UP:
//                        Toast.makeText(getContext(), "up", Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        break;
                }


                return true;
            }
        });










    }




}
