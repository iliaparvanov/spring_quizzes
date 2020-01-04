package org.elsys.quiz.models;

public class Submission {
    private final int id;
    private final int score;
    private final Quiz quiz;

    public Submission(int id, int score, Quiz quiz) {
        this.id = id;
        this.score = score;
        this.quiz = quiz;
    }

    public int getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public Quiz getQuiz() {
        return quiz;
    }
}
