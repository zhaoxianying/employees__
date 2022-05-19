package cn.bjsxt.pojo;

import java.io.Serializable;
import java.sql.Date;

/**
 *	实体类
 * @author chy
 *
 */
public class Emp implements Serializable{//一个实体类在网络中传播需要实现序列化接口
    private int empno;
    private String ename;
    private String job;
    private int mgr;
    private Date hiredate;
    private double sal;
    private double comm;
    private int deptno;

    //alt+shift+s 调出窗口;r;alt+a 全选
    public int getEmpno() {
        return empno;
    }
    public void setEmpno(int empno) {
        this.empno = empno;
    }
    public String getEname() {
        return ename;
    }
    public void setEname(String ename) {
        this.ename = ename;
    }
    public String getJob() {
        return job;
    }
    public void setJob(String job) {
        this.job = job;
    }
    public int getMgr() {
        return mgr;
    }
    public void setMgr(int mgr) {
        this.mgr = mgr;
    }
    public Date getHiredate() {
        return hiredate;
    }
    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }
    public double getSal() {
        return sal;
    }
    public void setSal(double sal) {
        this.sal = sal;
    }

    public double getComm() {
        return comm;
    }
    public void setComm(double comm) {
        this.comm = comm;
    }
    public int getDeptno() {
        return deptno;
    }
    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }


    //hashcode()和equals的作用是方便比较
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(comm);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + deptno;
        result = prime * result + empno;
        result = prime * result + ((ename == null) ? 0 : ename.hashCode());
        result = prime * result + ((hiredate == null) ? 0 : hiredate.hashCode());
        result = prime * result + ((job == null) ? 0 : job.hashCode());
        result = prime * result + mgr;
        temp = Double.doubleToLongBits(sal);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Emp other = (Emp) obj;
        if (Double.doubleToLongBits(comm) != Double.doubleToLongBits(other.comm))
            return false;
        if (deptno != other.deptno)
            return false;
        if (empno != other.empno)
            return false;
        if (ename == null) {
            if (other.ename != null)
                return false;
        } else if (!ename.equals(other.ename))
            return false;
        if (hiredate == null) {
            if (other.hiredate != null)
                return false;
        } else if (!hiredate.equals(other.hiredate))
            return false;
        if (job == null) {
            if (other.job != null)
                return false;
        } else if (!job.equals(other.job))
            return false;
        if (mgr != other.mgr)
            return false;
        if (Double.doubleToLongBits(sal) != Double.doubleToLongBits(other.sal))
            return false;
        return true;
    }
    public Emp(int empno, String ename, String job, int mgr, Date hiredate, double sal, double conn, int deptno) {
        super();
        this.empno = empno;
        this.ename = ename;
        this.job = job;
        this.mgr = mgr;
        this.hiredate = hiredate;
        this.sal = sal;
        this.comm = conn;
        this.deptno = deptno;
    }

    //反射里面不通过new创建对象，大部分使用的是无参构造器
    public Emp() {
        super();
    }

    public String toString() {
        return "EmpDao [empno=" + empno + ", ename=" + ename + ", job=" + job + ", mgr=" + mgr + ", hiredate=" + hiredate
                + ", sal=" + sal + ", conn=" + comm + ", deptno=" + deptno + "]";
    }





}