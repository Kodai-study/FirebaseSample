package com.example.firebasesample.Module;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class BookSampleDataModule {
    public String name ;
    public String date;

    public BookSampleDataModule(String name, String date) {
        this.name = name;
        this.date = date;
    }
}
