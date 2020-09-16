package com.aryun.gmall.user;

import java.sql.*;

public class MySqlDiver {
    public static void main(String[] args){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //1.注册数据库驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.获取数据库连接
            conn = DriverManager.getConnection("jdbc:mysql:///springdb","root","root");
            //3.获取传输器
            ps = conn.prepareStatement("select * from stu where id < ?");
            ps.setInt(1, 4);
            //4.执行sql获取结果集
            rs = ps.executeQuery();
            //5.遍历结果集获取结果
            while(rs.next()){
                String name = rs.getString("name");
                System.out.println(name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //6.关闭资源
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally{
                    rs = null;
                }
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally{
                    ps = null;
                }
            }
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally{
                    conn = null;
                }
            }
        }

    }
}
