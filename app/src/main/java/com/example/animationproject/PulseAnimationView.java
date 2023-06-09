package com.example.animationproject;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

public class PulseAnimationView extends View {

    private float mRadius;
    public final Paint mPaint = new Paint();
    private static final int COLOR_ADJUSTER = 5;
    private float mX,mY;
    private static final int ANIMATION_DURATION = 4000;
    private static final long ANIMATION_DELAY = 1000;
    private AnimatorSet mPulseAnimatorSet = new AnimatorSet();


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        ObjectAnimator growAnimator =
                ObjectAnimator.ofFloat(this,"radius",0,getWidth());
        growAnimator.setDuration(ANIMATION_DURATION);
        growAnimator.setInterpolator(new LinearInterpolator());


        ObjectAnimator shrinkAnimator =
                ObjectAnimator.ofFloat(this,"radius",getWidth(),0);
        shrinkAnimator.setDuration(ANIMATION_DURATION);
        shrinkAnimator.setInterpolator(new LinearInterpolator());
        shrinkAnimator.setStartDelay(ANIMATION_DELAY);


        ObjectAnimator repeatAnimator  =
                ObjectAnimator.ofFloat(this,"radius",0,getWidth());
        repeatAnimator.setStartDelay(ANIMATION_DELAY);
        repeatAnimator.setDuration(ANIMATION_DURATION);
        repeatAnimator.setRepeatCount(3);
        repeatAnimator.setRepeatMode(ObjectAnimator.REVERSE);

//        mPulseAnimatorSet.play(growAnimator).before(shrinkAnimator);
        mPulseAnimatorSet.play(repeatAnimator);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mX,mY,mRadius,mPaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            mX = event.getX();
            mY = event.getY();
            if(mPulseAnimatorSet!= null && mPulseAnimatorSet.isRunning()){
                mPulseAnimatorSet.cancel();
            }
            mPulseAnimatorSet.start();
        }
        return super.onTouchEvent(event);
    }

    public void setRadius(float radius){
        mRadius = radius;
        mPaint.setColor(Color.GREEN + (int) radius/COLOR_ADJUSTER);
        invalidate();
    }
    public PulseAnimationView(Context context) {
        super(context);
    }

    public PulseAnimationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PulseAnimationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PulseAnimationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
