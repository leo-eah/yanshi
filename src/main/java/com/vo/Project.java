package com.vo;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class Project {
    private String proName;
    private String proVersion;
    private String author;
    private String createTime;
    private CommonsMultipartFile pro;
    private String id;
    private  String fileName;
    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProVersion() {
        return proVersion;
    }

    public void setProVersion(String proVersion) {
        this.proVersion = proVersion;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public CommonsMultipartFile getPro() {
        return pro;
    }

    public void setPro(CommonsMultipartFile pro) {
        this.pro = pro;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
