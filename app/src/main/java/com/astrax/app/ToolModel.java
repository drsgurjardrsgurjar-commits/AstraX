package com.astrax.app;

public class ToolModel {
    public int id;
    public String title;
    public String desc;
    public String category;
    public String hint;
    public String output;

    public ToolModel(int id, String title, String desc, String category, String hint, String output) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.category = category;
        this.hint = hint;
        this.output = output;
    }
}
