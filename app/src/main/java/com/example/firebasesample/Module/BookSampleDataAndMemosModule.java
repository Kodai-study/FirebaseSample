package com.example.firebasesample.Module;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.List;

@IgnoreExtraProperties
public class BookSampleDataAndMemosModule {

    private String name ;
    private String date;
    private List<BookMarkData> memoList;



    public BookSampleDataAndMemosModule(String name, String date,List<BookMarkData> memoList) {
        this.name = name;
        this.date = date;
        this.memoList = memoList;
    }


    public BookSampleDataAndMemosModule(){}
    public String getName() {
        return name;
    }
    public String getDate() {
        return date;
    }
    public List<BookMarkData> getMemoList() {
        return memoList;
    }
}
