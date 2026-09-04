package com.astrax.app;

public class ToolModel {
    public int id;
    public String title;
    public String desc;
    public String hint;
    public String output;
    public String category;
    public String execType;

    public ToolModel() {}

    public ToolModel(int id, String title, String desc, String hint, String output, String category, String execType) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.hint = hint;
        this.output = output;
        this.category = category;
        this.execType = execType;
    }
}
