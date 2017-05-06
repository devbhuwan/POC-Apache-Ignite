package io.github.devbhuwan.poc.apache.ignite.sqlgrid.using.nativeapi;

import lombok.Getter;
import lombok.Setter;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.cache.query.annotations.QuerySqlField;
import org.apache.ignite.configuration.CacheConfiguration;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Bhuwan Upadhyay
 * @date 2017/05/05
 */
public class ApacheIgniteSQLTest {

    private static final String PAYMENT_CACHE = "Payments";

    @Test
    public void sqlDMLOperationInApacheIgnite() {
        try (Ignite ignite = Ignition.start("example-ignite.xml")) {
            CacheConfiguration<Long, Payment> paymentCacheCfg = new CacheConfiguration<>(PAYMENT_CACHE);
            paymentCacheCfg.setIndexedTypes(Long.class, Payment.class);
            try (IgniteCache<Long, Payment> paymentCache = ignite.getOrCreateCache(paymentCacheCfg)) {
                SqlFieldsQuery insertQuery = new SqlFieldsQuery("insert into Payment (_key, id, detail, amount) values (?, ?, ?, ?)");
                paymentCache.query(insertQuery.setArgs(1L, 10245L, "Transfer to Education Ministry", BigDecimal.valueOf(565656)));
                paymentCache.query(insertQuery.setArgs(2L, 10246L, "Transfer to Finance Ministry", BigDecimal.valueOf(1000)));

                SqlFieldsQuery selectQuery = new SqlFieldsQuery("select p.amount from Payment p where p.id=?");

                List<List<?>> results = paymentCache.query(selectQuery.setArgs(10245L)).getAll();
                assertEquals("Result Set Size Must be 1", 1, results.size());
                assertEquals(BigDecimal.valueOf(565656), results.get(0).get(0));
            }
        }
    }

}

