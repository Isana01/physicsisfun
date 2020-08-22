package com.example.myapplication;

public class ChapterItemViewModel {
    private String chapName,chapDesc;
    public ChapterItemViewModel(String chapName, String chapDesc) {
        this.chapName = chapName;
        this.chapDesc = chapDesc;
    }

    public String getChapName() {
        return chapName;
    }

    public void setChapName(String chapName) {
        this.chapName = chapName;
    }

    public String getChapDesc() {
        return chapDesc;
    }

    public void setChapDesc(String chapDesc) {
        this.chapDesc = chapDesc;
    }
}
