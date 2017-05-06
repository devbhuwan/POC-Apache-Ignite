package io.github.devbhuwan.poc.apache.ignite.sqlgrid.using.nativeapi.integration;

import org.junit.Before;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Bhuwan Upadhyay
 * @date 2017/05/06
 */
public class RdbmsIntegrationWithApacheIgniteTest {

    private final Properties testProperties = new Properties();

    @Before
    public void setUp() throws IOException {
        testProperties.load(new FileInputStream("src/test/resources/connection.properties"));
    }


}
