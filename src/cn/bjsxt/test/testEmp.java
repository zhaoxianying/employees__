package cn.bjsxt.test;

import cn.bjsxt.view.MenuView;

import java.util.Scanner;

public class testEmp {

    Scanner sc=new Scanner(System.in);
    public void showMenu() {
        System.out.println("*************************************");
        System.out.println("************欢迎使用员工管理系统**********");
        System.out.println("*************************************");
        System.out.println("请输入对应数字进行操作");


        while (true) {
            System.out.println("1、添加员工");
            System.out.println("2、查询所有员工");
            System.out.println("3、根据编号查询员工");
            System.out.println("4、修改员工工资");
            System.out.println("5、删除员工");
            System.out.println("6、退出");
            int num=sc.nextInt();
MenuView view=new MenuView();
            switch (num) {
                case 1:
                    view.addEmp();
                    continue;
                case 2:
                    view.selAll();
                    continue;
                case 3:
                    view.selByEmpno();
                    continue;
                case 4:
                   view.updSal();
                    break;
                case 5:
                    view.delEmp();
                    continue;
                case 6:
                    System.out.println("谢谢使用，byebye~");
                    break;//用于跳出switch循环

                default:
                    System.out.println("输入有误，请从新输入");
                    continue;
            }
            break;//用于跳出while循环
        }
    }
    public static void main(String[] args) {
        testEmp test=new testEmp();
        test.showMenu();
    }
}

