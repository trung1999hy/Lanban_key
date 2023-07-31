package com.example.keyboardtheme;

public class Theme {
    private int theme;
    private boolean block = false;

    public int getTheme() {
        return theme;
    }

    public void setTheme(int theme) {
        this.theme = theme;
    }

    public boolean isBlock() {
        return block;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    public Theme() {
    }

    public Theme(int theme, boolean block) {
        this.theme = theme;
        this.block = block;
    }
}
