package com.dingyl.xiaominote.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtils {

    private Context context;
    private String key;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SharedPreferencesUtils(Context context,String key){
        this.context = context;
        this.key = key;
        sharedPreferences = context.getSharedPreferences(key,Context.MODE_PRIVATE);
    }

    public void putInt(String intKey,int i){
        editor = sharedPreferences.edit();
        editor.putInt(intKey,i);
        editor.apply();
    }

    public void putBoolean(String booleanKey,boolean b){
        editor = sharedPreferences.edit();
        editor.putBoolean(booleanKey,b);
        editor.apply();
    }

    public void putString(String stringKey,String s){
        editor = sharedPreferences.edit();
        editor.putString(stringKey,s);
        editor.apply();
    }

    public int getInt(String intKey,int defaultValue){
        return sharedPreferences.getInt(intKey,defaultValue);
    }

    public boolean getBoolean(String booleanKey,boolean defaultValue){
        return sharedPreferences.getBoolean(booleanKey,defaultValue);
    }

    public String getString(String stringKey,String defaultValue){
        return sharedPreferences.getString(stringKey,defaultValue);
    }
}
