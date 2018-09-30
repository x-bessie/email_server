package entity;

import java.io.Serializable;

public class Task implements Comparable<Task>, Serializable {
    private  int ids;
    private String name;
    private int site;
    private int type;
    private String url;
    private String remark;
    private int language;
    private int sex;
    private int country;
    private String addon;
    private String city;
    private int status;

    public int compareTo(Task o){
        if(this.ids>o.getIds()){
            return 1;
        }else {
            return 0;
        }
    }

    public int getIds() {
        return ids;
    }

    public void setIds(int ids) {
        this.ids = ids;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSite() {
        return site;
    }

    public void setSite(int site) {
        this.site = site;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getLanguage() {
        return language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getCountry() {
        return country;
    }

    public void setCountry(int country) {
        this.country = country;
    }

    public String getAddon() {
        return addon;
    }

    public void setAddon(String addon) {
        this.addon = addon;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
