package com.sonusahu.kobonat.model;

public class StoreModel {

    String storeName, storeLogo,cover,fbLink,instaLink,contact,web,latLang;

    public StoreModel(String storeName, String storeLogo, String latLang) {
        this.storeName = storeName;
        this.storeLogo = storeLogo;
        this.latLang = latLang;
    }

    public StoreModel(String storeName, String storeLogo, String cover, String fbLink, String instaLink, String contact, String web, String latLang) {
        this.storeName = storeName;
        this.storeLogo = storeLogo;
        this.cover = cover;
        this.fbLink = fbLink;
        this.instaLink = instaLink;
        this.contact = contact;
        this.web = web;
        this.latLang = latLang;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreLogo() {
        return storeLogo;
    }

    public void setStoreLogo(String storeLogo) {
        this.storeLogo = storeLogo;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getFbLink() {
        return fbLink;
    }

    public void setFbLink(String fbLink) {
        this.fbLink = fbLink;
    }

    public String getInstaLink() {
        return instaLink;
    }

    public void setInstaLink(String instaLink) {
        this.instaLink = instaLink;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getLatLang() {
        return latLang;
    }

    public void setLatLang(String latLang) {
        this.latLang = latLang;
    }

    public StoreModel() {
    }
}
