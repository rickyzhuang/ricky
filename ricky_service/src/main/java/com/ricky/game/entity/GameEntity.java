package com.ricky.game.entity;

import java.io.Serializable;

public class GameEntity implements Serializable {
    private String id;

    private String name;

    private String rule;

    private static final long serialVersionUID = 1L;

    public GameEntity(String id, String name, String rule) {
        this.id = id;
        this.name = name;
        this.rule = rule;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRule() {
        return rule;
    }
}