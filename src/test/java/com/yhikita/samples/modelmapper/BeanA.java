package com.yhikita.samples.modelmapper;

import java.util.List;

import lombok.Data;

@Data
public class BeanA {
    String str;
    int i;
    boolean b;
    String onlyInA;
    MutableBean bean;
    List<Detail> details;

    BeanA(String str, int i, boolean b, String onlyInA, MutableBean bean, List<Detail> details) {
        this.str = str;
        this.i = i;
        this.b = b;
        this.onlyInA = onlyInA;
        this.bean = bean;
        this.details = details;
    }

    @Data
    static class Detail {
        String str;
        int i;
        boolean b;

        Detail(String str, int i, boolean b) {
            this.str = str;
            this.i = i;
            this.b = b;
        }
    }
}
