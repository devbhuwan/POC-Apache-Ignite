package io.github.devbhuwan.poc.apache.ignite.sqlgrid.using.nativeapi.integration;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.apache.ignite.Ignition;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Bhuwan Upadhyay
 * @date 2017/05/06
 */
public class RdbmsIntegrationWithApacheIgniteTest {

    @Test
    public void integrateMySQLRdbmsInApacheIgniteAsPersistence() {
        Ignition.start();

    }

}
