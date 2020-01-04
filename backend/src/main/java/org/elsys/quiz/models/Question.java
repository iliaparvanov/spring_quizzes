package org.elsys.quiz.models;

public class Question {
    private final int id;
    private final String name;
    private final Quiz quiz;

    public Question(int id, String name, Quiz quiz) {
        this.id = id;
        this.name = name;
        this.quiz = quiz;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Quiz getQuiz() {
        return quiz;
    }
}
