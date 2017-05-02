package com.vnh.lazadaofficial.CustomView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.renderscript.ScriptGroup;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.vnh.lazadaofficial.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by HUYVINH on 04-Sep-16.
 */
public class CustomEditTextPass extends EditText {
    Drawable eye, eyeStrike;
    Boolean visible = false;
    Boolean useStrike = false;
    Boolean useValidate = false;
    Drawable drawable;

    // Kiểm tra điều kiện passwords
    String MATCHER_PATTEN = "((?=.*\\d)(?=.*[A-Z])(?=.*[a-z]).{6,20})";
    Pattern pattern;
    Matcher matcher;

    public CustomEditTextPass(Context context) {
        super(context);
        khoitao(null);
    }

    public CustomEditTextPass(Context context, AttributeSet attrs) {
        super(context, attrs);
        khoitao(attrs);
    }

    public CustomEditTextPass(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        khoitao(attrs);
    }

    public CustomEditTextPass(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        khoitao(attrs);
    }

    private void khoitao(AttributeSet attrs) {

        this.pattern = Pattern.compile(MATCHER_PATTEN);

        if (attrs != null) {
            TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.CustomEditTextPass, 0, 0);
            this.useStrike = typedArray.getBoolean(R.styleable.CustomEditTextPass_useStrike, false);
            this.useValidate = typedArray.getBoolean(R.styleable.CustomEditTextPass_useValidate,false);
        }
        if (this.useValidate){
            setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus){
                        String b = getText().toString();
                        TextInputLayout textInputLayout = (TextInputLayout) v.getParent();

                        matcher = pattern.matcher(b);
                        if (!matcher.matches()){
                            textInputLayout.setErrorEnabled(true);
                            textInputLayout.setError("Mật khẩu phải bao gồm 6 kí tự ít nhất 1 số và 1 chữ hoa");
                        }
                        else{
                            textInputLayout.setErrorEnabled(false);
                            textInputLayout.setError("");
                        }
                    }
                }
            });
        }
        eye = ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility_black_24dp).mutate();
        eyeStrike = ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility_off_black_24dp).mutate();
        caidat();
    }

    private void caidat() {
        setInputType(InputType.TYPE_CLASS_TEXT | (visible ? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD : InputType.TYPE_TEXT_VARIATION_PASSWORD));
        Drawable[] drawables = getCompoundDrawables();
        drawable = useStrike && !visible ? eyeStrike : eye;
        setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], drawable, drawables[3]);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP && event.getX() >= (getRight() - drawable.getBounds().width() )){
            visible = !visible;
            caidat();
            invalidate();
        }

        return super.onTouchEvent(event);
    }
}
