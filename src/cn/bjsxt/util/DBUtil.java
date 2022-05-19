package cn.bjsxt.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


/**
 * 软编码，将配置信息生成一个配置文件，然后让程序在执行过程中，读取配置信息
 * 好处：可以动态调整配置信息，有助于后续代码的维护
 *
 *Java中，提供了一个类，叫Properties类，用于读取properties文件
 */
public class DBUtil {
    private static  String url;
    private static  String user;
    private static  String password;

    static {
        try {
            //创建Properties对象
            Properties prop=new Properties();
            //加载配置文件
            prop.load(DBUtil.class.getClassLoader().getResourceAsStream("db.properties"));
            //读取信息并进行初始化,trim()的作用是取出字符串两端多余的字符或者是其他预定义字符
            String driver = prop.getProperty("jdbc.driver").trim();
            url=prop.getProperty("jdbc.url").trim();
            user=prop.getProperty("jdbc.user").trim();
            password=prop.getProperty("jdbc.password").trim();


            //注册驱动
            Class.forName(driver);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接
     */
    public static Connection getConn() {
        Connection conn=null;
        try {
            conn=DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            System.out.println(
                    "DBUtil.getConn(连接创建失败, 请检查[url]:" + url + ", [user]:" + user + ", [password]:" + password + ")");
        }
        return conn;
    }

    /**
     * 获取sql发送器
     */
    public static Statement	 getStmt(Connection conn) {
        Statement stmt=null;
        try {
            stmt=conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stmt;
    }

    /**
     * 获取预处理发送器
     * @param conn
     * @param sql
     * @return
     */
    public static PreparedStatement getPstmt(Connection conn,String sql) {
        PreparedStatement pstmt=null;
        try {
            pstmt=conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pstmt;
    }

    /**
     * 动态绑定参数,如果sql语句中有问号就使用，且按顺序匹配
     * @param pstmt
     * @param params
     */
    public static void bindParam(PreparedStatement pstmt,Object...params) {
        //...可变参数，可以被认为是一个数组
        try {
            for (int i = 1; i <= params.length; i++) {
                pstmt.setObject(i, params[i-1]);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行修改操作pstmt.executeUpdate()，rs为null
     * 执行查询操作pstmt.executeQuery()，rs才出现
     * @param rs
     * @param stmt
     * @param conn
     */
    public static void close(ResultSet rs,Statement stmt,Connection conn) {
        if (rs!=null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt!=null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }if (conn!=null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
