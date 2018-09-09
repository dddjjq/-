package com.dingyl.xiaominote.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.dingyl.xiaominote.R;

public class MainToolBar extends Toolbar {

    private static final String TAG = "MainToolBar";
    private ImageButton leftBtn;
    private ImageButton rightbtn;
    private OnLeftItemClickListener onLeftItemClickListener;
    private OnRightItemClickListener onRightItemClickListener;

    public MainToolBar(Context context) {
        super(context);
    }

    public MainToolBar(Context context,AttributeSet attrs) {
        super(context,attrs);
        inflate(context, R.layout.main_tool_bar,this);
    }

    @Override
    protected void onFinishInflate(){
        super.onFinishInflate();
        leftBtn = findViewById(R.id.left_icon);
        rightbtn = findViewById(R.id.right_icon);
        leftBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onLeftClick");
                onLeftItemClickListener.onLeftClick();
            }
        });
        rightbtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onRightClick");
                onRightItemClickListener.onRightClick();
            }
        });
    }

    public void setOnLeftItemClickListener(OnLeftItemClickListener onLeftItemClickListener){
        this.onLeftItemClickListener = onLeftItemClickListener;
    }

    public void setOnRightItemClickListener(OnRightItemClickListener onRightItemClickListener){
        this.onRightItemClickListener = onRightItemClickListener;
    }

    public interface OnLeftItemClickListener{
        void onLeftClick();
    }

    public interface OnRightItemClickListener{
        void onRightClick();
    }

    public void setLeftIcon(Drawable drawable){
        leftBtn.setBackground(drawable);
    }

    public void setRightbtn(Drawable drawable){
        rightbtn.setBackground(drawable);
    }
}
