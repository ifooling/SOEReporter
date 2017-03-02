package com.teleware.plugin;

import org.apache.commons.io.FilenameUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * Created by miaoj on 2017/3/1.
 */
public class Description {
    private String name;
    private String description;
    private String version;
    private String author;
    private String company;
    private String date;
    private String target;
    private String libJarsPath;

    @Override
    public String toString() {
        return String.format("Name=%s,Description=%s,Version=%s,Author=%s,Company=%s,Date=%s,Targets=%s,libjars=%s", name, description, version, author, company, date, target, libJarsPath);
    }

    public Description(String jarPath) {
        this.name = FilenameUtils.getBaseName(jarPath);
        this.description = this.name;
        this.version = "1.0.0";
        this.author = "admin";
        this.company = "Teleware";
        this.date = new SimpleDateFormat("yyyy年MM月dd日 E").format(new Date()).toString();
        this.target = "10.2";
        this.libJarsPath = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getLibJarsPath() {
        return libJarsPath;
    }

    public void setLibJarsPath(String libJarsPath) {
        this.libJarsPath = libJarsPath;
    }
}
