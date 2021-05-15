package com.flaviotps.reciclando.Custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

public class CustomViewPager  extends ViewPager {

    private boolean enableTouch = false;

    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean isEnableTouch() {
        return enableTouch;
    }

    public void setEnableTouch(boolean enableTouch) {
        this.enableTouch = enableTouch;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        if (this.enableTouch) {
            return super.onTouchEvent(ev);
        } else {
            return false;
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (this.enableTouch) {
            return super.onInterceptTouchEvent(ev);
        } else {
            return false;
        }
    }


}