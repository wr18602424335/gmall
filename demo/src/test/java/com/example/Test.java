package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        int i=0;
        i++;
        String[] str={"1","2","3"};
        List list1=Arrays.asList(str);
        //list1.remove("1");
        System.out.println(list1);

        List strList=new ArrayList();
        strList.add("1");
        strList.add("2");
        System.out.println(strList);
        strList.remove("1");
        System.out.println(strList);
        System.out.println(i);
    }

}
