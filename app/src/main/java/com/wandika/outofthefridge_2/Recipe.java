package com.wandika.outofthefridge_2;

import android.os.Parcelable;

public class Recipe {
    private String id;
    private String url;
    private String label;
    private String calories;
    private String time;
    private String imgUrl;

    public Recipe()
    {

    }

    public Recipe (String id, String url, String label, String calories, String time, String imgUrl)
    {
        this.id = id;
        this.url = url;
        this.label = label;
        this.calories = calories;
        this.time = time;
        this.imgUrl = imgUrl;
    }

    //id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //url
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //Label
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    //Calories
    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    //Time
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    //Image Url
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

//    @Override
//    public void writeToParcel(Parcel dest, int arg1) {
//        //TODO Auto-generated method stub
//        dest.writeString(id);
//        dest.writeString(label);
//        dest.writeString(calories);
//        dest.writeString(time);
//        dest.writeString(imgUrl);
//    }
//
//    public Recipe(Parcel in) {
//        id = in.readString();
//        label = in.readString();
//        calories = in.readString();
//        time = in.readString();
//        imgUrl = in.readString();
//    }
}
