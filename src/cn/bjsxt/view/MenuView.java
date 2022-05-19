package cn.bjsxt.view;

import java.util.List;
import java.util.Scanner;

import cn.bjsxt.dao.EmpDao;
import cn.bjsxt.dao.EmpDaoImpl;
import cn.bjsxt.pojo.Emp;

public class MenuView {
    Scanner sc=new Scanner(System.in);
    EmpDao dao=new EmpDaoImpl();


    public void delEmp() {
        System.out.println("请输入员工编号：");
        int empno=sc.nextInt();
        if (dao.delEmp(empno)) {
            System.out.println("删除成功");
        }else {
            System.out.println("删除失败");
        }
    }

    public void updSal() {
        System.out.println("请输入员工编号：");
        int empno=sc.nextInt();
        System.out.println("请输入薪资：");
        double sal=sc.nextDouble();
        if (dao.upsalByEmpno(empno, sal)) {
            System.out.println("修改成功");
        }else {
            System.out.println("修改失败");
        }
    }

    public void selByEmpno() {
        System.out.println("请输入员工编号");
        int empno=sc.nextInt();
        Emp emp=dao.selByEmpno(empno);
        //定义emp对象，方便打印
        System.out.println(emp);
    }

    public void selAll() {
        List<Emp>list=dao.selALL();
        for (Emp emp : list) {
            System.out.println(emp);
        }
    }

    public void addEmp() {
        System.out.println("请输入编号：");
        int empno=sc.nextInt();
        System.out.println("请输入姓名：");
        String ename=sc.next();
        System.out.println("请输入职位：");
        String job=sc.next();
        System.out.println("请输入领导编号");
        int mgr=sc.nextInt();
        System.out.println("请输入薪资：");
        double sal=sc.nextDouble();
        System.out.println("请输入提成：");
        double comm=sc.nextDouble();
        System.out.println("请输入部门编号：");
        int deptno=sc.nextInt();

        Emp emp = new Emp(empno, ename, job, mgr, null, sal, comm, deptno);
        if(dao.insEmp(emp)) {
            System.out.println("添加成功!");
        } else {
            System.out.println("添加失败!");
        }

    }
}
