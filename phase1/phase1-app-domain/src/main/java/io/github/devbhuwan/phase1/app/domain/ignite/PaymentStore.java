package io.github.devbhuwan.phase1.app.domain.ignite;

import io.github.devbhuwan.phase1.app.domain.Payment;
import org.apache.ignite.IgniteException;
import org.apache.ignite.cache.store.CacheStoreAdapter;
import org.apache.ignite.lifecycle.LifecycleAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentStore.class);
    public static final String PAYMENT_CACHE = "paymentCache";
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Payment load(Long key) throws CacheLoaderException {
        Map<String, Object> params = new HashMap<>();
        params.put("id", key);
        return jdbcTemplate.queryForObject("SELECT * FROM Payment WHERE id = ?", params, (rs, rowNum) -> Payment.builder()
                .id(rs.getLong("ID"))
                .amount(rs.getBigDecimal("AMOUNT"))
                .purpose(rs.getString("PURPOSE"))
                .creationDate(rs.getDate("CREATIONDATE").toLocalDate())
                .build());
    }

    @Override
    public void write(Cache.Entry<? extends Long, ? extends Payment> entry) throws CacheWriterException {
        Payment payment = entry.getValue();
        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("id", payment.getId());
        parameterMap.put("amount", payment.getAmount());
        parameterMap.put("purpose", payment.getPurpose());
        parameterMap.put("creationDate", payment.getCreationDate());
        jdbcTemplate.update("INSERT INTO PAYMENT (ID, AMOUNT, PURPOSE, CREATIONDATE) VALUES (:id, :amount, :purpose, :creationDate)", parameterMap);
    }

    @Override
    public void delete(Object key) throws CacheWriterException {
        Map<String, Object> params = new HashMap<>();
        params.put("ID", key);
        jdbcTemplate.update("DELETE FROM PAYMENT WHERE ID=?", params);
    }

    @Override
    public void start() throws IgniteException {
        LOGGER.info(">LifeAware#start()");
        if(jdbcTemplate == null)
            LOGGER.info(">Dependency is null");
    }

    @Override
    public void stop() throws IgniteException {

    }

}
