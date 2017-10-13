package br.com.vogal.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by lbvil on 12/10/2017.
 */

public class Notebook {


    private String id;
    private String user;
    private String title;

    @SerializedName("created_at")
    private Date createdAt;

    @SerializedName("updated_at")
    private Date updatedAt;

    @SerializedName("is_default")
    private Boolean isDefault;

    @SerializedName("is_active")
    private Boolean isActive;

    @SerializedName("is_blog_attached")
    private Boolean isBlogAttached;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getBlogAttached() {
        return isBlogAttached;
    }

    public void setBlogAttached(Boolean blogAttached) {
        isBlogAttached = blogAttached;
    }
}


