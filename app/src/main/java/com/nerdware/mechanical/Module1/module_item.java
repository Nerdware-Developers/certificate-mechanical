package com.nerdware.mechanical.Module1;

public class module_item {
    int Image;
    String sub;
    String pub;

    public module_item(int image, String sub, String pub) {
        Image = image;
        this.sub = sub;
        this.pub = pub;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getPub() {
        return pub;
    }

    public void setPub(String pub) {
        this.pub = pub;
    }
}
