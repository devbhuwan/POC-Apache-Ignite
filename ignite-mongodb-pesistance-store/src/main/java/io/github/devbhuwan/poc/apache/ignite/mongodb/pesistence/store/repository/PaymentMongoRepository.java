package io.github.devbhuwan.poc.apache.ignite.mongodb.pesistence.store.repository;

import io.github.devbhuwan.poc.apache.ignite.mongodb.pesistence.store.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Bhuwan Upadhyay
 * @date 2017/05/06
 */
public interface PaymentMongoRepository extends MongoRepository<Payment, Long> {

}
