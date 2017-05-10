package io.github.devbhuwan.poc.apache.ignite.mongodb.pesistence.store.controller;

import io.github.devbhuwan.poc.apache.ignite.mongodb.pesistence.store.model.Payment;
import io.github.devbhuwan.poc.apache.ignite.mongodb.pesistence.store.repository.PaymentMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author Bhuwan Upadhyay
 * @date 2017/05/06
 */
@RestController
public class CrudController {

    @Autowired
    private PaymentMongoRepository paymentMongoRepository;

    @GetMapping("/add/{param}")
    public String add(@PathVariable("param") String param) {
        paymentMongoRepository.save(new Payment((long) new Random().nextInt(10000000)));
        return "Ok-added";
    }

}
