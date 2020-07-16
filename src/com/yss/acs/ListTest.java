package com.yss.acs;

import java.util.ArrayList;
import java.util.List;

public class ListTest {
    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        list.add(new Person(1001, "小白", "Female", "Single", 10));
        list.add(new Person(1002, "小黑", "Male", "Single", 200));
        list.add(new Person(1003, "比丢", "Male", "Married", 5));
        list.add(new Person(1004, "谛听", "Male", "Single", 280));
        list.add(new Person(1005, "老君", "Male", "Married", 500));
        list.add(new Person(1006, "山新", "Female", "Single", 10));
        list.add(new Person(1007, "红太狼", "Female", "Married", 26));
        list.add(new Person(1008, "灰太狼", "Male", "Married", 27));
        list.add(new Person(1009, "蕉太狼", "Male", "Single", 12));
        list.add(new Person(1010, "香太狼", "Female", "Single", 12));
        list.add(new Person(1011, "喜喜", "Male", "Single", 11));
        list.add(new Person(1012, "懒懒", "Male", "Single", 9));
        list.add(new Person(1013, "暖暖", "Female", "Single", 12));
        //①原始数据遍历
        //list.stream().forEach(person -> System.out.println(person));
        //②女性
        //list.stream().filter(person -> "Female".equalsIgnoreCase(person.getGender())).forEach(person -> System.out.println(person));
        //List<Person> personList = list.stream().filter(person -> "female".equalsIgnoreCase(person.getGender())).collect(Collectors.toList());
        //personList.stream().forEach(person -> System.out.println(person));
        //③年龄大于10的
        //list.stream().filter(person -> person.getAge()>10).forEach(person -> System.out.println(person));
        //④按年龄排序
        //list.stream().sorted((p1,p2) ->p1.getAge().compareTo(p2.getAge())).forEach(person -> System.out.println(person));
        //⑤女性且单身
        list.stream().filter(person -> "female".equalsIgnoreCase(person.getGender()))
                .filter(person -> "single".equalsIgnoreCase(person.getMaritalStatus()))
                .forEach(person -> System.out.println(person));
        //⑥原始数据并行遍历
        System.out.println("-------------------------------------------------------------------------------");
        list.parallelStream().forEach(person -> System.out.println(person));
    }
}

/**
 * Person测试类
 */
class Person {
    private Integer pNo;
    private String name;
    private String gender; // 性别 female-女；male-男
    private String maritalStatus; // 婚姻状况 married-已婚；single-单身
    private Integer age;

    public Person(Integer pNo, String name, String gender, String maritalStatus, Integer age) {
        this.pNo = pNo;
        this.name = name;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
        this.age = age;
    }

    public Integer getpNo() {
        return pNo;
    }

    public void setpNo(Integer pNo) {
        this.pNo = pNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person [pNo=" + pNo
                + ", name=" + name
                + ", gender=" + ("male".equalsIgnoreCase(gender) ? "男" : "女")
                + ", maritalStatus=" + ("married".equalsIgnoreCase(maritalStatus) ? "已婚" : "单身")
                + ", age=" + age + "]";
    }
}
