package com.yss.acs;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Test01 {

    @Test
    public void test() {
        Map<String, String> map = new HashMap<>();
        map.put("1", "小王");
        map.put("2", "小张");
        map.put("3", "小李");
        map.put("4", "小谭");
        map.put("5", "小飞");
        map.put("6", "小花");

        //增强型keySet遍历
        for (String s : map.keySet()) {
            System.out.println("key===>" + s + " value==>" + map.get(s));
        }
    }

    @Test
    public void test01() {
        Map<String, String> map = new HashMap<>();
        map.put("1", "小王");
        map.put("2", "小张");
        map.put("3", "小李");
        map.put("4", "小谭");
        map.put("5", "小飞");
        map.put("6", "小花");
        //增强型entrySet遍历
        for(Map.Entry<String,String> entry :map.entrySet()){
            System.out.println("key==>"+entry.getKey()+" value==>"+entry.getValue());
        }
    }

    @Test
    public void test02(){
        Map<String, String> map = new HashMap<>();
        map.put("1", "小王");
        map.put("2", "小张");
        map.put("3", "小李");
        map.put("4", "小谭");
        map.put("5", "小飞");
        map.put("6", "小花");
        //迭代器keySet循环
        Iterator<String> iterator = map.keySet().iterator();
        while(iterator.hasNext()){
            String next = iterator.next();
            System.out.println("key==>"+next+"  value==>"+map.get(next));
        }
    }


    @Test
    public void test03(){
        Map<String, String> map = new HashMap<>();
        map.put("1", "小王");
        map.put("2", "小张");
        map.put("3", "小李");
        map.put("4", "小谭");
        map.put("5", "小飞");
        map.put("6", "小花");
        //迭代器entrySet遍历
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, String> entry = iterator.next();
            System.out.println("key==>"+entry.getKey()+"  value==>"+entry.getValue());
        }
    }

    @Test
    public void test04(){
        Map<String, String> map = new HashMap<>();
        map.put("1", "小王");
        map.put("2", "小张");
        map.put("3", "小李");
        map.put("4", "小谭");
        map.put("5", "小飞");
        map.put("6", "小花");
       //Lambda表达式遍历
        map.forEach((key,value) -> System.out.println("key==>"+key+"  value==>"+value));
    }

    @Test
    public void test05(){
        Map<String, String> map = new HashMap<>();
        map.put("1", "小王");
        map.put("2", "小张");
        map.put("3", "小李");
        map.put("4", "小谭");
        map.put("5", "小飞");
        map.put("6", "小花");
        //Stream单线程遍历
        map.entrySet().stream().forEach(entry -> System.out.println("key==>"+entry.getKey()+"  value==>"+entry.getValue()));
    }

    @Test
    public void test06(){
        Map<String, String> map = new HashMap<>();
        map.put("1", "小王");
        map.put("2", "小张");
        map.put("3", "小李");
        map.put("4", "小谭");
        map.put("5", "小飞");
        map.put("6", "小花");
        //Stream多线程遍历
        map.entrySet().parallelStream().forEach(entry -> System.out.println("key==>"+entry.getKey()+"  value==>"+entry.getValue()));
        System.out.println("你好不???");
    }
}
