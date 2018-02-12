package com.ricky.user.vo;

import java.io.Serializable;

/**
 * Describetion
 * Created  by  zhuangjiayin
 * Date : 2018/2/12
 */
public class UserVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;

    private String name;

    private Integer age;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
