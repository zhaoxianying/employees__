package cn.bjsxt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.management.Query;

import org.apache.commons.beanutils.BeanUtils;

import cn.bjsxt.util.DBUtil;

public class BaseDao {

    /**
     * @param sql
     * @param params
     * @return
     */
    protected boolean update(String sql,Object...params) {
        //protected保证同一个包以及子类能够访问
        Connection conn=DBUtil.getConn();
        PreparedStatement pstmt=DBUtil.getPstmt(conn, sql);
        DBUtil.bindParam(pstmt, params);

        try {
            int num=pstmt.executeUpdate();
            if (num>0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(null, pstmt, conn);
        }

        return false;

    }

    /**
     * 返回List集合的查询操作
     * @param cls
     * @param sql
     * @return
     */
    protected <T>  List<T>QueryAll(Class<T>cls,String sql,Object...params){
        List<T>list=new ArrayList<>();

        Connection conn=DBUtil.getConn();
        PreparedStatement pstmt=DBUtil.getPstmt(conn, sql);
        DBUtil.bindParam(pstmt,params);
        ResultSet rs=null;
        try {//获取原始数据
            rs=pstmt.executeQuery();
            ResultSetMetaData metaData=rs.getMetaData();
            while (rs.next()) {
                T bean=cls.newInstance();//反射
                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    BeanUtils.setProperty(bean, metaData.getColumnLabel(i+1).toLowerCase(), rs.getObject(i+1));
                }//toLowerCase()将查到的字母小写，因为从Oracle哪里所查询到的结果是大写的
                list.add(bean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(null, pstmt, conn);
        }
        return list;
    }

    /**
     * 返回一个对象的查询
     * @param cls
     * @param sql
     * @param params
     * @return
     */
    protected <T> T QueryOne(Class<T>cls,String sql,Object...params){

        Connection conn=DBUtil.getConn();
        PreparedStatement pstmt=DBUtil.getPstmt(conn, sql);
        DBUtil.bindParam(pstmt,params);
        ResultSet rs=null;
        try {//获取原始数据
            rs=pstmt.executeQuery();
            ResultSetMetaData metaData=rs.getMetaData();
            if (rs.next()) {
                T bean=cls.newInstance();//反射
                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    BeanUtils.setProperty(bean, metaData.getColumnLabel(i+1).toLowerCase(), rs.getObject(i+1));
                }
                return bean;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(null, pstmt, conn);
        }
        return null;
    }
}