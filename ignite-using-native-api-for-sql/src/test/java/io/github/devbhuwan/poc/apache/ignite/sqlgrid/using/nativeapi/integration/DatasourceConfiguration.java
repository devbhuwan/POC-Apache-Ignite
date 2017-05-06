package io.github.devbhuwan.poc.apache.ignite.sqlgrid.using.nativeapi.integration;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.apache.ignite.configuration.IgniteConfiguration;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author Bhuwan Upadhyay
 * @date 2017/05/06
 */
public class DatasourceConfiguration {

    private static final Properties props = new Properties();

    static {
        try (InputStream in = IgniteConfiguration.class.getClassLoader().getResourceAsStream("integration/rdbms/connection.properties")) {
            props.load(in);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public static class DataSources {

        public static final MysqlDataSource INSTANCE_dsMySQL = createMySQL();

        private static MysqlDataSource createMySQL() {
            MysqlDataSource dataSource = new MysqlDataSource();

            dataSource.setURL(props.getProperty("dsMySQL.jdbc.url"));
            dataSource.setUser(props.getProperty("dsMySQL.jdbc.username"));
            dataSource.setPassword(props.getProperty("dsMySQL.jdbc.password"));

            return dataSource;
        }
    }

}
