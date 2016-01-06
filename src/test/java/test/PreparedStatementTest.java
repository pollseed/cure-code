package test;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import mockit.Expectations;
import mockit.integration.junit4.JMockit;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JMockit.class)
public class PreparedStatementTest {

    private int executeUpdate(String sql) throws SQLException {
        try (Connection conn =
                DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test?user=root&password=password");
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    @Test
    public void test() throws SQLException {
        new Expectations() {
            {
            }
        };
        String sql =
                "insert into employees (id,job_code, store_id) values (7, 4, 19) on duplicate key update id = 7, job_code = 4, separated = now();";
        int executeUpdate1 = executeUpdate(sql);
        int executeUpdate2 = executeUpdate(sql);
        int executeUpdate3 = executeUpdate(sql);

        System.out.println("1回目 : " + executeUpdate1);
        System.out.println("2回目 : " + executeUpdate2);
        System.out.println("3回目 : " + executeUpdate3);

        assertEquals(executeUpdate1, 1);
        assertEquals(executeUpdate2, 2);
        assertEquals(executeUpdate3, 0);
    }
}
