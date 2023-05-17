package com.example.animationproject;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ObjectAnimator mAnimatorRotate1;
    ObjectAnimator mAnimatorMove;
    ObjectAnimator mAnimatorFade;
    ObjectAnimator mAnimatorFadeIn;
    ObjectAnimator mAnimatorMoveDown;
    ObjectAnimator mAnimatorMoveRotate2;
    ObjectAnimator mAnimatorX;
    ObjectAnimator mAnimatorY;
    private AnimatorSet animatorSet = new AnimatorSet();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imgView = findViewById(R.id.imagee);
        mAnimatorRotate1 = ObjectAnimator.ofFloat(imgView,"rotation",360);
        mAnimatorMove = ObjectAnimator.ofFloat(imgView,"x",950);
        mAnimatorMoveDown = ObjectAnimator.ofFloat(imgView,"y",1450);
        mAnimatorMoveRotate2 = ObjectAnimator.ofFloat(imgView,"rotation",90);
        mAnimatorFade = ObjectAnimator.ofFloat(imgView,"alpha",0);
        mAnimatorFadeIn = ObjectAnimator.ofFloat(imgView,"alpha",1);
        mAnimatorX = ObjectAnimator.ofFloat(imgView,"x",450);
        mAnimatorY = ObjectAnimator.ofFloat(imgView,"y",450);
        mAnimatorFadeIn = ObjectAnimator.ofFloat(imgView,"alpha",1);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            mAnimatorRotate1.setDuration(600);
            animatorSet.play(mAnimatorRotate1).with(mAnimatorMove).before(mAnimatorFadeIn).before(mAnimatorFade).before(mAnimatorMoveRotate2).before(mAnimatorMoveDown);
            animatorSet.play(mAnimatorX).with(mAnimatorY).with(mAnimatorRotate1);
            animatorSet.start();

        }
        return super.onTouchEvent(event);
    }
}