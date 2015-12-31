package persistent.impl;

import java.sql.*;

public class ConnectionSQL {
    static Connection conn = null;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hotel"
                    , "root", "123456");
            //System.out.println("连接数据库成功。");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("连接数据库失败。");
        }

    }

    public PreparedStatement prepareSQL(String sql) {
        try {
            return conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public int updateSQL(PreparedStatement statement) {
        int rn = -1;
        try {
            rn = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("执行sql失败。");
        }
        //System.out.println("完成操作");
        return rn;
    }

    public ResultSet querySQL(String sql) {
        ResultSet rSet = null;
        try {
            Statement stmt = conn.createStatement();
            rSet = stmt.executeQuery(sql);
            //System.out.println("完成sql查询操作。");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("执行sql失败。");
        }
        //System.out.println("完成操作");
        return rSet;
    }

    public int updateSQL(String sql) {
        int rn;
        try {
            Statement stmt = conn.createStatement();
            rn = stmt.executeUpdate(sql);
            //System.out.println("完成sql更新操作。");
            return rn;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("执行sql失败。");
        }
        return -1;
    }
}
