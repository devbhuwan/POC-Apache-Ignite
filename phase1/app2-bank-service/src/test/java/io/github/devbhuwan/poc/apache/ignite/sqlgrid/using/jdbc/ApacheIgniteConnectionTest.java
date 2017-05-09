package io.github.devbhuwan.poc.apache.ignite.sqlgrid.using.jdbc;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static org.junit.Assert.assertNotNull;

/**
 * @author Bhuwan Upadhyay
 * @date 2017/05/05
 */
public class ApacheIgniteConnectionTest {

    private final Properties testProperties = new Properties();
    private Connection connection;

    @Before
    public void setUp() throws IOException {
        testProperties.load(new FileInputStream("src/test/resources/connection.properties"));
    }

    @Ignore
    @Test
    public void isAliveApacheIgniteJdbcConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.apache.ignite.IgniteJdbcDriver");
        connection = DriverManager.getConnection(testProperties.getProperty("url"));
        assertNotNull(connection);
    }

    @After
    public void tearDown() throws SQLException {
        if (connection != null)
            connection.close();
    }
}
