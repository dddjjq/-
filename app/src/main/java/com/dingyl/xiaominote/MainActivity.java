package com.dingyl.xiaominote;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.dingyl.xiaominote.activity.NoteContentActivity;
import com.dingyl.xiaominote.activity.NoteFolderActivity;
import com.dingyl.xiaominote.adapter.GridAdapter;
import com.dingyl.xiaominote.adapter.ListAdapter;
import com.dingyl.xiaominote.data.BaseData;
import com.dingyl.xiaominote.db.DBUtil;
import com.dingyl.xiaominote.utils.Constants;
import com.dingyl.xiaominote.view.MainToolBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainToolBar.OnLeftItemClickListener
        ,MainToolBar.OnRightItemClickListener,View.OnClickListener{

    private static final String TAG = "MainActivity";
    private MainToolBar mainToolBar;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private int listGridStatus;//为0时是ListView，为1是GridView
    private ListView listView;
    private GridView gridView;
    private ArrayList<BaseData> arrayList;
    private FloatingActionButton addButton;
    private DBUtil dbUtil;
    private ListAdapter listAdapter;
    private GridAdapter gridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"onCreate");
        initView();
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG,"onResume");
        initData();
        initListener();
    }
    private void initView(){
        mainToolBar = findViewById(R.id.main_tool_bar);
        listView = findViewById(R.id.list_view);
        gridView = findViewById(R.id.grid_view);
        addButton = findViewById(R.id.floating_button);
    }

    private void initListener(){
        mainToolBar.setOnLeftItemClickListener(this);
        mainToolBar.setOnRightItemClickListener(this);
        addButton.setOnClickListener(this);
        listAdapter = new ListAdapter(this,arrayList);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, NoteContentActivity.class);
                intent.putExtra("data",arrayList.get(i));
                startActivity(intent);
            }
        });
        for(BaseData bd:arrayList){
            Log.d(TAG,bd.toString());
        }
//        gridAdapter = new GridAdapter(this,arrayList);
//        gridView.setAdapter(gridAdapter);
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(MainActivity.this, NoteContentActivity.class);
//                intent.putExtra("data",arrayList.get(i));
//                startActivity(intent);
//            }
//        });

    }

    private void initData(){
        sharedPreferences = getSharedPreferences(Constants.LIST_GRID_STATUS,MODE_PRIVATE);
        listGridStatus = sharedPreferences.getInt("status",0);
        changeRightIcon();
        arrayList = new ArrayList<>();
        if(dbUtil == null){
            dbUtil = new DBUtil(this);
        }
        dbUtil.openDateBase();
        arrayList = dbUtil.queryAllData();
        Log.d(TAG,"listGridStatus is : " + listGridStatus);
    }

    @Override
    public void onLeftClick() {
        Intent intent = new Intent(this, NoteFolderActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.folder_in,R.anim.folder_stay);
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
            listView.setVisibility(View.GONE);
            gridView.setVisibility(View.VISIBLE);
        }else {
            Drawable drawable = this.getResources().getDrawable(R.drawable.list_grid);
            mainToolBar.setRightbtn(drawable);
            listView.setVisibility(View.VISIBLE);
            gridView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.floating_button:
                listAdapter.notifyDataSetChanged();
                Intent intent = new Intent(this,NoteContentActivity.class);
                startActivity(intent);
                break;
        }
    }
}
