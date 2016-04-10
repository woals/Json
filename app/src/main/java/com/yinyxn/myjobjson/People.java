package com.yinyxn.myjobjson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yinyxn on 2015/12/29.
 */
public class People {

    String firstName;
    String lastName;
    String sex;
    int age;

    Address address = new Address();//不 new 就不能引用 public static class Address 中的属性
    List<Phone> phoneNumber = new ArrayList<>();

    public static class Address{
        String streetAddress;
        String city;
        String state;
        String postalCode;

        @Override
        public String toString() {
            return "Address{" +
                    "streetAddress='" + streetAddress + '\'' +
                    ", city='" + city + '\'' +
                    ", state='" + state + '\'' +
                    ", postalCode='" + postalCode + '\'' +
                    '}';
        }
    }
    public static class Phone{
        String type;
        String number;

        @Override
        public String toString() {
            return "Phone{" +
                    "type='" + type + '\'' +
                    ", number='" + number + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "People{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", address=" + address +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}
