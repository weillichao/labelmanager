package com.bigdata.labelmanager.util;


import com.bigdata.labelmanager.domain.Condition;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtil {
    private static final String driverClass = "com.mysql.jdbc.Driver";
    private static final String jdbcUrl = "jdbc:mysql://192.168.202.166:3306/label_manager?useUnicode=true&characterEncoding=utf8";
    private static final String user = "root";
    private static final String password = "Sczq@1234!";
    private static Gson gson =new GsonBuilder().serializeNulls().create();

    public static Connection getConn(String url,String user,String passward) {
        // 1.注册驱动
        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 2.创建Connection(数据库连接对象)
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, passward);
         //   conn.setAutoCommit(false);
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*
         * Connection是Statement的工厂，一个Connection可以生产多个Statement。
         * Statement是ResultSet的工厂，一个Statement却只能对应一个ResultSet（它们是一一对应的关系）。
         * 所以在一段程序里要用多个ResultSet的时候，必须再Connection中获得多个Statement，然后一个Statement对应一个ResultSet。
         */
        return null;
    }

    /**
     * 关闭编译的 SQL 语句的对象
     * @param stmt
     */
    public static void close(Statement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭结果集
     * @param rs
     */
    public static void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭连接(数据库连接对象)
     * @param conn
     */
    public static void close(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static  List<Map<String,String>> find(List<Condition> list,int start,int num) {
        List<Map<String,String>> listName=new ArrayList<Map<String,String>>();
        Connection conn = DBUtil.getConn(jdbcUrl,user,password);
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;


        String sql = "select client_id,client_name,client_type,status from user_label where ";
        String cd=getSql(list);
        //  Student student = null;
        sql=sql+cd+" order by client_id desc limit "+start+","+num;
       // sql=sql+cd;
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
           // pstmt =conn.prepareStatement()
                    //  pstmt.setInt(1, id);
            resultSet = pstmt.executeQuery();


            while (resultSet.next()) {
             //   System.out.println("sss");
                Map<String,String> map=new HashMap<String,String>();
                map.put("id",resultSet.getString(1));
                map.put("name",resultSet.getString(2));
                map.put("type",resultSet.getString(3));
                map.put("status",resultSet.getString(4));
                listName.add(map);
            }




        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(resultSet);
            DBUtil.close(pstmt);
            DBUtil.close(conn);
        }
        return  listName;
    }

   private static String getSql(List<Condition> list)
   {
       String result="";
       for (int i=0;i<list.size()-1;i++)
       {
           Condition cd=list.get(i) ;
           String condition=cd.getId()+"='"+cd.getText()+"'";
           result=result+condition+" AND ";
       }
       Condition cd=list.get(list.size()-1);
       String condition=cd.getId()+"='"+cd.getText()+"'";
       result=result+condition;
       return result;
   }
    public static  int getNum(List<Condition> list)
    {
        List<Map<String,String>> listName=new ArrayList<Map<String,String>>();
        Connection conn = DBUtil.getConn(jdbcUrl,user,password);
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        int result=0;

        String sql = "select count(client_id) from user_label where ";
        String cd=getSql(list);
        //  Student student = null;
        sql=sql+cd;
        // sql=sql+cd;
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            // pstmt =conn.prepareStatement()
            //  pstmt.setInt(1, id);
            resultSet = pstmt.executeQuery();
           // result=resultSet.getInt(1);

            while( resultSet.next()){
                result=resultSet.getInt(1);
            }




        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(resultSet);
            DBUtil.close(pstmt);
            DBUtil.close(conn);
        }
        return  result;
    }
}
