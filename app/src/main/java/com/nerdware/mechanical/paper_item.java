package com.nerdware.mechanical;

public class paper_item {
    int image;
    String pap;

    public paper_item(int image, String pap) {
        this.image = image;
        this.pap = pap;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getPap() {
        return pap;
    }

    public void setPap(String pap) {
        this.pap = pap;
    }
}
