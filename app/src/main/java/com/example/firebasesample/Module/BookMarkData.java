package com.example.firebasesample.Module;

public class BookMarkData {
    private int pageNumber;
    private int bookMark;
    private String memo;

    private boolean isResolved;

    public BookMarkData(int pageNumber, int bookMark, String memo, boolean isResolved) {
        this.pageNumber = pageNumber;
        this.bookMark = bookMark;
        this.memo = memo;
        this.isResolved = isResolved;
    }

    public BookMarkData(){}

    public int getPageNumber() {
        return pageNumber;
    }

    public int getBookMark() {
        return bookMark;
    }

    public String getMemo() {
        return memo;
    }

    public boolean isResolved() {
        return isResolved;
    }
}

