package com.unirail.Channel;

public class ListViewItem {
    private String title;



    public ListViewItem(){}

    public void setTitle(String text) {

        title = text;

        // 화면에 출력할 tag (한글), Id 와 같은 역할을 하는 title(영어)

        /*
        title = text;
        int resId = Channel_main.mContext.getResources()
                .getIdentifier(text+"kr", "string", Channel_main.mContext.getPackageName());

        tag = Channel_main.mContext.getResources().getString(resId);
        */



    }

    public String getTitle() { return this.title; }

}


