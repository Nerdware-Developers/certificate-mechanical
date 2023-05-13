package com.nerdware.mechanical.dash;

public class Items {
    int Image;
    String dash;

    public Items(int image, String dash) {
        Image = image;
        this.dash = dash;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getDash() {
        return dash;
    }

    public void setDash(String dash) {
        this.dash = dash;
    }
}
