package com.example.heinhtet.newsreader;

import java.io.Serializable;

/**
 * Created by heinhtet on 2/13/17.
 */
public class Google implements Serializable {
    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getmPublishedAt() {
        return mPublishedAt;
    }

    public void setmPublishedAt(String mPublishedAt) {
        this.mPublishedAt = mPublishedAt;
    }

    String mTitle;
    String mDescription;
    String mImageUrl;
    String mUrl;
    String mPublishedAt;


    public Google(String title,String description,String imgeUrl,String url,String publishedAt){
        mTitle = title;
        mDescription = description;
        mImageUrl = imgeUrl;
        mUrl = url;
        mPublishedAt = publishedAt;

    }
}
