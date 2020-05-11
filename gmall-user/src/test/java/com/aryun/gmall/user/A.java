package com.aryun.gmall.user;

public class A {
    public static String a;

    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println("开始classforname");
        Class.forName("com.aryun.gmall.user.bean.NewClass");
        System.out.println("结束classforname");
    }

}
