package com.yinyxn.myjobjson;

/**
 * Created by yinyxn on 2016/4/7.
 */

public class Person {

    private String name;
    private String sex;
    private int age;
    private String address;

    public Person() {
    }

    public Person(String name, String sex, int age, String address) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}
