package com.yhikita.samples.modelmapper;

import lombok.Data;

@Data
public class MutableBean {
    String data;

    public MutableBean(String data) {
        this.data = data;
    }
}
