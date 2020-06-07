package com.lanmushan.frameworktest;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author dy
 * @Date 2020/6/7 15:09
 * @Version 1.0
 */
@Slf4j
public class UserTest {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
