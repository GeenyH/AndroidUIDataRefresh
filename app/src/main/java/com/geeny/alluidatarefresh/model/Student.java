package com.geeny.alluidatarefresh.model;

/**
 * Created by Leslie Huang on 2018/10/13
 */
public class Student {
    private String name;

    private String num;

    public Student(String name, String num) {
        this.name = name;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
