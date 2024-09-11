package com.example.fingerprint;

public class ResponseModel {

    String message;
    String title, url, date,ip;
    private String username;
    private String password;
    String webcode;
    String status;


    public ResponseModel(String message) {

        this.message = message;
    }

    public ResponseModel(String title, String url, String date, String ip, String status, String username, String password,String webcode) {
        this.title = title;
        this.url = url;
        this.date = date;
        this.ip = ip;
        this.status = status;
        this.username = username;
        this.password = password;
        this.webcode = webcode;
    }

    public String getWebcode() {
        return webcode;
    }

    public void setWebcode(String webcode) {
        this.webcode = webcode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ResponseModel() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {

        this.message = message;
    }
}
