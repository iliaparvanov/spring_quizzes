package org.elsys.quiz.models;

public class Answer {
    private final int id;
    private final boolean is_correct;
    private final String text;
    private final Question question;

    public Answer(int id, boolean is_correct, String text, Question question) {
        this.id = id;
        this.is_correct = is_correct;
        this.text = text;
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public boolean isIs_correct() {
        return is_correct;
    }

    public String getText() {
        return text;
    }

    public Question getQuestion() {
        return question;
    }
}
