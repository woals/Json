package com.yinyxn.myjobjson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yinyxn on 2016/4/8.
 */
public class Test {
    String error;
    String status;
    String date;
    List<Results> results = new ArrayList<>();
    public Test(String error, String status, String date, List<Results> results) {
        this.error = error;
        this.status = status;
        this.date = date;
        this.results = results;
    }

    public static class Results {
        String currentCity;
        String pm25;
        List<Index> index = new ArrayList<>();
        public static class Index{
            String title;
            String zs;
            String tipt;
            String des;
            public Index(String title, String zs, String tipt, String des) {
                this.title = title;
                this.zs = zs;
                this.tipt = tipt;
                this.des = des;
            }
            @Override
            public String toString() {
                return "title='" + title + '\'' +
                        ", zs='" + zs + '\'' +
                        ", tipt='" + tipt + '\'' +
                        ", des='" + des;
            }
        }

        @Override
        public String toString() {
            return "currentCity='" + currentCity + '\'' +
                    ", pm25='" + pm25 + '\'' +
                    ", index=" + index;
        }
    }

    @Override
    public String toString() {
        return "Test{" +
                "error='" + error + '\'' +
                ", status='" + status + '\'' +
                ", date='" + date + '\'' +
                ", results=" + results +
                '}';
    }
}
