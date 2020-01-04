package org.elsys.quiz.models;

public class Quiz {
    private final int id;
    private final String name;

    public Quiz(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
