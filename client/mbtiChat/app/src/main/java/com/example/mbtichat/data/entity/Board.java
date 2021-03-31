package com.example.mbtichat.data.entity;

public class Board {
    private int idx;
    private String name;
    private String description;

    Board(int idx, String name, String description){
        this.idx = idx;
        this.name = name;
        this.description = description;
    }

    public int getIdx() {
        return idx;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}
