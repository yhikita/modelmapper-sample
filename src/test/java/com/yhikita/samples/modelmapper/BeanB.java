package com.yhikita.samples.modelmapper;

import java.util.List;

import lombok.Data;

@Data
public class BeanB {
    String str;
    int i;
    boolean b;
    String onlyInB;
    MutableBean bean;
    List<Detail> details;

    @Data
    static class Detail {
        String str;
        int i;
        boolean b;
    }

}
