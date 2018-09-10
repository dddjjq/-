package com.dingyl.xiaominote.data;

import java.io.Serializable;

public class BaseData implements Serializable{

    private String title;
    private String content;
    private String contentSummary;
    private String date;
    private int id;

    public String getTitle(){
        return content.split("\n")[0];
    }

    public String getContentSummary(){
        if(content.split("\n").length > 1){
            return content.split("\n")[1];
        }else {
            return "";
        }
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
