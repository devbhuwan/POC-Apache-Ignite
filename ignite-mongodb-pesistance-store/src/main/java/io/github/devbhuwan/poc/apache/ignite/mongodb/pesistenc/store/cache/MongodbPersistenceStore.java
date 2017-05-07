package io.github.devbhuwan.poc.apache.ignite.mongodb.pesistenc.store.cache;

import io.github.devbhuwan.poc.apache.ignite.mongodb.pesistenc.store.model.Payment;
import org.apache.ignite.IgniteException;
import org.apache.ignite.cache.store.CacheStoreAdapter;
import org.apache.ignite.lifecycle.LifecycleAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.cache.Cache;
import javax.cache.integration.CacheLoaderException;
import javax.cache.integration.CacheWriterException;

/**
 * @author Bhuwan Upadhyay
 * @date 2017/05/06
 */
public class MongodbPersistenceStore extends CacheStoreAdapter<Long, Payment> implements LifecycleAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(MongodbPersistenceStore.class);

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
        LOGGER.info("->start()");
    }

    @Override
    public void stop() throws IgniteException {
        LOGGER.info("->stop()");
    }
}
