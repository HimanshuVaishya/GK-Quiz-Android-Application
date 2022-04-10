package com.example.dynamicapplicationtwo.feedback;

public class FeedbackModel {

    private String id, name, email, feedback;

    public FeedbackModel(String id, String name, String email, String feedback) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.feedback = feedback;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
