package com.dingyl.xiaominote.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;

public class MyCheckBox extends View implements Checkable{

    private Paint bitmapPaint;
    private Paint bitmapEraser;
    private Paint checkEraser;
    private Paint borderPaint;
    private Canvas bitmapCanvas;
    private Canvas checkCanvas;

    public MyCheckBox(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        bitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bitmapEraser = new Paint(Paint.ANTI_ALIAS_FLAG);
        bitmapEraser.setColor(0);
        bitmapEraser.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        checkEraser = new Paint(Paint.ANTI_ALIAS_FLAG);
        checkEraser.setColor(0);
        checkEraser.setStyle(Paint.Style.STROKE);
        checkEraser.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(2);

    }
    @Override
    public void setChecked(boolean b) {

    }

    @Override
    public boolean isChecked() {
        return false;
    }

    @Override
    public void toggle() {

    }
}
