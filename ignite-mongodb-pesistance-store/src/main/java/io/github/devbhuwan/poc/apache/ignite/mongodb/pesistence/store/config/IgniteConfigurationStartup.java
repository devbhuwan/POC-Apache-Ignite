package io.github.devbhuwan.poc.apache.ignite.mongodb.pesistence.store.config;

import io.github.devbhuwan.poc.apache.ignite.mongodb.pesistence.store.model.Payment;
import io.github.devbhuwan.poc.apache.ignite.mongodb.pesistence.store.store.PaymentMongodbStore;
import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.CacheAtomicityMode;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.configuration.FactoryBuilder;

/**
 * @author Bhuwan Upadhyay
 * @date 2017/05/06
 */
@Configuration
public class IgniteConfigurationStartup {

    static final String SPRING_DATA_IGNITE = "springDataInstance";
    static final String PAYMENT_MONGODB_CACHE = "paymentMongodbCache";

    @Bean
    public Ignite igniteInstance() {
        IgniteConfiguration cfg = new IgniteConfiguration();
        cfg.setIgniteInstanceName(SPRING_DATA_IGNITE);

        CacheConfiguration<Long, Payment> cacheCfg = new CacheConfiguration<>();
        cacheCfg.setIndexedTypes(Long.class, Payment.class);
        cacheCfg.setName(PAYMENT_MONGODB_CACHE);
        cacheCfg.setAtomicityMode(CacheAtomicityMode.TRANSACTIONAL);
        cacheCfg.setCacheStoreFactory(FactoryBuilder.factoryOf(PaymentMongodbStore.class));
        cacheCfg.setReadThrough(true);
        cacheCfg.setWriteBehindEnabled(true);
        cacheCfg.setWriteThrough(true);

        cfg.setCacheConfiguration(cacheCfg);
        return Ignition.start(cfg);
    }
}
