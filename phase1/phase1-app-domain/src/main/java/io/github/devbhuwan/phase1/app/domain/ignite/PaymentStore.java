package io.github.devbhuwan.phase1.app.domain.ignite;

import io.github.devbhuwan.phase1.app.domain.Payment;
import org.apache.ignite.IgniteException;
import org.apache.ignite.cache.store.CacheStoreAdapter;
import org.apache.ignite.lifecycle.LifecycleAware;
import org.apache.ignite.resources.SpringResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.cache.Cache;
import javax.cache.integration.CacheLoaderException;
import javax.cache.integration.CacheWriterException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Bhuwan Upadhyay
 * @date 2017/05/07
 */
public class PaymentStore extends CacheStoreAdapter<Long, Payment> implements LifecycleAware {

    public static final String PAYMENT_CACHE = "paymentCache";
    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentStore.class);

    @SpringResource(resourceName = "jdbcTemplate")
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Payment load(Long key) throws CacheLoaderException {
        Map<String, Object> params = new HashMap<>();
        params.put("id", key);
        return jdbcTemplate.query("SELECT * FROM PAYMENT WHERE ID = :id", params, rs -> {
            if (rs.next()) {
                return Payment.builder()
                        .id(rs.getLong("ID"))
                        .amount(rs.getBigDecimal("AMOUNT"))
                        .purpose(rs.getString("PURPOSE"))
                        .creationDate(rs.getDate("CREATIONDATE"))
                        .status(rs.getString("STATUS"))
                        .build();
            }
            return null;
        });
    }

    @Override
    public void write(Cache.Entry<? extends Long, ? extends Payment> entry) throws CacheWriterException {
        Payment payment = entry.getValue();
        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("id", payment.getId());
        parameterMap.put("amount", payment.getAmount());
        parameterMap.put("purpose", payment.getPurpose());
        parameterMap.put("creationDate", payment.getCreationDate());
        parameterMap.put("status", payment.getStatus());
        if (load(entry.getKey()) == null)
            jdbcTemplate.update("INSERT INTO PAYMENT (ID, AMOUNT, PURPOSE, CREATIONDATE, STATUS) VALUES (:id, :amount, :purpose, :creationDate, :status)", parameterMap);
        else
            jdbcTemplate.update("UPDATE PAYMENT SET AMOUNT = :amount, PURPOSE = :purpose, CREATIONDATE = :creationDate, STATUS = :status WHERE ID = :id", parameterMap);
    }

    @Override
    public void delete(Object key) throws CacheWriterException {
        Map<String, Object> params = new HashMap<>();
        params.put("id", key);
        jdbcTemplate.update("DELETE FROM PAYMENT WHERE ID=:id", params);
    }

    @Override
    public void start() throws IgniteException {
        LOGGER.info(">LifeAware#start()");
        if (jdbcTemplate == null)
            LOGGER.info(">Dependency is null");
    }

    @Override
    public void stop() throws IgniteException {

    }

}
