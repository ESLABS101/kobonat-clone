package com.sonusahu.kobonat.model;

public class CatModel {

    String catName, iconUri,picUri;

    public CatModel() {
    }

    public CatModel(String catName, String iconUri, String picUri) {
        this.catName = catName;
        this.iconUri = iconUri;
        this.picUri = picUri;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getIconUri() {
        return iconUri;
    }

    public void setIconUri(String iconUri) {
        this.iconUri = iconUri;
    }

    public String getPicUri() {
        return picUri;
    }

    public void setPicUri(String picUri) {
        this.picUri = picUri;
    }
}
