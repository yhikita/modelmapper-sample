package com.yhikita.samples.modelmapper;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

public class ModelMapperTest {

    @Test
    public void test01() {
        // prepare
        ModelMapper modelMapper = new ModelMapper();
        BeanA a = new BeanA("string", 5, true, "onlyInA", new MutableBean("data"),
                Arrays.asList(new BeanA.Detail("str1", 1, true), new BeanA.Detail("str2", 2, false),
                        new BeanA.Detail("str3", 3, true)));

        // execute
        BeanB b = modelMapper.map(a, BeanB.class);

        // verify
        assertThat(b.getStr(), is(a.getStr()));
        assertThat(b.getI(), is(a.getI()));
        assertThat(b.isB(), is(a.isB()));
        assertThat(b.getOnlyInB(), nullValue());
        assertThat(b.getBean(), is(a.getBean()));
        // System.out.println(b.getBean().hashCode());
        // System.out.println(a.getBean().hashCode());
        // a.getBean().setData("data2");
        // assertThat(b.getBean(), is(a.getBean()));
        assertThat(b.getDetails().size(), is(a.getDetails().size()));
        assertThat(b.getDetails().get(0).getStr(), is(a.getDetails().get(0).getStr()));
        assertThat(b.getDetails().get(0).getI(), is(a.getDetails().get(0).getI()));
        assertThat(b.getDetails().get(0).isB(), is(a.getDetails().get(0).isB()));
    }

    @Test
    public void test02() {
        // prepare
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<BeanA, BeanB> typeMap = modelMapper.createTypeMap(BeanA.class, BeanB.class);
        typeMap.addMapping(BeanA::getOnlyInA, BeanB::setOnlyInB);
        // typeMap.<String>addMapping(src -> src.getOnlyInA(), (dest, value) ->
        // dest.setOnlyInB(value));
        BeanA a = new BeanA("string", 5, true, "onlyInA", new MutableBean("data"),
                Arrays.asList(new BeanA.Detail("str1", 1, true), new BeanA.Detail("str2", 2, false),
                        new BeanA.Detail("str3", 3, true)));

        // execute
        BeanB b = modelMapper.map(a, BeanB.class);

        // verify
        assertThat(b.getStr(), is(a.getStr()));
        assertThat(b.getI(), is(a.getI()));
        assertThat(b.isB(), is(a.isB()));
        // assertThat(b.getOnlyInB(), nullValue());
        assertThat(b.getBean(), is(a.getBean()));
        //
        assertThat(b.getDetails().size(), is(a.getDetails().size()));
        assertThat(b.getDetails().get(0).getStr(), is(a.getDetails().get(0).getStr()));
        assertThat(b.getDetails().get(0).getI(), is(a.getDetails().get(0).getI()));
        assertThat(b.getDetails().get(0).isB(), is(a.getDetails().get(0).isB()));
        //
        assertThat(b.getOnlyInB(), is(a.getOnlyInA()));

    }

    @Test
    public void test03() {
        // prepare
        ModelMapper modelMapper = new ModelMapper();
        Map<String, Object> map = new HashMap<>();
        map.put("str", "string");
        map.put("i", 5);
        map.put("b", true);

        // execute
        BeanB b = modelMapper.map(map, BeanB.class);

        // verify
        assertThat(b.getStr(), is(map.get("str")));
        assertThat(b.getI(), is(map.get("i")));
        assertThat(b.isB(), is(map.get("b")));
        assertThat(b.getOnlyInB(), nullValue());
    }

}
