package cn.bjsxt.dao;

import java.util.List;
import cn.bjsxt.pojo.Emp;
/**
 * 专门用来操作emp的接口
 * @author chy
 *
 */
public interface EmpDao {

    /**
     * 查询所有员工信息(select all)
     * @return
     */
    List<Emp> selALL();//通过List集合来存储emp类型的数据

    /**
     * 根据员工编号查询员工信息(select by empno)
     * @param empno
     * @return
     */
    Emp selByEmpno(int empno);

    /**
     * 新增员工信息(insert)
     * @param emp
     * @return
     */
    boolean insEmp(Emp emp);

    /**
     * 根据编号修改工资（update sal by empno）
     * @param empno
     * @param sal
     * @return
     */
    boolean upsalByEmpno(int empno,double sal);

    /**
     * 根据编号删除员工
     * @param empno
     * @return
     */
    boolean delEmp(int empno);
}
