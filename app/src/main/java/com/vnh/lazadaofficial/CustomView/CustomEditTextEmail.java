package com.vnh.lazadaofficial.CustomView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import com.vnh.lazadaofficial.R;

/**
 * Created by HUYVINH on 06-Sep-16.
 */
public class CustomEditTextEmail extends EditText {
    Drawable clearx, noneclearx,drawable;
    Boolean visible=false;

    public CustomEditTextEmail(Context context) {
        super(context);
        khoitao();
    }

    public CustomEditTextEmail(Context context, AttributeSet attrs) {
        super(context, attrs);
        khoitao();
    }

    public CustomEditTextEmail(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        khoitao();
    }

    public CustomEditTextEmail(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        khoitao();
    }

    public void khoitao() {
        clearx = ContextCompat.getDrawable(getContext(), R.drawable.ic_clear_black_24dp).mutate();
        noneclearx = ContextCompat.getDrawable(getContext(), android.R.drawable.screen_background_light_transparent).mutate();
        caidat();
    }

    public void caidat() {
        setInputType(InputType.TYPE_CLASS_TEXT);
        Drawable[] drawables = getCompoundDrawables();
        drawable = visible ? clearx : noneclearx;
        setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], drawable, drawables[3]);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        if (lengthAfter == 0 && start ==0) {
            visible = false;
            caidat();
        } else {
            visible = true;
            caidat();
        }
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP && event.getX() >= (getRight() -drawable.getBounds().width() )){
            setText("");
        }

        return super.onTouchEvent(event);
    }
}
