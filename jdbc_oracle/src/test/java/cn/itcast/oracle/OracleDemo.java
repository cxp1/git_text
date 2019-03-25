package cn.itcast.oracle;

import oracle.jdbc.OracleTypes;
import org.junit.Test;

import java.sql.*;

public class OracleDemo {

    @Test
    public void test() throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.88.128:1521:orcl", "scott", "tiger");
        PreparedStatement pm = connection.prepareStatement("select * from emp where empno = ?");
        pm.setObject(1, 7788);
        ResultSet resultSet = pm.executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.getString("ename"));
        }
        connection.close();
        pm.close();
        resultSet.close();
    }

    /**
     * java调用存储过程
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.88.128:1521:orcl", "scott", "tiger");
        CallableStatement pm = connection.prepareCall("call p_yearsal(?,?)");
        pm.setObject(1, 7788);
        pm.registerOutParameter(2, OracleTypes.NUMBER);

        pm.execute();
        System.out.println(pm.getObject(2));

        connection.close();
        pm.close();
    }
}
