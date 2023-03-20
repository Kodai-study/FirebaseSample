package com.example.firebasesample.Module;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.List;

@IgnoreExtraProperties
public class BookSampleDataAndMemosModule {
    public String name ;
    public String date;

    public List<BookMarkDatas> memoList;

    public BookSampleDataAndMemosModule(String name, String date,List<BookMarkDatas> memoList) {
        this.name = name;
        this.date = date;
        this.memoList = memoList;
    }

    public BookSampleDataAndMemosModule(){}

}
