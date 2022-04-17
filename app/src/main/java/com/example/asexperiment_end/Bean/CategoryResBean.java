package com.example.asexperiment_end.Bean;

public class CategoryResBean {
    private String title;
    private int resWhite;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getResWhite() {
        return resWhite;
    }

    public void setResWhite(int resWhite) {
        this.resWhite = resWhite;
    }

    @Override
    public String toString() {
        return "CategoryResBean{" +
                "title='" + title + '\'' +
                ", resWhite=" + resWhite +
                '}';
    }
}
