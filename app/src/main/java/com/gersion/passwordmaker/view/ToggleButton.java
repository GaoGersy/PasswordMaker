package com.gersion.passwordmaker.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.gersion.passwordmaker.R;


/**
 * @作者 Gers
 * @版本
 * @包名 com.gersion.superlock.view
 * @待完成
 * @创建时间 2016/8/14
 * @功能描述 TODO
 * @更新人 $
 * @更新时间 $
 * @更新版本 $
 */
public class ToggleButton extends View implements View.OnClickListener {

    private final Bitmap onBitmap;
    private final Bitmap offBitmap;
    private boolean mState;
    private Paint mPaint;
    private OnStateSwitchListener mListener;

    public ToggleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ToggleButton);
        mState = ta.getBoolean(R.styleable.ToggleButton_switchState,false);
        ta.recycle();
        mPaint = new Paint();
        onBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.switch_on);
        offBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.switch_off);
        setOnClickListener(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(offBitmap.getWidth(),offBitmap.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mState){
            mListener.onSwitchState(mState);
            canvas.drawBitmap(onBitmap,0,0,mPaint);
        }else{
            mListener.onSwitchState(mState);
            canvas.drawBitmap(offBitmap,0,0,mPaint);
        }
    }


    @Override
    public void onClick(View v) {
        mState = !mState;
        invalidate();
    }

    public void setOnStateSwitchListener(OnStateSwitchListener listener){

        mListener = listener;
    }

    public interface OnStateSwitchListener{
        void onSwitchState(boolean state);
    }
}
