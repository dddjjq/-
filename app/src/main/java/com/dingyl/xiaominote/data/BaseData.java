package com.dingyl.xiaominote.data;

public class BaseData {

    private String title;
    private String content;
    private String contentSummary;
    private String date;

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
}
