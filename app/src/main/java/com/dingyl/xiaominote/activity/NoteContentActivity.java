package com.dingyl.xiaominote.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.dingyl.xiaominote.R;
import com.dingyl.xiaominote.data.BaseData;
import com.dingyl.xiaominote.db.DBUtil;
import com.dingyl.xiaominote.utils.Constants;
import com.dingyl.xiaominote.utils.SharedPreferencesUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NoteContentActivity extends AppCompatActivity {

    private static final String TAG = "NoteContentActivity";
    private Toolbar contentToolbar;
    private EditText editText;
    private BaseData baseData;
    private SimpleDateFormat format;
    private String dateAndTime;
    private String date;
    private String time;
    private DBUtil dbUtil;
    private SharedPreferencesUtils utils;
    private boolean isExist = false;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_content);
        initView();
        initData();
        initListener();
    }

    private void initView(){
        contentToolbar = findViewById(R.id.note_content_tool_bar);
        editText = findViewById(R.id.content_edit);
        contentToolbar.setNavigationIcon(R.drawable.back);
    }

    private void initData(){
        dbUtil = new DBUtil(this);
        dbUtil.openDateBase();
        utils = new SharedPreferencesUtils(this, Constants.ITEM_ID_SP);
        baseData = new BaseData();
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateAndTime = format.format(new Date());
        if(getIntent().hasExtra("data")){
            Intent intent = getIntent();
            baseData = (BaseData) intent.getSerializableExtra("data");
            isExist = true;
            id = baseData.getId();
            Log.d(TAG,"hasExtra data is : " + id);
        }else {
            baseData.setDate(dateAndTime);
            isExist = false;
            id = utils.getInt(Constants.ID,0);
            Log.d(TAG,"don't hasExtra data is : " + id);
        }
        date = baseData.getDate().split(" ")[0];
        time = baseData.getDate().split(" ")[1];
        editText.setText(baseData.getContent());
        contentToolbar.setTitle(date);
        contentToolbar.setSubtitle(time);
        setSupportActionBar(contentToolbar);
    }

    private void initListener(){
        contentToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void finish(){
        super.finish();
        baseData.setContent(editText.getText().toString());
        baseData.setDate(dateAndTime);
        if(isExist){
            Log.d(TAG,"baseData.getId() is : " + baseData.getId());
            dbUtil.updateData(baseData.getId(),baseData);
        }else {
            id = utils.getInt(Constants.ID,0)+1;
            utils.putInt(Constants.ID,id);
            baseData.setId(id);
            dbUtil.insertData(baseData);
            Log.d(TAG,"set ID is : " + id);
        }
    }
}
