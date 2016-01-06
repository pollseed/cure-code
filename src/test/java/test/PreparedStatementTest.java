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


/*
メモ

DDL

CREATE SCHEMA `new_schema` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `employees` (
   `id` int(11) NOT NULL,
   `fname` varchar(30) DEFAULT NULL,
   `lname` varchar(30) DEFAULT NULL,
   `hired` date NOT NULL DEFAULT '1970-01-01',
   `separated` date NOT NULL DEFAULT '9999-12-31',
   `job_code` int(11) NOT NULL,
   `store_id` int(11) NOT NULL,
   PRIMARY KEY (`id`,`job_code`,`store_id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8
 /*!50100 PARTITION BY RANGE (store_id)
 (PARTITION p0 VALUES LESS THAN (6) ENGINE = InnoDB,
  PARTITION p1 VALUES LESS THAN (11) ENGINE = InnoDB,
  PARTITION p2 VALUES LESS THAN (16) ENGINE = InnoDB,
  PARTITION p3 VALUES LESS THAN (21) ENGINE = InnoDB) */
