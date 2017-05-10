package io.github.devbhuwan.phase1.app.domain.ignite;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.Insert.Options;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import io.github.devbhuwan.phase1.app.domain.Payment;
import org.apache.ignite.cache.store.CacheStoreAdapter;
import org.apache.ignite.resources.SpringResource;
import org.apache.lucene.search.BooleanClause;
import org.springframework.data.cassandra.core.CassandraAdminOperations;
import org.springframework.data.cassandra.core.CassandraOperations;

import javax.cache.Cache;
import javax.cache.integration.CacheLoaderException;
import javax.cache.integration.CacheWriterException;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Bhuwan Upadhyay
 * @date 2017/05/10
 */
public class PaymentCassandraStore extends CacheStoreAdapter<Long, Payment> {

    @SpringResource(resourceName = "cqlTemplate")
    private CassandraOperations cqlTemplate;

    @Override
    public Payment load(Long key) throws CacheLoaderException {
        ResultSet resultSet = cqlTemplate.getSession().execute(
                QueryBuilder.select().from("PAYMENT")
                        .where(QueryBuilder.eq("id", key)).getQueryString());
        Row row = resultSet.one();
        if (row != null) {
            Payment.builder()
                    .amount(row.get("amount", BigDecimal.class))
                    .creationDate(row.get("creationDate", Date.class))
                    .purpose(row.get("purpose", String.class))
                    .status(row.get("status", String.class))
                    .id(row.get("id", Long.class))
                    .build();
        }
        return null;
    }

    @Override
    public void write(Cache.Entry<? extends Long, ? extends Payment> entry) throws CacheWriterException {
        if (load(entry.getKey()) == null)
            cqlTemplate.execute(QueryBuilder.insertInto("PAYMENT")
                    .value("id", entry.getValue().getId())
                    .value("amount", entry.getValue().getAmount())
                    .value("purpose", entry.getValue().getPurpose())
                    .value("creationDate", entry.getValue().getCreationDate())
                    .value("status", entry.getValue().getStatus())
            );
        else
            cqlTemplate.execute(QueryBuilder.update("PAYMENT")
                    .with(QueryBuilder.set("amount", entry.getValue().getAmount()))
                    .and(QueryBuilder.set("purpose", entry.getValue().getPurpose()))
                    .and(QueryBuilder.set("creationDate", entry.getValue().getCreationDate()))
                    .and(QueryBuilder.set("status", entry.getValue().getStatus()))
                    .where(QueryBuilder.eq("id", entry.getKey())));
    }

    @Override
    public void delete(Object key) throws CacheWriterException {
        cqlTemplate.execute(QueryBuilder.delete().from("PAYMENT").where(QueryBuilder.eq("id", key)));
    }
}
