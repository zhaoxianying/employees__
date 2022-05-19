package cn.bjsxt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.bjsxt.pojo.Emp;
import cn.bjsxt.util.DBUtil;

/**
 * 相比上版本而言封装查询语句
 *
 * @author chy
 *
 */
public class EmpDaoImpl extends BaseDao implements EmpDao {

    @Override
    public List<Emp> selALL() {
        String sql = "select * from emp";
        return QueryAll(Emp.class, sql);
    }

    @Override
    public Emp selByEmpno(int empno) {
        String sql = "select * from emp where empno=?";

        return QueryAll(Emp.class, sql, empno).get(0);

    }

    @Override
    public boolean insEmp(Emp emp) {
        String sql = "insert into emp values (?, ?, ?, ?, sysdate, ?, ?, ?)";
        Object[] params = { emp.getEmpno(), emp.getEname(), emp.getJob(), emp.getMgr(), emp.getSal(), emp.getComm(),
                emp.getDeptno() };
        return update(sql, params);
    }

    @Override
    public boolean upsalByEmpno(int empno, double sal) {
        String sql = "update emp set sal=? where empno=?";
        return update(sql, sal, empno);
    }

    @Override
    public boolean delEmp(int empno) {

        return update("delete from emp where empno=?", empno);
    }

}
