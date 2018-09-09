package com.dingyl.xiaominote;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.ListView;

import com.dingyl.xiaominote.activity.NoteFolderActivity;
import com.dingyl.xiaominote.data.BaseData;
import com.dingyl.xiaominote.utils.Constants;
import com.dingyl.xiaominote.view.MainToolBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainToolBar.OnLeftItemClickListener
        ,MainToolBar.OnRightItemClickListener{

    private static final String TAG = "MainActivity";
    private MainToolBar mainToolBar;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private int listGridStatus;//为0时是ListView，为1是GridView
    private ListView listView;
    private GridView gridView;
    private ArrayList<BaseData> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initListener();
    }

    private void initView(){
        mainToolBar = findViewById(R.id.main_tool_bar);
        listView = findViewById(R.id.list_view);
        gridView = findViewById(R.id.grid_view);
    }

    private void initListener(){
        mainToolBar.setOnLeftItemClickListener(this);
        mainToolBar.setOnRightItemClickListener(this);
    }

    private void initData(){
        sharedPreferences = getSharedPreferences(Constants.LIST_GRID_STATUS,MODE_PRIVATE);
        listGridStatus = sharedPreferences.getInt("status",0);
        changeRightIcon();
        arrayList = new ArrayList<>();
        Log.d(TAG,"listGridStatus is : " + listGridStatus);
    }

    @Override
    public void onLeftClick() {
        Intent intent = new Intent(this, NoteFolderActivity.class);
        startActivity(intent);
    }

    @Override
    public void onRightClick() {
        listGridStatus = (listGridStatus + 1) % 2;
        Log.d(TAG,"setListGridStatus : " + listGridStatus);
        editor = sharedPreferences.edit();
        editor.putInt("status",listGridStatus);
        editor.apply();
        changeRightIcon();
    }

    public void changeRightIcon(){
        if(listGridStatus == 0){
            Drawable drawable = this.getResources().getDrawable(R.drawable.list);
            mainToolBar.setRightbtn(drawable);
        }else {
            Drawable drawable = this.getResources().getDrawable(R.drawable.list_grid);
            mainToolBar.setRightbtn(drawable);
        }
    }
}
