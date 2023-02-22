package com.dbrud1032.loginchatbotapp.model;

public class User {
    // 사ㅣ용자 계정 정보 모델 클래스
    private String emailId;
    private  String password;

    private  String message;

    // 계정 고유 토큰정보
    private String idToken;

    public User() {
    }

    public User(String emailId, String message) {
        this.emailId = emailId;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}