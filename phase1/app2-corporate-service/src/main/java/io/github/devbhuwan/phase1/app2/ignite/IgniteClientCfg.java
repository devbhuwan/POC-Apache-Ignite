package io.github.devbhuwan.phase1.app2.ignite;

import io.github.devbhuwan.phase1.app.domain.ignite.PaymentStore;
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
 * @date 2017/05/07
 */
@Configuration
public class IgniteClientCfg {

    public static final String PAYMENT_CACHE = "paymentCache";

    @Bean
    public Ignite ignite() {
        Ignition.setClientMode(true);
        IgniteConfiguration cfg = new IgniteConfiguration();
        //cfg.setPeerClassLoadingEnabled(true);
        CacheConfiguration configuration = new CacheConfiguration();
        configuration
                .setCacheStoreFactory(FactoryBuilder.factoryOf(PaymentStore.class))
                .setName(PAYMENT_CACHE)
                .setAtomicityMode(CacheAtomicityMode.TRANSACTIONAL)
                .setReadThrough(true)
                .setWriteThrough(true)
                .setWriteBehindEnabled(true);
        cfg.setCacheConfiguration(configuration);
        return Ignition.start(cfg);
    }

}
