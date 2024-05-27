package com.example.prakt7.Data.Models;

public class Task {
    private int type;
    private String text;
    private int image;
    private String answer;
    public Task() {
        type = 0;
        text = null;
        image = 0;
        answer = null;
    }
    public Task(String text, int image, String answer) {
        this.text = text;
        this.image = image;
        this.answer = answer;
    }

    public String getText() {
        return text;
    }

    public int getImage() {
        return image;
    }

    public String getAnswer() {
        return answer;
    }
}
