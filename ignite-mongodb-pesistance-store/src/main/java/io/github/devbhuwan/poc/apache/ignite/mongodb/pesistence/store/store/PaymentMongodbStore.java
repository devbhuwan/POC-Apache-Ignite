package io.github.devbhuwan.poc.apache.ignite.mongodb.pesistence.store.store;

import com.google.common.base.Preconditions;
import io.github.devbhuwan.poc.apache.ignite.mongodb.pesistence.store.model.Payment;
import io.github.devbhuwan.poc.apache.ignite.mongodb.pesistence.store.repository.PaymentMongoRepository;
import org.apache.ignite.IgniteException;
import org.apache.ignite.cache.store.CacheStoreAdapter;
import org.apache.ignite.lifecycle.LifecycleAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import javax.cache.Cache;
import javax.cache.integration.CacheLoaderException;
import javax.cache.integration.CacheWriterException;

/**
 * @author Bhuwan Upadhyay
 * @date 2017/05/06
 */
public class PaymentMongodbStore extends CacheStoreAdapter<Long, Payment> implements LifecycleAware {

    private PaymentMongoRepository paymentMongoRepository;
    private MongoOperations mongoOperations;

    @Override
    public Payment load(Long key) throws CacheLoaderException {
        return null;
    }

    @Override
    public void write(Cache.Entry<? extends Long, ? extends Payment> entry) throws CacheWriterException {

    }

    @Override
    public void delete(Object key) throws CacheWriterException {

    }

    @Override
    public void start() throws IgniteException {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("mongo-context.xml");
        paymentMongoRepository = context.getBean(PaymentMongoRepository.class);
        mongoOperations = context.getBean(MongoOperations.class);
        if (!mongoOperations.collectionExists(Payment.class))
            mongoOperations.createCollection(Payment.class);
    }

    @Override
    public void stop() throws IgniteException {

    }
}
