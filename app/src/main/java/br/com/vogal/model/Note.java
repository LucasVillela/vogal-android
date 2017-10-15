package br.com.vogal.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Created by lbvil on 13/10/2017.
 */

public class Note {

    private String id;
    private String user;
    private String title;
    @SerializedName("text_raw")
    private String textRaw;
    @SerializedName("text_markdown")
    private String textMarkdown;
    @SerializedName("text_html")
    private String textHTML;
    private String align;
    @SerializedName("created_at")
    private Date createdAt;
    @SerializedName("updated_at")
    private Date updatedAt;
    private List<String> labels;
    private String notebook;
    @SerializedName("is_active")
    private Boolean isActive;

    public Note(){
    }

    public static Note basicNote(String notebook){
        Note note = new Note();

        note.setNotebook(notebook);
        note.setTextHTML("");
        note.setTitle("");
        note.setTextMarkdown("");
        note.setTextRaw("");
        note.setAlign("left");

        return note;
    }

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

    public String getTextRaw() {
        return textRaw;
    }

    public void setTextRaw(String textRaw) {
        this.textRaw = textRaw;
    }

    public String getTextMarkdown() {
        return textMarkdown;
    }

    public void setTextMarkdown(String textMarkdown) {
        this.textMarkdown = textMarkdown;
    }

    public String getTextHTML() {
        return textHTML;
    }

    public void setTextHTML(String textHTML) {
        this.textHTML = textHTML;
    }

    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
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

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public String getNotebook() {
        return notebook;
    }

    public void setNotebook(String notebook) {
        this.notebook = notebook;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
