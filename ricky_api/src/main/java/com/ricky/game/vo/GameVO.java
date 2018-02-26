package com.ricky.game.vo;

import java.io.Serializable;

/**
 * Describetion
 * Created  by  zhuangjiayin
 * Date : 2018/2/26
 */
public class GameVO implements Serializable {
    private String id;

    private String name;

    private String rule;

    private static final long serialVersionUID = 1L;

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

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }
}
