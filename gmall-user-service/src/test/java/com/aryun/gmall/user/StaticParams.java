package com.aryun.gmall.user;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class StaticParams {

    private static final String config="/asdfa/asdfadf/asdfas/dfasfasdf";
    private static int NUM_A = getA();
    private static int NUM_B = getB();
    private static List<String> LIST_A = getListA();
    static{
        System.out.println("静态代码块初始化");
        System.out.println(config.length());
    }

    private StaticParams() {
        System.out.println("初始化构造方法");
    }

    public static StaticParams getInstance() {

        return new StaticParams();
    }

    private static int getA() {
        System.out.println("初始化A");
        return 10;
    }

    private static int getB() {
        System.out.println("初始化B");
        return 20;
    }

    private static List<String> getListA() {
        System.out.println("初始化List");
        return new ArrayList<String>();
    }

    public static void main(String[] args) {
        StaticParams.getInstance();
        File file=new File("C:\\init");
    }
}
