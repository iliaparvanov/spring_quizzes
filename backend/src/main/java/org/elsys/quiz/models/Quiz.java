package org.elsys.quiz.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Quiz {
    private final int id;
    private final String name;
    private List<Question> content;

    public Quiz(@JsonProperty("id") int id, @JsonProperty("name") String name, @JsonProperty("content") List<Question> content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }

    public List<Question> getContent() {
        return content;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setContent(List<Question> content) {
        this.content = content;
    }
}
